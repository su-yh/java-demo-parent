
-- 创建表单属性模板表
CREATE TABLE form_property_template (
	id INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	business_key VARCHAR(64) NOT NULL COMMENT '业务KEY',
	property_type VARCHAR(64) NOT NULL COMMENT '属性类型',
	required CHAR(1) NOT NULL COMMENT '是否为必须',
	var_key VARCHAR(64) NOT NULL COMMENT '变量名',
	description VARCHAR(512) NULL COMMENT '描述信息',
	pattern VARCHAR(255) NULL COMMENT '日期类型的格式化',
	value_custom VARCHAR(4000) NULL COMMENT '自定义类型时的json 数据，总数据不超过400 时存放',
	value_custom_text TEXT NULL COMMENT '自定义类型时的json 数据，总数据超过4000 时存放该字段',

	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

