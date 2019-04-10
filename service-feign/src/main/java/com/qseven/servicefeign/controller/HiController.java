package com.qseven.servicefeign.controller;

import com.qseven.servicefeign.service.IServiceHi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HiController.class);

    @Autowired
    private IServiceHi serviceHi;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        LOGGER.info("Service Consumer :: service-feign");
        return serviceHi.sayHi(name);
    }

}
