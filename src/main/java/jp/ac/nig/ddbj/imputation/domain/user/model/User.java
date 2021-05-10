/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nig.ddbj.imputation.domain.user.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {
    private String id;
    private String password;
    private String nameJ;
    private String nameE;
    private String affliation;
    private String role;
}

