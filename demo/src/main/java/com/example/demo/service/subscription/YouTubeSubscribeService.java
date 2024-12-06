package com.example.demo.service.subscription;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;
import com.example.demo.model.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("YOUTUBE")
@RequiredArgsConstructor
public class YouTubeSubscribeService implements SubscribeService {

    private final SubscriptionRepository subscriptionRepository;


    @Override
    public HostingType getHostingType() {
        return HostingType.YOUTUBE;
    }

    @Override
    public void subscribe(SubscribeRequestDto subscribeRequestDto) {
        Subscription subscription = new Subscription();
        subscription.setId(UUID.randomUUID().toString());
        subscription.setHostingType(HostingType.YOUTUBE);
        subscription.setUri(subscribeRequestDto.getUri());
        subscription.setUserId(subscribeRequestDto.getUserId());
        subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getUserSubscriptions(String username) {
       return subscriptionRepository.findByUserId(username);
    }
}
