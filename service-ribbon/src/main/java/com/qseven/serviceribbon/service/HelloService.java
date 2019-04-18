package com.qseven.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HelloService {

    private static Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    private RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    public HelloService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    /*
     * 消费 service-hi 服务中的 /hi 接口
     * */

    @HystrixCommand(fallbackMethod = "hiError")     // 断路时的降级处理
    public String hiService(String name) {
        logger.info("根据服务名寻找服务...");
        final List<String> services = discoveryClient.getServices();
        for (String serviceId : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance instance : instances) {
                logger.info(instance.getUri() + "/" + serviceId + " - " + instance.getServiceId());
            }
        }
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }

}
