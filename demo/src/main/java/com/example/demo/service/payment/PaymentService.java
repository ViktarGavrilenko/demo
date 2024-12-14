package com.example.demo.service.payment;

import com.example.demo.model.entity.StatusUser;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.web.exception.NotValidAmountException;
import com.example.demo.web.exception.UserNoFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.ulits.ConstantMessages.NEGATIVE_AMOUNT;
import static com.example.demo.ulits.ConstantMessages.USER_NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserRepository userRepository;

    public void replenishmentBalance(String userId, double amount) {
        User user = userRepository.findByIdAndStatus(userId, StatusUser.ACTIVE)
                .orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));
        if (amount < 0) {
            throw new NotValidAmountException(NEGATIVE_AMOUNT, BAD_REQUEST);
        }

        double currentBalance;
        double result;

        boolean flag = true;

        while (flag) {
            currentBalance = user.getBankAccount();
            result = currentBalance + amount;

            if (user.getBankAccount() == currentBalance) {
                flag = false;
                user.setBankAccount(result);
            }
        }


        user.setBankAccount(user.getBankAccount() + amount);
        userRepository.save(user);
    }



    public void withdrawalBalance(String userId, double amount) {
        User user = userRepository.findByIdAndStatus(userId, StatusUser.ACTIVE)
                .orElseThrow(() -> new UserNoFoundException(USER_NOT_FOUND));
        if (amount < 0) {
            throw new NotValidAmountException(NEGATIVE_AMOUNT, BAD_REQUEST);
        }
        if (user.getBankAccount() < amount) {
            throw new NotValidAmountException("Недостаточно средств", METHOD_NOT_ALLOWED);
        }

        user.setBankAccount(user.getBankAccount() - amount);
        userRepository.save(user);
    }
}