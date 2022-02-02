package com.suyh.demo5301;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 处理zookeeper 监听问题，前提是每次使用ZK 中最新的数据来进行数据更新。
 */
public class ZooKeeperWatch {
    // 最后一次注册时间
    private static final AtomicLong lastRegisterTime = new AtomicLong(0);
    // 最后一次执行时间
    private static final AtomicLong lastExecutedTime = new AtomicLong(0);

    public void init() {
        registerZookeeper(System.currentTimeMillis());
    }

    // 监听通知
    private void notifySomething() {
        long registerTime = System.currentTimeMillis();
        registerZookeeper(registerTime);

        Timer timer = new Timer(true);
        TimerSomething something = new TimerSomething(registerTime);
        timer.schedule(something, 500); // 500 的意义在于：在这500 ms 内注册要成功，否则就有问题。
    }

    private void registerZookeeper(long registerTime) {
        lastRegisterTime.set(registerTime);
        // TODO: 向zookeeper 注册监听
        // ...

    }

    private static class TimerSomething extends TimerTask {
        // 当前定时任务的注册时间，用来处理，当前定时任务是否需要执行。
        private final long registerTime;
        public TimerSomething(long registerTime) {
            this.registerTime = registerTime;
        }

        @Override
        public void run() {
            long curTime = System.currentTimeMillis();
            if (registerTime != lastRegisterTime.get() && curTime - lastExecutedTime.get() > 10_000) {
                return;
            }

            // do something
            // TODO: 业务处理
            // 拉取ZK中最新的数据，这样可以保证同时有监听的存在，不会漏掉任何一个数据的变更。
            //  ...

            lastExecutedTime.set(curTime);
        }
    }
}
