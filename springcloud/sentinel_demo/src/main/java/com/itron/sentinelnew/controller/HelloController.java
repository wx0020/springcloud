package com.itron.sentinelnew.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.itron.sentinelnew.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class HelloController {
    private static final String RESOURCE_NAME = "hello";
    private static final String USER_RESOURCE_NAME = "user";
    private static final String DEGRADE_RESOURCE_NAME = "degrade";

    // 进行流控 sentinal
    @RequestMapping(value = "/hello")
    public String hello(){
        Entry entry = null;
        try {
            // 定义流控 资源名称 sentinel
            entry = SphU.entry(RESOURCE_NAME);
            String str = "hello world";
            log.info("====" + str + "====");
            return str;
        }catch (BlockException e1){
            log.info("blck!");
            return "被流控了！";
        }catch (Exception ex){
            Tracer.traceEntry(ex,entry);
        }finally {
            if (entry!=null){
                entry.exit();
            }
        }
        return null;
    }

    /**
     * 初始化发放
     */
    @PostConstruct
    private static void initFlowRules(){
        // 流控规则
        List<FlowRule> rules = new ArrayList<>();
        // 流控
        FlowRule rule = new FlowRule();
        // 设置受保护资源
        rule.setRefResource(RESOURCE_NAME);
        // 设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护的资源阈值
        rule.setCount(1);
        rules.add(rule);
        // 流控管理器 加载
        FlowRuleManager.loadRules(rules);
    }


    /**
     *
     * @param id
     * @return
     */
    @SentinelResource(value = USER_RESOURCE_NAME, blockHandler = "blockHandlerForGetUser")
    @RequestMapping(value = "/user")
    public User getUser(String id){
        return new User("xushu1");
    }

    /**
     *  流控处理方法  public
     *  返回值 和原方法报错一致
     *  可以在参数最后增加BlockException
     * @param id
     * @param ex
     * @return
     */
    public User blockHandlerForGetUser(String id, BlockException ex){
        ex.printStackTrace();
        return new User("流控");
    }
}
