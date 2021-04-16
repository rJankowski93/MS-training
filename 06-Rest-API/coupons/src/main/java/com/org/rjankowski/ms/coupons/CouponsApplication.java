package com.org.rjankowski.ms.coupons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CouponsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponsApplication.class, args);
    }

}
