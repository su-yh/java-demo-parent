package com.suyh4201;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class OsUsageUtils {

    /**
     * 当前进程的CPU使用率
     */
    public static double getProcessCpuLoad() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        // CPU 使用率（当前进程的）
        return osBean.getProcessCpuLoad() * 100;
    }

    /**
     * 系统总CPU使用率
     */
    public static double getSystemCpuLoad() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        // CPU 使用率（整个系统的）
        return osBean.getSystemCpuLoad() * 100;
    }

    /**
     * 系统总物理内存
     */
    public static long getTotalPhysicalMemorySize() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getTotalPhysicalMemorySize() / (1024 * 1024);
    }

    /**
     * 系统空闲物理内存
     */
    public static long getFreePhysicalMemorySize() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getFreePhysicalMemorySize() / (1024 * 1024);
    }

    /**
     * 获取内存使用信息
     */
    public static String getOsMemoryInfo() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return "系统内存信息: " + osBean.getTotalPhysicalMemorySize() / (1024 * 1024) + " MB"
            + "  空闲内存: " + osBean.getFreePhysicalMemorySize() / (1024 * 1024) + " MB"
            + "  已使用内存: " + (osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize()) / (1024 * 1024) + " MB"
            + "  内存使用率: " + osBean.getSystemCpuLoad() * 100 + "%"
            ;
    }

    /**
     * 获取CPU使用信息
     */
    public static String getOsCpuInfo() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return "系统CPU信息: " + osBean.getAvailableProcessors() + " 核"
            + "  CPU使用率: " + Math.round(osBean.getSystemCpuLoad() * 100 * 100.0) / 100 + "%"
            + "  进程CPU使用率: " + Math.round(osBean.getProcessCpuLoad() * 100 * 100.0) / 100 + "%"
            ;
    }

    /**
     * 获取系统负载信息
     */
    public static String getOsLoadInfo() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        StringBuilder sb = new StringBuilder();

        double usedMemoryRate = (double) ((osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize()) * 100) / osBean.getTotalPhysicalMemorySize();

        sb.append("系统CPU信息: ").append(osBean.getAvailableProcessors()).append(" 核")
            .append("  系统CPU使用率: ").append(Math.round(osBean.getSystemCpuLoad() * 100 * 100.0) / 100).append("%")
            .append("  当前进程CPU使用率: ").append(Math.round(osBean.getProcessCpuLoad() * 100 * 100.0) / 100).append("%")
            .append("  系统负载: ").append(Math.round(osBean.getSystemLoadAverage() * 100.0) / 100).append("; ");

        // 2. 内存信息（单位：MB）
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024); // JVM总内存
        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);     // JVM最大可用内存
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);   // JVM空闲内存
        sb.append("JVM内存信息:")
            .append("  总内存: ").append(totalMemory).append(" MB")
            .append("  最大内存: " + maxMemory + " MB")
            .append("  空闲内存: " + freeMemory + " MB")
            .append("  已使用内存: " + (totalMemory - freeMemory) + " MB");

        sb.append("系统内存信息: ")
            .append("  总内存: ").append(osBean.getTotalPhysicalMemorySize() / (1024 * 1024)).append(" MB")
            .append("  空闲内存: ").append(osBean.getFreePhysicalMemorySize() / (1024 * 1024)).append(" MB")
            .append("  已使用内存: ").append((osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize()) / (1024 * 1024)).append(" MB")
            .append("  内存使用率: ").append(Math.round(usedMemoryRate * 100.0) / 100).append("%")
        ;

        return sb.toString();
    }

}
