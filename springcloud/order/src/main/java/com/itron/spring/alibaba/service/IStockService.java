package com.itron.spring.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "stockservice", fallback = StockFallbackService.class)
public interface IStockService {

    @GetMapping("/stock/reduct")
    String reduct();
}
