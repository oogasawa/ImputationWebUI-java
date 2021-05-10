

INSERT INTO user (
  id,
  name_j,
  name_e,
  affliation,
  password,
  role
)
VALUES (
  'system@co.jp', 
  'システム管理者',
  'System Administrator',
  'National Institute of Genetics, Japan',
  '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy',
  'ROLE_ADMIN'
),
(
  'oogasawa@nig.ac.jp', 
  '小笠原　理',
  'Osamu Ogasawara',
  'Bioinformation and DDBJ Center, National Institute of Genetics, Japan',
  '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy',
  'ROLE_GENERAL'
),
(
  'user1@co.jp', 
  'ユーザー1',
  'user1',
  'National Center for Gobal Health and Medicine, Japan',
  '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy',
  'ROLE_GENERAL'
);


INSERT INTO job (
  id,
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
),
VALUES (
  '20210508-105553-950832',
  'HLA',
  'dummy!!',
  'Running',
  'user1@co.jp'
),
VALUES (
  '20210507-125553-275235',
  'KIR',
  'dummy!!',
  'Running',
  'user1@co.jp'
),
VALUES (
  '20210506-095553-555310',
  'SNP',
  'dummy!!',
  'Running',
  'user1@co.jp'
),
VALUES (
  '20210505-085553-943196',
  'HLA',
  'dummy!!',
  'Running'
);




















INSERT INTO employee (id, name, age)
VALUES('1', 'Tom', 30);

/* ユーザーマスタ */
INSERT INTO m_user (
    user_id
  , password
  , user_name
  , birthday
  , age
  , gender
  , department_id
  , role
) VALUES
('system@co.jp', '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy', 'システム管理者', '2000-01-01', 21, 1, 1, 'ROLE_ADMIN')
, ('user@co.jp', '$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy', 'ユーザー1', '2000-01-01', 21, 2, 2, 'ROLE_GENERAL')
;

/* 部署マスタ */
INSERT INTO m_department (
    department_id
  , department_name
) VALUES
(1, '国立遺伝学研究所 システム管理部')
, (2, '国立国際医療研究センター')
;

/* 給料テーブル */
INSERT INTO t_salary (
    user_id
  , year_month
  , salary
) VALUES
('user@co.jp', '2020/11', 280000)
, ('user@co.jp', '2020/12', 290000)
, ('user@co.jp', '2021/01', 300000)
;