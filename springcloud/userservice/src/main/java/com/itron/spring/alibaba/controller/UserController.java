package com.itron.spring.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = Logger.getLogger("UserController");
    @RequestMapping("/add")
    @SentinelResource(value = "useradd", blockHandler = "flowBlockHandler")
    public String add(){
        logger.info("下单成功");
        return "Hello world";
    }

    public String flowBlockHandler(BlockException blockException){
        return "被流控了";
    }
}
