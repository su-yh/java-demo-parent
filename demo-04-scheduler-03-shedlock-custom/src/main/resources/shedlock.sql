-- 参考博客：https://www.cnblogs.com/nickhan/p/10434604.html
-- 分布式锁，需要添加如下表到数据库中
CREATE TABLE shedlock(
                         name VARCHAR(64),
                         lock_until TIMESTAMP(3) NULL,
                         locked_at TIMESTAMP(3) NULL,
                         locked_by  VARCHAR(255),
                         PRIMARY KEY (name)
)
