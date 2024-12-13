package com.example.demo.model.dto;

import com.example.demo.model.entity.StatusUser;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String username;
    StatusUser statusUser;
}