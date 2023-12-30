
DROP TABLE IF EXISTS test_long_id;
-- Long 类型的id 值
CREATE TABLE test_long_id (
    id bigint,
    nick_name varchar(128),
    age integer,
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

