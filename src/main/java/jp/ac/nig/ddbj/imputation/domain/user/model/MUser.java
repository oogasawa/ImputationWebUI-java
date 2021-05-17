package jp.ac.nig.ddbj.imputation.domain.user.model;

// import java.util.Date;
// import java.util.List;

import lombok.Data;

@Data
public class MUser {
    private String userId;
    private String userName;
    private String affiliation;    
    private String password;
    private String role;
    // private Department department;
    // private List<Salary> salaryList;
}
