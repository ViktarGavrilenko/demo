package com.example.demo.service.user;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.StatusUser;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.web.exception.NotValidAmountException;
import com.example.demo.web.exception.UserNoFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.demo.ulits.ConstantMessages.NEGATIVE_AMOUNT;
import static com.example.demo.ulits.ConstantMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(UserDto dto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(dto.getUsername());
        user.setStatusUser(StatusUser.ACTIVE);
        return userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository
                .findById(id).filter(user -> user.getStatusUser() == StatusUser.ACTIVE)
                .stream().findFirst()
                .orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));
    }

    public User updateUser(UserDto dto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));
        user.setUsername(dto.getUsername());
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        //в репозитории
        User user = userRepository.findById(id).orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));
        user.setStatusUser(StatusUser.BLOCKED);
    }

    public void replenishmentBalance(String userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));
        if (amount < 0) {
            throw new NotValidAmountException(NEGATIVE_AMOUNT);
        }
        user.setBankAccount(user.getBankAccount() + amount);
    }

    public void withdrawalBalance(String userId, double amount) {
        User user = userRepository.findByIdAndStatus(userId, StatusUser.ACTIVE);
        if (amount < 0) {
            throw new NotValidAmountException(NEGATIVE_AMOUNT);
        }
        if (user.getBankAccount() < amount) {
            throw new NotValidAmountException("Недостаточно средств");
        }

        user.setBankAccount(user.getBankAccount() - amount);
    }
}