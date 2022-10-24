package com.itron.spring.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.itron.spring.alibaba.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
@RequestMapping("/order")
public class OrderController {

    Logger logger = Logger.getLogger("OrderController");
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IStockService iStockService;

    @RequestMapping("/add")
    public String add(){
        logger.info("下单成功");
//        String msg = restTemplate.getForObject("http://stockservice/stock/reduct",String.class);
        String msg = iStockService.reduct();
        return "Hello world " + msg;
    }


    @RequestMapping("/flow")
    @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flow(){
        return "正常访问";
    }

    public String flowBlockHandler(BlockException blockException){
        return "被流控了";
    }
}
