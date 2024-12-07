package com.example.demo.service.subscription;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;

import java.util.List;

public interface SubscribeService {
    HostingType getHostingType();

    void subscribe(SubscribeRequestDto subscribeRequestDto);

    List<Subscription> getUserSubscriptions(String username);
}