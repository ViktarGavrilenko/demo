package com.example.demo.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    String id;

    String username;

    List<Subscription> subscriptions = new ArrayList<>();

    StatusUser statusUser;

    double bankAccount;

    public User(String id) {
        this.id = id;
    }
}
