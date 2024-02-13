package com.suyh1101.sequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Enumeration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * 基于Twitter的Snowflake算法实现分布式高效有序ID生产黑科技(sequence)——升级版Snowflake
 *
 * <br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * <br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * <br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下START_TIME属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * <br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位dataCenterId和5位workerId<br>
 * <br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * <br>
 * <br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 * <p>
 * <p>
 * 特性：
 * 1.支持自定义允许时间回拨的范围<p>
 * 2.解决跨毫秒起始值每次为0开始的情况（避免末尾必定为偶数，而不便于取余使用问题）<p>
 * 3.解决高并发场景中获取时间戳性能问题<p>
 * 4.支撑根据IP末尾数据作为workerId
 * 5.时间回拨方案思考：1024个节点中分配10个点作为时间回拨序号（连续10次时间回拨的概率较小）
 * <p>
 * 常见问题:
 * 1.时间回拨问题
 * 2.机器id的分配和回收问题
 * 3.机器id的上限问题
 *
 * @author lry
 * @version 3.0
 */
public class Sequence {

    private static final Logger log = LoggerFactory.getLogger(Sequence.class);

    /**
     * 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
     * suyh - 该值需要项目首次运行时就必须固定，后续就不可变更，它的作用是提供时间戳的初始值，使得基于时间戳的部分不会长度溢出。
     */
    private static final long TW_EPOCH;

    static {
        LocalDate localDate = LocalDate.of(2020, 1, 1);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        TW_EPOCH = localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * 5位的机房id
     */
    private static final long DATACENTER_ID_BITS = 5L;
    /**
     * 5位的机器id
     */
    private static final long WORKER_ID_BITS = 5L;
    /**
     * 每毫秒内产生的id数: 2的12次方个
     */
    private static final long SEQUENCE_BITS = 12L;

    // suyh - 最大的dataCenterId 的值，即：低5 位全为1， 0001 1111  ==>   0X1F
    protected static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);
    // suyh - 最大的workerId 的值，低5 位全为1， 0001 1111 ==> 0X1F
    protected static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    // suyh - 最大的序列号的值。
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    // suyh - workerId 所在二进制位的位置，左移位数。
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    // suyh - datacenterId 所在二进制位的位置，左移位数。
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间戳左移动位
     * suyh - 时间戳的二进制位置
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /**
     * 所属机房id
     */
    private final long datacenterId;
    /**
     * 所属机器id
     */
    private final long workerId;
    /**
     * 并发控制序列
     */
    private long sequence = 0L;

    /**
     * 上次生产 ID 时间戳
     */
    private long lastTimestamp = -1L;

    private static volatile InetAddress LOCAL_ADDRESS = null;
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    public Sequence() {
        this.datacenterId = getDatacenterId();
        this.workerId = getMaxWorkerId(datacenterId);
    }

    /**
     * 有参构造器
     *
     * @param workerId     工作机器 ID
     * @param datacenterId 序列号
     */
    public Sequence(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("Datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // 把workerId 和datacenterId 合并在一起
    public Sequence(long instanceId) {
        long instanceIdBits = WORKER_ID_BITS + DATACENTER_ID_BITS;
        long instanceIdMax = ~(-1L << instanceIdBits);
        if (instanceId > instanceIdMax) {
            throw new IllegalArgumentException(String.format("Instance Id can't be greater than %d or less than 0", instanceIdMax));
        }

        this.workerId = instanceId >> DATACENTER_ID_BITS;
        this.datacenterId = instanceId & MAX_DATACENTER_ID;
    }

    /**
     * 基于网卡MAC地址计算余数作为数据中心
     * <p>
     * 可自定扩展
     */
    protected long getDatacenterId() {
        long id = 0L;
        try {
            NetworkInterface network = NetworkInterface.getByInetAddress(getLocalAddress());
            if (null == network) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null != mac) {
                    id = ((0x000000FF & (long) mac[mac.length - 2]) | (0x0000FF00 & (((long) mac[mac.length - 1]) << 8))) >> 6;
                    id = id % (MAX_DATACENTER_ID + 1);
                }
            }
        } catch (Exception e) {
            log.warn(" getDatacenterId: " + e.getMessage());
        }

        return id;
    }

    /**
     * 基于 MAC + PID 的 hashcode 获取16个低位
     * <p>
     * 可自定扩展
     */
    protected long getMaxWorkerId(long datacenterId) {
        StringBuilder mpId = new StringBuilder();
        mpId.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (name != null && name.length() > 0) {
            // GET jvmPid
            mpId.append(name.split("@")[0]);
        }

        // MAC + PID 的 hashcode 获取16个低位
        return (mpId.toString().hashCode() & 0xffff) % (MAX_WORKER_ID + 1);
    }

    /**
     * 获取下一个 ID
     *
     * @return next id
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        // 闰秒
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            if (offset <= 5) {
                try {
                    // 休眠双倍差值后重新获取，再次校验
                    wait(offset << 1);
                    timestamp = timeGen();
                    if (timestamp < lastTimestamp) {
                        throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
            }
        }

        if (lastTimestamp == timestamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                // 同一毫秒的序列数已经达到最大
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 不同毫秒内，序列号置为 [1, 10) 随机数，作为序列号的起始值
            sequence = ThreadLocalRandom.current().nextLong(1, 10);
        }

        lastTimestamp = timestamp;

        // 时间戳部分 | 数据中心部分 | 机器标识部分 | 序列号部分
        return ((timestamp - TW_EPOCH) << TIMESTAMP_LEFT_SHIFT)  // 时间戳 所在位置
                | (datacenterId << DATACENTER_ID_SHIFT)  // datacenter id 所在位置
                | (workerId << WORKER_ID_SHIFT)   // worker id 所在位置
                | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            Thread.yield();
            timestamp = timeGen();
        }

        return timestamp;
    }

    protected long timeGen() {
        // 个人感觉这个在实际环境中意义不大，所以直接取系统毫秒值，因为绝大多数情况id 生成并不会有高并发的情况。
        // 而使用这个优化的方案反而需要每毫秒执行一次定时任务，不清楚这个会不会出现一定的误差，可能出现误差也没关系吧。最终一致性结果。
        // return SystemClock.INSTANCE.currentTimeMillis();
        return System.currentTimeMillis();
    }

    /**
     * Find first valid IP from local network card
     *
     * @return first valid local IP
     */
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }

        LOCAL_ADDRESS = getLocalAddress0();
        return LOCAL_ADDRESS;
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            log.warn("Failed to retrieving ip address, " + e.getMessage(), e);
        }

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        while (addresses.hasMoreElements()) {
                            try {
                                InetAddress address = addresses.nextElement();
                                if (isValidAddress(address)) {
                                    return address;
                                }
                            } catch (Throwable e) {
                                log.warn("Failed to retrieving ip address, " + e.getMessage(), e);
                            }
                        }
                    } catch (Throwable e) {
                        log.warn("Failed to retrieving ip address, " + e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            log.warn("Failed to retrieving ip address, " + e.getMessage(), e);
        }

        log.error("Could not get local host ip address, will use 127.0.0.1 instead.");
        return localAddress;
    }

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }

        String name = address.getHostAddress();
        return (name != null && !"0.0.0.0".equals(name) && !"127.0.0.1".equals(name) && IP_PATTERN.matcher(name).matches());
    }

}
