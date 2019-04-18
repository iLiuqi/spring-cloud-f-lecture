package com.qseven.serviceribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RibbonRuleConfiguration {

    /*
     * Ribbon负载均衡算法，通过此项配置可以设置为随机，默认是轮询
     * */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

}
