
DROP TABLE IF EXISTS test_long_id;
-- Long 类型的id 值
CREATE TABLE test_long_id (
    id bigint,
    nick_name varchar(128),
    age integer,
    delete_flag    BIGINT NULL DEFAULT 0 COMMENT '删除标记，0：未删除，非0：已删除(一般记时间戳，以纳秒为单位)',
    PRIMARY KEY (id)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS test_uuid;
-- 字符串类型的id 值
CREATE TABLE test_uuid (
    id VARCHAR(64),
    nick_name varchar(128),
    age integer,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

