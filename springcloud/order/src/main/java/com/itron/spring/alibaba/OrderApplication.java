package com.itron.spring.alibaba;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@EnableFeignClients
@SpringBootApplication
public class OrderApplication {
    static Logger logger =  Logger.getLogger("OrderApplication");
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OrderApplication.class,args);
        String name = applicationContext.getEnvironment().getProperty("name");
        String age = applicationContext.getEnvironment().getProperty("age");
        String aaa = applicationContext.getEnvironment().getProperty("aaa");
        logger.info("name:" + name + " age:" + age + " aaa:" + aaa);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }
}
