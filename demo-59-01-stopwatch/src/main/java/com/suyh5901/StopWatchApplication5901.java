package com.suyh5901;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

@SpringBootApplication
public class StopWatchApplication5901 {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("测试");
        stopWatch.start("任務一");
        Thread.sleep(3000);
        stopWatch.stop();

        stopWatch.start("任務二");
        Thread.sleep(3000);
        stopWatch.stop();

        Thread.sleep(2000);


        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.shortSummary());
    }
}
