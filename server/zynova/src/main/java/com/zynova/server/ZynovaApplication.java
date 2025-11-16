package com.zynova.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.zynova.server"})
@EnableJpaAuditing
public class ZynovaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZynovaApplication.class, args);
    }
}
