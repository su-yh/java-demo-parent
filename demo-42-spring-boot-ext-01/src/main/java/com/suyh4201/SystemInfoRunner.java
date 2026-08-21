package com.suyh4201;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TimeZone;

@Component
@Slf4j
@RequiredArgsConstructor
public class SystemInfoRunner implements ApplicationRunner {

    private static final DecimalFormat DF = new DecimalFormat("#.##");

    private final DynamicDataSourceProviderProperties dynamicDataSourceProviderProperties;

    @Override
    public void run(ApplicationArguments args) {
        Map<String, DataSource> dataSourceMaps = dynamicDataSourceProviderProperties.loadDataSources();
        StringBuilder sb = new StringBuilder();

        // 2. 获取数据库连接
        for (Map.Entry<String, DataSource> entry : dataSourceMaps.entrySet()) {
            String dataSourceName = entry.getKey();
            DataSource dataSource = entry.getValue();

            log.info("数据源名称：{}", dataSourceName);
            log.info("数据源类型：{}", dataSource.getClass().getName());

            hikariDataSourceStats(sb, dataSourceName, dataSource);
        }

        // 6. 程序运行时区
        sb.append("\n\n============ 程序运行信息 ============").append("\n\n");
        sb.append("运行时区: ").append(TimeZone.getDefault().getID()).append("\n");

        String jvmTimeZone = System.getProperty("user.timezone");
        sb.append("JVM启动时区参数: ").append(jvmTimeZone != null ? jvmTimeZone : "未指定").append("\n");

        String envTZ = System.getenv("TZ");
        sb.append("环境变量 TZ:").append(envTZ != null ? envTZ : "未设置").append("\n");

        String evnLocalTimeZone = System.getenv("LOCAL_TIMEZONE");
        sb.append("环境变量 LOCAL_TIMEZONE:").append(evnLocalTimeZone != null ? evnLocalTimeZone : "未设置").append("\n");

        printSystemInfo(sb);

        sb.append("\n\n====== 运行环境信息 ======").append("\n");
        sb.append("-- 系统属性 --").append("\n");
        System.getProperties().forEach((key, value) -> {
            sb.append(key).append(" = ").append(value).append("\n");
        });

        sb.append("\n");
        log.info(sb.toString());
    }

    private void hikariDataSourceStats(StringBuilder sb, String dsName, DataSource dataSource) {
        if (!(dataSource instanceof HikariDataSource)) {
            return;
        }

        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        try (Connection connection = hikariDataSource.getConnection()) {

            // 3. 打印数据库基本信息（通过 DatabaseMetaData）
            printDatabaseBasicInfo(sb, dsName, connection);

            // 4. 打印数据库时区信息（通过 SQL 查询）
            printDbTimezoneForPG(sb, connection);

            printDbTimezoneForMySQL(sb, connection);

            // 5. 打印连接池状态（活跃连接数等）
            printConnectionPoolStats(sb, hikariDataSource);
        } catch (Exception e) {
            log.warn("启动获取数据库连接信息异常：{}", e.getMessage());
        }
    }

