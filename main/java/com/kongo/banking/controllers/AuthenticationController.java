package com.kongo.banking.controllers;


import com.kongo.banking.configsecurity.JwtUtils;
import com.kongo.banking.dto.AuthenticationRequest;
import com.kongo.banking.dto.AuthenticationResponse;
import com.kongo.banking.dto.UserDto;
import com.kongo.banking.repository.UserRepository;
import com.kongo.banking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
           @RequestBody UserDto user){

        return ResponseEntity.ok(userService.register(user));
    }


    @PostMapping("/authenticate")
    public  ResponseEntity<AuthenticationResponse> authenticationResponseResponseEntity(

            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(userService.authenticate(request));



    }
}
