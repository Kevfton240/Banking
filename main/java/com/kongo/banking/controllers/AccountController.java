package com.kongo.banking.controllers;


import com.kongo.banking.dto.AccountDto;
import com.kongo.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AccountDto accountDto
            ){
        return ResponseEntity.ok(service.save(accountDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto> findById(
            @PathVariable("account-id") Integer addressId
    ){
        return ResponseEntity.ok(service.findById(addressId));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("account-id") Integer accountId
    ){
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}
