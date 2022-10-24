package com.itron.spring.alibaba.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StockAppliction {
    public static void main(String[] args) {

        SpringApplication.run(StockAppliction.class,args);
    }
}
