package jp.ac.nig.ddbj.webui.imputation.rest;


import jp.ac.nig.ddbj.webui.imputation.domain.job.service.HlaJobService;
import jp.ac.nig.ddbj.webui.imputation.form.HlaImputationForm;
import jp.ac.nig.ddbj.webui.imputation.util.IdGenerator;
import jp.ac.nig.ddbj.webui.imputation.json.JobInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class HlaRestController {

    @Autowired
    HlaJobService service;



    String baseDir = "/home/oogasawa/tmp/WabiImputation";

    ObjectMapper mapper = new ObjectMapper();


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


    @DeleteMapping("/rest/HLA_imputation/{jobId}")
    public JobInfo deleteJob(@PathVariable String jobId) {
        JobInfo result = new JobInfo();
        result.setJobId(jobId);
        if (this.existsJobId(jobId)) {
            this.deleteJobDir(jobId);
            
            this.service.deleteJob(jobId);

            result.setState("deleted");
        } else {
            result.setState("not-found");
        }

        return result;
    }


    public boolean deleteJobDir(String jobId) {
        String dirName = this.createDirName(jobId);
        File dirObj = new File(dirName);
        return this.deleteDirectory(dirObj);
    }

    // https://www.baeldung.com/java-delete-directory
    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                this.deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }



    public boolean existsJobId(String jobId) {
        String dirName = this.createDirName(jobId);
        File dirObj = new File(dirName);
        return dirObj.exists();
    }


    @GetMapping("/rest/HLA_imputation/{jobId}/state")
    public JobInfo getJobState(@PathVariable String jobId) {
        JobInfo result = new JobInfo();
        result.setJobId(jobId);
        if (this.existsJobId(jobId)) {
            result.setState("initialized");
        }
        else {
            result.setState("not-found");
        }

        return result;
    }



    @PostMapping("/rest/HLA_imputation")
    public JobInfo post(HlaImputationForm form) {
        JobInfo result = new JobInfo();
        String jobId = null;
        if (form.getJobId() == null) {
            jobId = IdGenerator.generate();
            result.setJobId(IdGenerator.generate());
            this.createDirs(jobId);
            result.setState("initialized");
        }
        else {
            jobId = form.getJobId();
        }

        return result;
    }




    @PostMapping("/rest/HLA_imputation/{jobId}/file_upload")
    public JobInfo uploadFile(@PathVariable String jobId, @RequestParam("file") MultipartFile file) {
        JobInfo result = new JobInfo();
        result.setJobId(jobId);
        if (this.existsJobId(jobId)) {

            // normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                Path path = Paths.get(this.createDirName(jobId) + "/" + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            result.setState("uploading");

        } else {
            result.setState("not-found");
        }

        return result;
    }




}
