package com.example.demo.repository;

import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SubscribeRepository {
    Map<String, List<Subscription>> subscriptions = new HashMap<>();

    public void save(String login, Subscription subscription) {
        List<Subscription> userSubscriptions;
        if (subscriptions.containsKey(login)) {
            userSubscriptions = subscriptions.get(login);
        } else {
            userSubscriptions = new ArrayList<>();
        }
        userSubscriptions.add(subscription);
        subscriptions.put(login, userSubscriptions);
    }

    public List<Subscription> findByLoginAndHostingType(String login, HostingType hostingType) {
        return subscriptions.get(login)
                .stream()
                .filter(subscription -> subscription.getHostingType().equals(hostingType))
                .toList();
    }

/*    public void deleteUri(String login, Subscription subscription) {
        List<Subscription> userSubscriptions = findByLoginAndHostingType(login);
        userSubscriptions.remove(subscription);
        subscriptions.put(login, userSubscriptions);
    }*/
}
