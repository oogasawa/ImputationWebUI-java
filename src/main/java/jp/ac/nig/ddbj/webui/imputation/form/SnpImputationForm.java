/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nig.ddbj.webui.imputation.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 *
 * @author oogasawa
 */
@Data
public class SnpImputationForm {

    private MultipartFile plinkFile;
    private String genomicAssembly = "hg19";
    private String populationModel;
    private String genotypingPlatform;
    private String outputFormat = "xlsx";

}


