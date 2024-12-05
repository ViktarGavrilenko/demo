package com.example.demo.model.dto;

import com.example.demo.model.entity.HostingType;
import com.example.demo.model.entity.Subscription;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SubscribeRequestDto {
    String login;
    HostingType hostingType;
    String uri;
}