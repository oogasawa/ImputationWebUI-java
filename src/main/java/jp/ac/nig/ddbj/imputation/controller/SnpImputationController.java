/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nig.ddbj.imputation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author oogasawa
 */
@Controller
public class SnpImputationController {
    @GetMapping("/SNP_imputation")
    public String getSnpImputation() {
        return "imputation/SNP_imputation";
    }
}

