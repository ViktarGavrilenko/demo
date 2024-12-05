package com.example.demo.service;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;

import java.util.List;

public interface SubscribeService {
    HostingType getHostingType();
    void subscribe(SubscribeRequestDto subscribeRequestDto);
    List<Subscription> subscriptions(String login);
}
