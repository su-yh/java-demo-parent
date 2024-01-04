DROP TABLE jd_user;    
CREATE TABLE jd_user (
        id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
        username VARCHAR(256) NULL , 
        password VARCHAR(256) NULL , 
        role VARCHAR(256) NULL
    ) ENGINE = INNODB ;
