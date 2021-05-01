package jp.ac.nig.ddbj.imputation.domain.user.model;

import lombok.Data;

@Data
public class Salary {
    private String userId;
    private String yearMonth;
    private Integer salary;
}
