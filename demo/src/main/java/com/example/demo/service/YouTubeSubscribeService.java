package com.example.demo.service;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;
import com.example.demo.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YouTubeSubscribeService implements SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Autowired
    public YouTubeSubscribeService(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    @Override
    public HostingType getHostingType() {
        return HostingType.YOUTUBE;
    }

    @Override
    public void subscribe(SubscribeRequestDto subscribeRequestDto) {
        subscribeRepository.save(
                subscribeRequestDto.getLogin(),
                new Subscription(HostingType.YOUTUBE, subscribeRequestDto.getUri()));
    }

    @Override
    public List<Subscription> subscriptions(String login) {
        return subscribeRepository.findByLoginAndHostingType(login, HostingType.YOUTUBE);
    }
}
