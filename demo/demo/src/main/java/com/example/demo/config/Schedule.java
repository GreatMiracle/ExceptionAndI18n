package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
public class Schedule {
    private static final Logger logger = LoggerFactory.getLogger(Schedule.class);

    public void scheduleTaskWithFixedRate() {
    }

    public void scheduleTaskWithFixedDelay() {
    }

    public void scheduleTaskWithInitialDelay() {
    }

    public void scheduleTaskWithCronExpression() {
    }
}
