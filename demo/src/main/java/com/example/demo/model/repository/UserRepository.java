package com.example.demo.model.repository;

import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;
import com.example.demo.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public User save(User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    public void deleteById(String id) {
        userMap.remove(id);
    }
}
