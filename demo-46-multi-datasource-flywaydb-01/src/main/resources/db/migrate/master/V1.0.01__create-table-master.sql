/*Table structure for table `test_user` */

DROP TABLE IF EXISTS `test_user`;

CREATE TABLE `test_user`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `name`            varchar(32) NOT NULL COMMENT '姓名',
    `phone`           varchar(32)          DEFAULT NULL COMMENT '手机号',
    `title`           varchar(32)          DEFAULT NULL COMMENT '职称职别',
    `email`           varchar(128)         DEFAULT NULL COMMENT '邮箱',
    `gender`          varchar(32)          DEFAULT NULL COMMENT '性别',
    `date_of_birth`   date                 DEFAULT NULL COMMENT '出生时间',
    `deleted`         tinyint(1) DEFAULT '0' COMMENT '1:已删除,0:未删除',
    `sys_create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `sys_create_user` varchar(255)         DEFAULT NULL,
    `sys_update_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sys_update_user` varchar(255)         DEFAULT NULL,
    `record_version`  bigint(20) DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7013 DEFAULT CHARSET=utf8;

/*Data for the table `test_user` */

insert into `test_user`(`id`, `name`, `phone`, `title`, `email`, `gender`, `date_of_birth`, `deleted`,
                        `sys_create_user`, `sys_update_user`, `record_version`)
values (3497, '王**', '13800000000', 'test', 'test1@qq.com', '1', '1958-07-01', 0, 'admin', 'admin', 0),
       (3500, '林**', '13800000000', NULL, NULL, '1', NULL, 0, 'admin', 'admin', 0),
       (3508, '高**', '13800000000', 'test', NULL, '2', '1978-05-20', 0, 'admin', 'admin', 0),
       (7005, 'test4', NULL, 'test4', 'test4@qq.com', '1', NULL, 0, NULL, 'admin', 0),
       (7006, 'test5', NULL, 'new title', 'test5@qq.com', '1', NULL, 0, NULL, 'admin', 0),
       (7007, 'test6', NULL, NULL, 'test6@qq.com', NULL, NULL, 1, NULL, NULL, 0);

