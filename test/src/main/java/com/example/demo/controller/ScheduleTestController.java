package com.example.demo.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTestController {
    public ScheduleTestController() {
        Runnable runnable = new Runnable() {
            int i = 1;

            public void run() {
                if (i < 5) {
                    System.out.println(i);
                    i++;
                }
            }

            ;
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 1表示时间单位的数值 TimeUnit.SECONDS  延时单位为秒
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }
}
