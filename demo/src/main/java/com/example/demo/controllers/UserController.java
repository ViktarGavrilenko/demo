package com.example.demo.controllers;

import com.example.demo.model.dto.UserDto;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.addUser(dto));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUser(@PathVariable("user_id") String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PatchMapping("/{user_id}")
    public ResponseEntity<?> patchUser(@RequestBody UserDto userDto, @PathVariable("user_id") String userId) {
        return ResponseEntity.ok(userService.patchUser(userDto, userId));
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<?> putUser(@RequestBody UserDto userDto, @PathVariable("user_id") String userId) {
        return ResponseEntity.ok(userService.putUser(userDto, userId));
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user_id") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}