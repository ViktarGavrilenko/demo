package com.example.demo.config;


import com.example.demo.model.entity.User;
import com.example.demo.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Slf4j
@Configuration
public class UserInitConfig {

    private final UserRepository userRepository;

    @Autowired
    public UserInitConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${users.names}")
    private List<String> users;

    @Bean
    public UserRepository initUsersRepository() {
        for (String userName : users) {
            User user = new User();
            String idUser = UUID.randomUUID().toString();
            log.info(idUser);
            user.setId(idUser);
            user.setUsername(userName);
            userRepository.save(user);
        }
        return userRepository;
    }
}