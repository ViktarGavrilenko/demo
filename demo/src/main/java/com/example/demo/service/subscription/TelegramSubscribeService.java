package com.example.demo.service.subscription;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.StatusUser;
import com.example.demo.model.entity.Subscription;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.SubscriptionRepository;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.web.exception.UserNoFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.demo.ulits.ConstantMessages.USER_NOT_FOUND;

@Service("TELEGRAM")
@RequiredArgsConstructor
public class TelegramSubscribeService implements SubscribeService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;


    @Override
    public HostingType getHostingType() {
        return HostingType.TELEGRAM;
    }

    @Override
    public void subscribe(SubscribeRequestDto subscribeRequestDto) {
        User user = userRepository.findByIdAndStatus(subscribeRequestDto.getUserId(), StatusUser.ACTIVE);

//        user.getBankAccount()

        Subscription subscription = new Subscription();
        subscription.setId(UUID.randomUUID().toString());
        subscription.setHostingType(HostingType.TELEGRAM);
        subscription.setUri(subscribeRequestDto.getUri());
        subscription.setUser(user);
        subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getUserSubscriptions(String userId) {
        User user = userRepository.findByIdAndStatus(userId, StatusUser.ACTIVE);
        return subscriptionRepository.findByUserIdAndHostingType(user.getId(), getHostingType());
    }
}
