package com.example.demo.controllers;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.service.SubscribeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeController {

    private final SubscribeServiceImpl subscribeServiceImpl;

    @Autowired
    public SubscribeController(SubscribeServiceImpl subscribeServiceImpl) {
        this.subscribeServiceImpl = subscribeServiceImpl;
    }

    @PostMapping("/subscribe")
    public ResponseEntity subscribe(@RequestBody SubscribeRequestDto subscribeRequestDto) {
        subscribeServiceImpl.subscribe(subscribeRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscribe")
    public ResponseEntity subscriptions(@RequestParam("login") String login,
                                        @RequestParam("hostingType") String hostingType) {
        return ResponseEntity.ok(subscribeServiceImpl.subscriptions(login, hostingType));
    }
}