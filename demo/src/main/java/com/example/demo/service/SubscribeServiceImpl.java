package com.example.demo.service;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.Subscription;
import com.example.demo.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeServiceImpl implements SubscribeService {
    private final SubscribeRepository subscribeRepository;

    @Autowired
    public SubscribeServiceImpl(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    @Override
    public void subscribe(SubscribeRequestDto subscribeRequestDto) {
        subscribeRepository.save(subscribeRequestDto.getLogin(), subscribeRequestDto.getSubscription());
    }

    @Override
    public List<Subscription> subscriptions(String login, String hostingType) {


        return subscribeRepository.findByLoginAndHostingType(login, hostingType);
    }
}