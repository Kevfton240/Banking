package com.kongo.banking.controllers;


import com.kongo.banking.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {


    private final StatisticsService service;


    @GetMapping("/sum-by-date/{user-id}")
    public ResponseEntity<Map<LocalDate, BigDecimal>> findSumTransactionsByDate(
            @RequestParam("start-date") LocalDate startDate,
            @RequestParam("end-date") LocalDate endDate,
            @PathVariable("user-id") Integer userId) {
        Map<LocalDate, BigDecimal> result = service.findSumTractionsByDate(startDate, endDate, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/account/balance/{user-id}")
    public ResponseEntity<BigDecimal> getAccountBalance(
            @PathVariable("user-id") Integer userId) {
        BigDecimal accountBalance = service.getAccountBalance(userId);
        return ResponseEntity.ok(accountBalance);
    }


    @GetMapping("/highest-transfer/{user-id}")
    public ResponseEntity<BigDecimal>  highestTransfert(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.highestTransfert(userId));

    }


    @GetMapping("/highest-deposit/{user-id}")
    public ResponseEntity<BigDecimal>  highestDeposit(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.highestDeposit(userId));
    }
}

