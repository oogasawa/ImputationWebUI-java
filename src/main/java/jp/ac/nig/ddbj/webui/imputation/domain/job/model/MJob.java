package jp.ac.nig.ddbj.webui.imputation.domain.job.model;

// import java.util.Date;
// import java.util.List;

import lombok.Data;

@Data
public class MJob {
    private String jobId;
    private String analysisType;
    private String state;
    private String userId;


    private String plinkFileName;
    private String genomicAssembly;
    private String populationModel;
    private String genotypingPlatform;
    private String outputFormat;

}
