package com.drinktea;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
@EnableCaching
//@EnableScheduling
public class DrinkTeaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrinkTeaApplication.class, args);
        log.info("Server started");
    }
}
