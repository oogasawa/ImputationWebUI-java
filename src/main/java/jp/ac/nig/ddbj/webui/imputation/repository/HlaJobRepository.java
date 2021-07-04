package jp.ac.nig.ddbj.webui.imputation.repository;

import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.nig.ddbj.webui.imputation.domain.job.model.MHlaJob;


@Repository
public class HlaJobRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    public int insertJobInfo(MHlaJob jobInfo) {
        String sqlStr = "INSERT INTO m_job "
            + "(job_id, analysis_type, params, state, user_id)"
            + " VALUES "
            + "(?, ?, ?, ?, ?)";

        // Gson gson = new Gson();
        // String paramsJson = gson.toJson(jobInfo.getParams());

        return jdbcTemplate.update(sqlStr,
                                   jobInfo.getJobId(),
                                   jobInfo.getAnalysisType(),
                                   jobInfo.getParams(),
                                   jobInfo.getState(),
                                   jobInfo.getUserId()
                                   );


    }



    @Transactional
    public List<MHlaJob> getJobList(String analysisType, String userId) {

        String sqlStr = "SELECT "
            + "job_id, analysis_type, params, state, user_id "
            + "FROM "
            + "m_job "
            + "WHERE "
            + "analysis_type=? AND user_id=?";


        return jdbcTemplate.query(sqlStr,
                                  new Object[]{analysisType, userId},
                                  (rs, rowNum) -> {
                                      MHlaJob jobInfo = MHlaJob.builder()
                                          .jobId(rs.getString("job_id"))
                                          .analysisType(rs.getString("analysis_type"))
                                          .params(this.modifyJson(rs.getString("params")))
                                          .state(rs.getString("state"))
                                          .userId(rs.getString("user_id")).build();
                                      return jobInfo;
                                  }
                                  );
    }


    public String modifyJson(String json) {
        return json.replaceAll("\",\"", "\", \"");
    }


    @Transactional
    public int deleteJob(String jobId) {
        String sqlStr = "DELETE "
            + "FROM m_job "
            + "WHERE job_id=?";

        return jdbcTemplate.update(sqlStr, jobId);

    }



}
