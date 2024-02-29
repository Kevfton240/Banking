package com.kongo.banking.controllers;


import com.kongo.banking.dto.UserDto;
import com.kongo.banking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
        @RequestBody UserDto userDto
    ){
        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    public ResponseEntity<UserDto> findById(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findById(userId));
    }

    @PatchMapping("/validate/{userId}")
    public ResponseEntity<Integer> validateAccount(
            @PathVariable("userId") Integer userId
    ){
        return ResponseEntity.ok(service.validateAccount(userId));
    }

    @PatchMapping("/invalidate/{user-id}")
    public ResponseEntity<Integer> invalidateAccount(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.invalidateAccount(userId));
    }

    @DeleteMapping("{user-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("user-id") Integer userId
    ){
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }

}