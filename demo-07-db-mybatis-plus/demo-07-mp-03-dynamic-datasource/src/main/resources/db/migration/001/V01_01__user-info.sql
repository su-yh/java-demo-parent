
CREATE TABLE user_info  (
  uuid varchar(64) NOT NULL,
  user_name varchar(64) NOT NULL COMMENT '用户名称',
  phone varchar(20) NULL DEFAULT NULL COMMENT '手机号',
  email varchar(64) NULL DEFAULT NULL COMMENT '邮箱地址',
  phone_verify_code varchar(16) NULL DEFAULT NULL COMMENT '手机验证码',
  phone_verify_code_expired datetime NULL DEFAULT NULL COMMENT '手机验证码到期时间',
  email_verify_code varchar(16) NULL DEFAULT NULL COMMENT '邮箱验证码',
  email_verify_code_expired datetime NULL DEFAULT NULL COMMENT '邮箱验证码到期时间',
  create_date datetime(3) NOT NULL COMMENT '创建时间',
  create_by varchar(64) NOT NULL COMMENT '创建人',
  update_date datetime(3) NOT NULL COMMENT '修改时间',
  update_by varchar(64) NOT NULL COMMENT '修改人',
  PRIMARY KEY (uuid) USING BTREE
) ENGINE = InnoDB;





