
/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY
  , user_name VARCHAR(50)
  , affliation VARCHAR(250)
  , password VARCHAR(100)
  , role VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS m_job (
  job_id VARCHAR(250) PRIMARY KEY
  , analysis_type VARCHAR(50)
  , params VARCHAR(10000)
  , state VARCHAR(50)
  , user_id VARCHAR(50)
  );
