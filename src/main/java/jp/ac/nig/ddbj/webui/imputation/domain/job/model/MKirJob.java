package jp.ac.nig.ddbj.webui.imputation.domain.job.model;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class MKirJob {
    private String jobId;
    private String analysisType;
    private String state;
    private String userId;

    // private MSnpParams params;
    private String params;
}
