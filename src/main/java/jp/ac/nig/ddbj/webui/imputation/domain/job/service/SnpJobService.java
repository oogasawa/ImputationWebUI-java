package jp.ac.nig.ddbj.webui.imputation.domain.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nig.ddbj.webui.imputation.domain.job.model.MSnpJob;
import jp.ac.nig.ddbj.webui.imputation.repository.SnpJobRepository;

@Service
public class SnpJobService {

    @Autowired
    private SnpJobRepository repository;


    public void addJobInfo(MSnpJob jobInfo) {
        repository.insertJobInfo(jobInfo);
    }


    public void deleteJob(String jobId) {
        repository.deleteJob(jobId);
    }

    public List<MSnpJob> getJobList(String analysisType, String userId) {
        return repository.getJobList(analysisType, userId);
    }

}
