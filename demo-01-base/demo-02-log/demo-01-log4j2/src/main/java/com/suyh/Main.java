package com.suyh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "conf/log4j2.xml");
        // 对应log4j2.xml 配置文件中使用的 ${sys:log4j2.dir:-demo-log}
        System.setProperty("log4j2.dir", "demo-01");
        System.setProperty("rollingFile", "main");
    }

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Logger logSp = LoggerFactory.getLogger("spLogger");

    public static void main(String[] args) {
        logger.info("main starting...");
        for (long i = 1; i < 10; i++) {
            logger.info("i: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
        }

        logger.info("logger sp begin");
        for (int i = 0; i < 5; i++) {
            logSp.info("logger get name. i: " + i);
        }
        logger.info("logger sp finished");

        logger.info("main finished.");
    }
}
