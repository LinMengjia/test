package com.example.demo.util;

import com.example.demo.controller.ScheduleTestController;
import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ScheduleTestController.class);

    }
}
