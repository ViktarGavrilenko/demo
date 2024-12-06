package com.example.demo.service.subscription;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;
import com.example.demo.model.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("TELEGRAM")
@RequiredArgsConstructor
public class TelegramSubscribeService implements SubscribeService {

    private final SubscriptionRepository subscriptionRepository;


    @Override
    public HostingType getHostingType() {
        return HostingType.TELEGRAM;
    }

    @Override
    public void subscribe(SubscribeRequestDto subscribeRequestDto) {
        Subscription subscription = new Subscription();
        subscription.setId(UUID.randomUUID().toString());
        subscription.setHostingType(HostingType.TELEGRAM);
        subscription.setUri(subscribeRequestDto.getUri());
        subscription.setUserId(subscribeRequestDto.getUserId());
        subscriptionRepository.save(subscription);

        //Здесь можно и нужно сделать связку user и subscription,
        // то есть при добавлении новой подписки, проверять существует ли такой пользователь и
        // уже ему добавлять в лист эту подписку
    }

    @Override
    public List<Subscription> getUserSubscriptions(String username) {
        return subscriptionRepository.findByUserId(username);
    }
}
