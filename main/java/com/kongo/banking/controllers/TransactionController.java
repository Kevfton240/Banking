package com.kongo.banking.controllers;


import com.kongo.banking.dto.TransationDto;
import com.kongo.banking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody TransationDto transationDto
    ){
        return ResponseEntity.ok(service.save(transationDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransationDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransationDto> findById(
            @PathVariable("transaction-id") Integer transactionId
    ){
        return ResponseEntity.ok(service.findById(transactionId));
    }
    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<TransationDto>> findAllByUserId(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("transaction-id") Integer transactionId
    ){
        service.delete(transactionId);
        return ResponseEntity.accepted().build();
    }
}
