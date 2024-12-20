package com.example.demo.model.repository;

import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SubscriptionRepository {

    private final Map<String, Subscription> subscriptionMap = new HashMap<>();

    public List<Subscription> findAll() {
        return new ArrayList<>(subscriptionMap.values());
    }

    public Optional<Subscription> findById(String id) {
        return Optional.ofNullable(subscriptionMap.get(id));
    }

    public Subscription save(Subscription subscription) {
        subscriptionMap.put(subscription.getId(), subscription);
        return subscription;
    }

    public void deleteById(String id) {
        subscriptionMap.remove(id);
    }

    public List<Subscription> findByUserIdAndHostingType(String userId, HostingType hostingType) {
        return subscriptionMap.values().stream()
                .filter(subscription -> subscription.getUser().getId().equals(userId))
                .filter(subscription -> subscription.getHostingType().equals(hostingType))
                .collect(Collectors.toList());
    }
}