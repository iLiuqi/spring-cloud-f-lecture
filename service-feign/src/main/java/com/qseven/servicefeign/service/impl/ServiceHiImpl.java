package com.qseven.servicefeign.service.impl;

import com.qseven.servicefeign.service.IServiceHi;
import org.springframework.stereotype.Component;

@Component
public class ServiceHiImpl implements IServiceHi {

    @Override
    public String sayHi(String name) {
        return "sorry " + name;
    }

}
