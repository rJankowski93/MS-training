package com.org.rjankowski.ms.eurekascaling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaScalingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaScalingApplication.class, args);
    }

}
