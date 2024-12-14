package com.example.demo.service.subscription;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.StatusUser;
import com.example.demo.model.entity.Subscription;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.SubscriptionRepository;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.web.exception.NotValidAmountException;
import com.example.demo.web.exception.UserNoFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.demo.ulits.ConstantMessages.INSUFFICIENT_FUNDS;
import static com.example.demo.ulits.ConstantMessages.USER_NOT_FOUND;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@Service("YOUTUBE")
@RequiredArgsConstructor
public class YouTubeSubscribeService implements SubscribeService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;


    @Override
    public HostingType getHostingType() {
        return HostingType.YOUTUBE;
    }

    @Override
    public void subscribe(SubscribeRequestDto subscribeRequestDto) {
        User user = userRepository.findByIdAndStatus(subscribeRequestDto.getUserId(), StatusUser.ACTIVE)
                .orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));

        double sum = user.getBankAccount();
        if (sum < subscribeRequestDto.getPrice()) {
            throw new NotValidAmountException(INSUFFICIENT_FUNDS, METHOD_NOT_ALLOWED);
        }

        user.setBankAccount(user.getBankAccount() - subscribeRequestDto.getPrice());


        Subscription subscription = new Subscription();
        subscription.setId(UUID.randomUUID().toString());
        subscription.setHostingType(HostingType.YOUTUBE);
        subscription.setUri(subscribeRequestDto.getUri());
        subscription.setUser(user);

        List<Subscription> userSubscriptions = user.getSubscriptions();
        userSubscriptions.add(subscription);
        user.setSubscriptions(userSubscriptions);

        subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getUserSubscriptions(String userId) {
        User user = userRepository.findByIdAndStatus(userId, StatusUser.ACTIVE)
                .orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));
        return subscriptionRepository.findByUserIdAndHostingType(user.getUsername(), getHostingType());
    }
}
