/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nig.ddbj.webui.imputation.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;

import jp.ac.nig.ddbj.webui.imputation.util.IdGenerator;
import jp.ac.nig.ddbj.webui.imputation.json.JobInfo;
import jp.ac.nig.ddbj.webui.imputation.domain.job.model.MKirJob;
import jp.ac.nig.ddbj.webui.imputation.domain.job.model.MKirParams;
import jp.ac.nig.ddbj.webui.imputation.domain.job.service.KirJobService;
import jp.ac.nig.ddbj.webui.imputation.form.KirImputationForm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author oogasawa
 */
@Controller
@Slf4j
public class KirImputationController {

    @Autowired
    private KirJobService service;

    final static Map<String, String> POPULATION_MODEL_MAP = Collections
            .unmodifiableMap(new LinkedHashMap<String, String>() {
                {
                    put("African", "African");
                    put("Asian", "Asian");
                    put("European", "European");
                    put("Japanese", "Japanese");
                    put("Korean", "Korean");
                    put("Han Chinese", "Han Chinese");
                    put("Hispanic", "Hispanic");
                    put("Multi-ethic", "Multi-ethic");
                }
            });

    final static Map<String, String> GENOTYPING_PLATFORM_MAP = Collections
            .unmodifiableMap(new LinkedHashMap<String, String>() {
                {
                    put("Affymetrix_GenomeWideHumanSNPArray_6.0", "Affymetrix_GenomeWideHumanSNPArray_6.0");
                    put("Affymetrix_Axom_ASI", "Affymetrix_Axom_ASI");
                    put("Affymetrix_Axom_Japonica", "Affymetrix_Axom_Japonica");
                    put("AFfymetrix_Axom_Neo", "Affymetrix_Axom_Neo");
                }
            });

    String baseDir = "/home/oogasawa/tmp/WabiImputation";

    public String createDirName(String jobId) {
        ArrayList<String> result = new ArrayList<String>();
        String[] jobDir = jobId.split("_");
        result.add(this.baseDir);

        for (int i = 0; i < jobDir.length; i++) {
            result.add(jobDir[i]);
        }

        return String.join("/", result);
    }

    public void createDirs(String jobId) {
        String dirName = this.createDirName(jobId);
        File dirObj = new File(dirName);

        if (!dirObj.exists()) {
            dirObj.mkdirs();
        }

    }

    @GetMapping("/KIR_imputation")
    public String getKirImputation(Model model, Locale locale, @ModelAttribute KirImputationForm form) {

        model.addAttribute("populationModelMap", POPULATION_MODEL_MAP);
        model.addAttribute("genotypingPlatformMap", GENOTYPING_PLATFORM_MAP);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUsername();// get logged in username
        //form.setJobList(this.service.getJobList("KIR", userId));
        model.addAttribute("jobList", this.service.getJobList("KIR", userId));

        return "imputation/KIR_imputation";
    }


    @PostMapping("/KIR_imputation")
    public String postKirImputation(Model model, Locale locale,
                                    @ModelAttribute KirImputationForm form) {


        model.addAttribute("populationModelMap", POPULATION_MODEL_MAP);
        model.addAttribute("genotypingPlatformMap", GENOTYPING_PLATFORM_MAP);


        // JobInfo result = new JobInfo();
        String jobId = null;
        if (form.getJobId() == null) {
            jobId = IdGenerator.generate();
            // result.setJobId(IdGenerator.generate());
            this.createDirs(jobId);
            // result.setState("initialized");
        } else {
            jobId = form.getJobId();
        }


        MultipartFile mfile = form.getPlinkFile();

        log.info("form.getGenomicAssembly(): " + form.getGenomicAssembly());
        log.info("form.getPopulatonModel()" + form.getPopulationModel());
        log.info("form.getGenotypingPlatform()" + form.getGenotypingPlatform());
        log.info("form.getOutputFormat()" + form.getOutputFormat());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUsername();// get logged in username
        log.info("form : user : " + userId);

        MKirParams params = MKirParams.builder()
            .plinkFileName(mfile.getOriginalFilename())
            .genomicAssembly(form.getGenomicAssembly())
            .populationModel(form.getPopulationModel())
            .genotypingPlatform(form.getGenotypingPlatform())
            .outputFormat(form.getOutputFormat())
            .build();

        Gson gson = new Gson();
        String paramsJson = gson.toJson(params);

        MKirJob job = MKirJob.builder()
            .jobId(jobId)
            .analysisType("KIR")
            .state("Initialized")
            .userId(userId)
            .params(paramsJson)
            .build();

        this.service.addJobInfo(job);

        // form.setJobList(this.service.getJobList("KIR", userId));
        model.addAttribute("jobList", this.service.getJobList("KIR", userId));

        log.info("form.getPlinkFile(): " + mfile.getOriginalFilename());
        this.uploadFile(jobId, mfile);

        return "imputation/KIR_imputation";
    }




    public JobInfo uploadFile(String jobId, MultipartFile file) {
        JobInfo result = new JobInfo();
        result.setJobId(jobId);
        // if (this.existsJobId(jobId)) {

            // normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                Path path = Paths.get(this.createDirName(jobId) + "/" + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            result.setState("uploading");

        // } else {
        //     result.setState("not-found");
        // }

        return result;
    }


}




