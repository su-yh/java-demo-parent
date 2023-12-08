
DROP TABLE IF EXISTS sql_date_test;

CREATE TABLE sql_date_test (
    id bigint NOT NULL AUTO_INCREMENT,
    test_sql_date DATE NULL COMMENT '仅存年月日，用来测试它存的值是否受时区的影响',
    local_date DATE NULL COMMENT '仅存年月日，用来测试它存的值是否受时区的影响',
    PRIMARY KEY (id)
) ENGINE=InnoDB;