    private void printSystemInfo(StringBuilder sb) {
        sb.append("===== 系统硬件信息 =====").append("\n");

        // 1. CPU信息
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        sb.append("CPU核心数（逻辑核心）: " + availableProcessors).append("\n");

        // 2. 内存信息（单位：MB）
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024); // JVM总内存
        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);     // JVM最大可用内存
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);   // JVM空闲内存
        sb.append("JVM内存信息:").append("\n");
        sb.append("  总内存: ").append(totalMemory).append(" MB").append("\n");
        sb.append("  最大内存: " + maxMemory + " MB").append("\n");
        sb.append("  空闲内存: " + freeMemory + " MB").append("\n");
        sb.append("  已使用内存: " + (totalMemory - freeMemory) + " MB").append("\n");

        // 3. 系统信息
        sb.append("系统信息:").append("\n");
        sb.append("  操作系统: " + System.getProperty("os.name") + " " + System.getProperty("os.version")).append("\n");
        sb.append("  架构: " + System.getProperty("os.arch")).append("\n");

        // 4. 系统总内存（需要操作系统MXBean）
        OperatingSystemMXBean osMXBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        if (osMXBean instanceof com.sun.management.OperatingSystemMXBean) {
            com.sun.management.OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) osMXBean;
            long totalPhysicalMemory = OsUsageUtils.getTotalPhysicalMemorySize(); // 系统总物理内存
            long freePhysicalMemory = OsUsageUtils.getFreePhysicalMemorySize();   // 系统空闲物理内存
            double usedMemoryPercent = 100.0 * (totalPhysicalMemory - freePhysicalMemory) / totalPhysicalMemory;

            sb.append("系统CPU信息: ").append("\n");
            sb.append("  CPU核心数: ").append(osBean.getAvailableProcessors()).append("\n");
            sb.append("  CPU使用率: ").append(Math.round(osBean.getSystemCpuLoad() * 100.0) / 100).append("%").append("\n");
            sb.append("  进程CPU使用率: ").append(Math.round(osBean.getProcessCpuLoad() * 100 * 100.0) / 100).append("%").append("\n");

            sb.append("系统物理内存信息: ").append("\n");
            sb.append("  总物理内存: " + totalPhysicalMemory + " MB").append("\n");
            sb.append("  空闲物理内存: " + freePhysicalMemory + " MB").append("\n");
            sb.append("  内存使用率: " + DF.format(usedMemoryPercent) + "%").append("\n");
        }
    }

    /**
     * 打印数据库基本信息（版本、产品名称等）
     */
    private static void printDatabaseBasicInfo(StringBuilder sb, String dsName, Connection connection) throws Exception {
        DatabaseMetaData metaData = connection.getMetaData();
        sb.append("\n");
        sb.append("===== 数据库基本信息 =====").append(dsName).append("\n");
        sb.append("数据库产品名称: ").append(metaData.getDatabaseProductName()).append("\n");
        sb.append("数据库产品版本: ").append(metaData.getDatabaseProductVersion()).append("\n");
        sb.append("JDBC 驱动版本: ").append(metaData.getDriverVersion()).append("\n");
        sb.append("数据库 URL: ").append(metaData.getURL()).append("\n");
        sb.append("数据库用户名: ").append(metaData.getUserName()).append("\n");
        sb.append("是否支持事务: ").append(metaData.supportsTransactions()).append("\n");
        sb.append("-------------------------").append("\n");
    }

    /**
     * 打印数据库时区信息
     */
    private static void printDbTimezoneForPG(StringBuilder sb, Connection connection) throws Exception {
        DatabaseMetaData metaData = connection.getMetaData();
        if (!"PostgreSQL".equalsIgnoreCase(metaData.getDatabaseProductName())) {
            return;
        }

        try (Statement statement = connection.createStatement()) {
            // 查询数据库时区配置
            ResultSet rs = statement.executeQuery("SELECT (SELECT boot_val FROM pg_settings WHERE name = 'timezone') AS server_default_timezone,"
                + "  current_setting('timezone') AS current_session_timezone");
            if (rs.next()) {
                String dbTimezone = rs.getString(1);
                String sessionTimezone = rs.getString(2);
                sb.append("===== 数据库时区信息 =====").append("\n");
                sb.append("数据库配置的时区: ").append(dbTimezone).append("\n");
                sb.append("数据库当前会话时区: ").append(sessionTimezone).append("\n");
            }

            // 可选：查询当前时间（验证时区）
            rs = statement.executeQuery("SELECT NOW();");
            if (rs.next()) {
                String currentTime = rs.getString(1);
                sb.append("数据库当前时间（带时区）: ").append(currentTime).append("\n");
            }
            sb.append("-------------------------").append("\n");
        }
    }

    private static void printDbTimezoneForMySQL(StringBuilder sb, Connection conn) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        if (!"MySQL".equalsIgnoreCase(metaData.getDatabaseProductName())) {
            return;
        }

        // 获取并打印数据库全局时区
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT @@global.time_zone")) {
            if (rs.next()) {
                String globalTimezone = rs.getString(1);
                sb.append("数据库全局时区: ").append(globalTimezone).append("\n");
            }
        }

        // 获取并打印当前会话时区
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT @@session.time_zone")) {
            if (rs.next()) {
                String sessionTimezone = rs.getString(1);
                sb.append("当前会话时区: ").append(sessionTimezone).append("\n");
            }
        }

        // 获取并打印 MySQL 服务器的操作系统时区
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT @@system_time_zone")) {
            if (rs.next()) {
                String systemTimezone = rs.getString(1);
                sb.append("MySQL 服务器操作系统时区: ").append(systemTimezone).append("\n");
            }
        }
    }


    /**
     * 打印连接池状态（活跃连接数、空闲连接数等）
     */
    private static void printConnectionPoolStats(StringBuilder sb, HikariDataSource dataSource) {
        sb.append("===== 连接池状态 =====").append("\n");
        sb.append("活跃连接数: ").append(dataSource.getHikariPoolMXBean().getActiveConnections()).append("\n");
        sb.append("空闲连接数: ").append(dataSource.getHikariPoolMXBean().getIdleConnections()).append("\n");
        sb.append("总连接数: ").append(dataSource.getHikariPoolMXBean().getTotalConnections()).append("\n");
        sb.append("等待连接的线程数: ").append(dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection()).append("\n");
        sb.append("最大连接数（配置值）: ").append(dataSource.getMaximumPoolSize()).append("\n");
        sb.append("-------------------------").append("\n");
    }
}
