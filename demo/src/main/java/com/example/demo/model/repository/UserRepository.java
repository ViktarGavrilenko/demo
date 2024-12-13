package com.example.demo.model.repository;

import com.example.demo.model.entity.StatusUser;
import com.example.demo.model.entity.User;
import com.example.demo.web.exception.UserNoFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.demo.ulits.ConstantMessages.USER_NOT_FOUND;

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

    public User findByIdAndStatus(String id, StatusUser statusUser) {
        return findById(id).filter(user -> user.getStatusUser() == statusUser)
                .stream().findFirst()
                .orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));
    }

}