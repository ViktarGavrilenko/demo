package com.example.demo.service;

import com.example.demo.model.entity.HostingType;
import com.example.demo.repository.SubscribeRepository;

public class SubscribeServiceResolver {
    SubscribeService getService(HostingType hostingType) {
        return switch (hostingType) {
            case TELEGRAM -> new YouTubeSubscribeService(new SubscribeRepository());
            default -> throw new RuntimeException();
        };
    }
}
