

INSERT INTO m_user (
  user_id,
  user_name,
  affliation,
  password,
  role
)
VALUES (
  'system@co.jp',
  'System Administrator',
  'National Institute of Genetics, Japan',
  '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy',
  'ROLE_ADMIN'
),
(
  'oogasawa@nig.ac.jp', 
  'Osamu Ogasawara',
  'Bioinformation and DDBJ Center, National Institute of Genetics, Japan',
  '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy',
  'ROLE_GENERAL'
),
(
  'user1@co.jp', 
  'user1',
  'National Center for Gobal Health and Medicine, Japan',
  '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy',
  'ROLE_GENERAL'
);


INSERT INTO m_job (
  job_id,
  analysis_type,
  params,
  state,
  user_id
)
VALUES (
  '20210509-145553-668966',
  'SNP',
  'dummy!!',
  'Running',
  'user1@co.jp'
);

INSERT INTO m_job (
  job_id,
  analysis_type,
  params,
  state,
  user_id
)
VALUES (
  '20210508-105553-950832',
  'HLA',
  'dummy!!',
  'Running',
  'user1@co.jp'
);

INSERT INTO m_job (
  job_id,
  analysis_type,
  params,
  state,
  user_id
)
VALUES (
  '20210507-125553-275235',
  'KIR',
  'dummy!!',
  'Running',
  'user1@co.jp'
);

INSERT INTO m_job (
  job_id,
  analysis_type,
  params,
  state,
  user_id
)
VALUES (
  '20210506-095553-555310',
  'SNP',
  'dummy!!',
  'Running',
  'user1@co.jp'
);

INSERT INTO m_job (
  job_id,
  analysis_type,
  params,
  state,
  user_id
)
VALUES (
  '20210505-085553-943196',
  'HLA',
  'dummy!!',
  'Running',
'user1@co.jp'
);
















