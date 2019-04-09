package com.qseven.servicefeign.service;

import com.qseven.servicefeign.service.impl.ServiceHiImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hi", fallback = ServiceHiImpl.class)
public interface IServiceHi {

    @GetMapping("/hi")
    String sayHi(@RequestParam(value = "name") String name);

}
