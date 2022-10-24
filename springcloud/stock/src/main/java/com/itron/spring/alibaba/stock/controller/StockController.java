package com.itron.spring.alibaba.stock.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/stock")
public class StockController {

    java.util.logging.Logger logger = Logger.getLogger("StockController");
    @RequestMapping("/reduct")
    public String reduct(){
        logger.info("扣减库存");
        return "扣减库存";
    }
}
