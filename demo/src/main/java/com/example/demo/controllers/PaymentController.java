package com.example.demo.controllers;

import com.example.demo.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/replenishment/{user_id}")
    public ResponseEntity<?> replenishmentBalance(@PathVariable("user_id") String userId, @RequestParam("amount") double amount) {
        paymentService.replenishmentBalance(userId, amount);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/withdrawal/{user_id}")
    public ResponseEntity<?> withdrawalBalance(@PathVariable("user_id") String userId, @RequestParam("amount") double amount) {
        paymentService.withdrawalBalance(userId, amount);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
