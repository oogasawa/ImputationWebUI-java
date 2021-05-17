package jp.ac.nig.ddbj.imputation.form;

import java.util.Date;
import java.util.List;

// import jp.ac.nig.ddbj.imputation.domain.user.model.Department;
// import jp.ac.nig.ddbj.imputation.domain.user.model.Salary;

import lombok.Data;

@Data
public class UserDetailForm {
    private String userId;
    private String userName;
    private String affiliation;
    private String password;
    // private Department department;
    // private List<Salary> salaryList;
}
