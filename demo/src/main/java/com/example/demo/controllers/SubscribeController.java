package com.example.demo.controllers;

import com.example.demo.model.dto.SubscribeRequestDto;
import com.example.demo.model.entity.HostingType;
import com.example.demo.service.subscription.SubscribeService;
import com.example.demo.service.subscription.SubscribeServiceResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subs/{type}")
public class SubscribeController {

    private final SubscribeServiceResolver subscribeServiceResolver;

    @PostMapping
    public ResponseEntity<?> subscribe(@PathVariable("type") HostingType type,
                                       @RequestBody SubscribeRequestDto subscribeRequestDto) {
        SubscribeService service = subscribeServiceResolver.getService(type);
        service.subscribe(subscribeRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUserSubscriptions(@PathVariable("type") HostingType type,
                                                  @PathVariable("user_id") String userId) {
        SubscribeService service = subscribeServiceResolver.getService(type);
        return ResponseEntity.ok(service.getUserSubscriptions(userId));
    }
}