/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nig.ddbj.imputation.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import jp.ac.nig.ddbj.imputation.form.SnpImputationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class SnpImputationController {

    final static Map<String, String> POPULATION_MODEL_MAP
        = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
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



    final static Map<String, String> GENOTYPING_PLATFORM_MAP
        = Collections.unmodifiableMap(new LinkedHashMap<String,String>() {
        {
            put("Affymetrix_GenomeWideHumanSNPArray_6.0","Affymetrix_GenomeWideHumanSNPArray_6.0");
            put("Affymetrix_Axom_ASI","Affymetrix_Axom_ASI");
            put("Affymetrix_Axom_Japonica","Affymetrix_Axom_Japonica");
            put("AFfymetrix_Axom_Neo","Affymetrix_Axom_Neo");
        }
    });
 


    @GetMapping("/SNP_imputation")
    public String getSnpImputation(Model model, Locale locale,
            @ModelAttribute SnpImputationForm form) {

        model.addAttribute("populationModelMap", POPULATION_MODEL_MAP);
        model.addAttribute("genotypingPlatformMap", GENOTYPING_PLATFORM_MAP);

        return "imputation/SNP_imputation";
    }

    @PostMapping("/SNP_imputation")
    public String postSnpImputation(Model model, Locale locale,
                                    @ModelAttribute SnpImputationForm form) {


        model.addAttribute("populationModelMap", POPULATION_MODEL_MAP);
        model.addAttribute("genotypingPlatformMap", GENOTYPING_PLATFORM_MAP);

        MultipartFile mfile = form.getPlinkFile();

        log.info("form.getPlinkFile(): " + mfile.getOriginalFilename());
        log.info("form.getGenomicAssembly(): " + form.getGenomicAssembly());
        log.info("form.getPopulatonModel()" + form.getPopulationModel());
        log.info("form.getGenotypingPlatform()" + form.getGenotypingPlatform());
        log.info("form.getOutputFormat()" + form.getOutputFormat());

        return "imputation/SNP_imputation";
    }

}

