package com.example.demo.service.subscription;

import com.example.demo.model.entity.HostingType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SubscribeServiceResolver {
    Map<HostingType,SubscribeService> serviceMap = new HashMap<>();

    public SubscribeServiceResolver(List<SubscribeService> services){
        for (SubscribeService subscribeService: services){
            serviceMap.put(subscribeService.getHostingType(),subscribeService);
        }
    }
    public SubscribeService getService(HostingType hostingType) {
        if(serviceMap.get(hostingType)==null){
            throw new RuntimeException("Subscribe service not found");
        }
       return serviceMap.get(hostingType);
    }
}
