

CREATE TABLE personal_info (
  uuid varchar(64) NOT NULL,

  country varchar(256) NULL COMMENT '国家',
  city varchar(256) NULL COMMENT '城市',
  work_experience varchar(4000) NULL COMMENT '工作经验',
  about_me varchar(4000) NULL COMMENT '关于我的',

  discord varchar(1024) NULL COMMENT '',
  hugging_face varchar(1024) NULL COMMENT '',
  github varchar(1024) NULL COMMENT '',
  reddit varchar(1024) NULL COMMENT '',

  create_date datetime(3) NOT NULL COMMENT '创建时间',
  create_by varchar(64) NOT NULL COMMENT '创建人',
  update_date datetime(3) NOT NULL COMMENT '修改时间',
  update_by varchar(64) NOT NULL COMMENT '修改人',
  PRIMARY KEY (uuid) USING BTREE
) ENGINE = InnoDB;





