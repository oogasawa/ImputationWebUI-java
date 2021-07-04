package jp.ac.nig.ddbj.webui.imputation.domain.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nig.ddbj.webui.imputation.domain.job.model.MKirJob;
import jp.ac.nig.ddbj.webui.imputation.repository.KirJobRepository;

@Service
public class KirJobService {

    @Autowired
    private KirJobRepository repository;


    public void addJobInfo(MKirJob jobInfo) {
        repository.insertJobInfo(jobInfo);
    }


    public void deleteJob(String jobId) {
        repository.deleteJob(jobId);
    }

    public List<MKirJob> getJobList(String analysisType, String userId) {
        return repository.getJobList(analysisType, userId);
    }

}
