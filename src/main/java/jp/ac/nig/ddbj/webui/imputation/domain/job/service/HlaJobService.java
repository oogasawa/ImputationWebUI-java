package jp.ac.nig.ddbj.webui.imputation.domain.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nig.ddbj.webui.imputation.domain.job.model.MHlaJob;
import jp.ac.nig.ddbj.webui.imputation.repository.HlaJobRepository;

@Service
public class HlaJobService {

    @Autowired
    private HlaJobRepository repository;


    public void addJobInfo(MHlaJob jobInfo) {
        repository.insertJobInfo(jobInfo);
    }


    public void deleteJob(String jobId) {
        repository.deleteJob(jobId);
    }

    public List<MHlaJob> getJobList(String analysisType, String userId) {
        return repository.getJobList(analysisType, userId);
    }

}
