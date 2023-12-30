DROP TABLE IF EXISTS enum_list;

CREATE TABLE enum_list (
    id bigint NOT NULL AUTO_INCREMENT,
    status_list varchar(4000) DEFAULT NULL COMMENT '使用jackson 序列化与反序列化枚举 List 数据。',
    flag varchar(16) DEFAULT NULL COMMENT 'Boolean 类型',
    PRIMARY KEY (id)
) ENGINE=InnoDB;


