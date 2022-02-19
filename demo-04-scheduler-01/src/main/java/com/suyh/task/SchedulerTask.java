package com.suyh.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务，这些定时任务是在同一个线程中运行的
 * 如果添加了线程池的配置就会在线程池中运行
 */
@Component
public class SchedulerTask {
    private Logger logger = LoggerFactory.getLogger(SchedulerTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private int count = 0;

    /**
     * cron一共有7位，但是最后一位是年，可以留空，所以我们可以写6位：
     *
     * 第一位，表示秒，取值0-59
     * 第二位，表示分，取值0-59
     * 第三位，表示小时，取值0-23
     * 第四位，日(具体指代每月哪一天)，取值1-31
     * 第五位，月份，取值1-12
     * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
     *         另外：1表示星期天，2表示星期一。
     * 第7为，年份，可以留空，取值1970-2099
     * cron中，还有一些特殊的符号，含义如下：
     * (*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
     * (?)问号：问号只能出现在日期和星期这两个位置。
     * (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
     * (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
     * (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60
     *      另：(*)/y，等同于0/y
     *      如：(*)/7 会在每分钟的 7秒的倍数时执行，在某一分钟的 10:01:56 执行一次后，下一次将在10:02:00 再执行一次。
     *          这个与上一次之间没有间隔到7秒时间
     *
     * 示例
     * 秒   分     时  日  月  周几  年份(可空)
     *  0   0     3    *   *   ?     每天3点执行
     *  0   5     3    *   *   ?     每天3点5分执行
     *  0   5     3    ?   *   *     每天3点5分执行，与上面作用相同
     *  0   5/10  3    *   *   ?  每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行
     *  0   10    3    ?   *   1    每周星期天，3点10分 执行，注：1表示星期天
     *  0   10    3    ?   *   1#3  每个月的第三个星期，星期天 执行，#号只能出现在星期的位置
     *
     * 当前这个方法是每个分钟的秒值是7 的倍数时执行。且0秒时也会执行
     */
    @Scheduled(cron = "*/7 * * * * ?")
    private void process() {
        logger.info("每7秒一次，this is scheduler task runing  " + (count++));
    }

    // 这个似乎也是每6 秒一次

    private static int s_count = 0;
    /**
     * fixedDelay和fixedRate，单位是毫秒，它们的区别就是：
     * fixedRate: 是每多次分钟一次，不论你业务执行花费了多少时间。我都是1分钟执行1次，
     *      如果执行的任务实际使用时间超过了定时时间。
     *      则会在实际时间完成之后马上进入下一次定时任务。
     *      就算是使用线程池也是一样的
     * fixedDelay: 是当任务执行完毕后1分钟在执行。
     *      即：两次任务之间会休息1分钟。
     *
     * initialDelay: 指定在系统启动初始化一定时间之后再开始运行
     */
    @Scheduled(initialDelay = 10000, fixedRate = 6000)
    public void reportCurrentTime() {
        int c = ++s_count;
        logger.info("c: " + c);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("c: " + c + ", 现在时间：" + dateFormat.format(new Date()));
    }
}
