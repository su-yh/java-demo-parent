package com.suyh4602.init;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Component
@Slf4j
public class ApplicationInit implements ApplicationRunner {
    @Resource
    private HikariDataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Runnable runnable = () -> {
            while (true) {
                HikariPoolMXBean pool = dataSource.getHikariPoolMXBean();
                int activeConnections = pool.getActiveConnections();
                int totalConnections = pool.getTotalConnections();
                int idleConnections = pool.getIdleConnections();
                int threadsAwaitingConnection = pool.getThreadsAwaitingConnection();
                log.info("thread runnable. totalConnections: {}, activeConnections: {}, idleConnections: {}, threadsAwaitingConnection: {}",
                        totalConnections, activeConnections, idleConnections, threadsAwaitingConnection);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                }
            }
        };
        new Thread(runnable).start();

        func01(false);
        func01(true);
    }

    public void func01(boolean isRelease) {
        Consumer<Integer> consumer = (index) -> {
            try {
                Connection connection = dataSource.getConnection();

                TimeUnit.SECONDS.sleep(10 + index);

                if (isRelease) {
                    connection.close();
                }
            } catch (SQLException | InterruptedException exception) {
                log.error("func01 exception", exception);
            }
        };
        for (int i = 0; i < 10; i++) {
            Integer index = i;
            new Thread(() -> consumer.accept(index)).start();
        }
    }
}
