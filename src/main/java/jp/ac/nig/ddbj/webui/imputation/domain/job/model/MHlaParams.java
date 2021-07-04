package jp.ac.nig.ddbj.webui.imputation.domain.job.model;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class MHlaParams {
    private String plinkFileName;
    private String genomicAssembly;
    private String populationModel;
    private String genotypingPlatform;
    private String outputFormat;
}
