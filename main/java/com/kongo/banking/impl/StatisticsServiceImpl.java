package com.kongo.banking.impl;

import com.kongo.banking.repository.TransactionRepository;
import com.kongo.banking.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final TransactionRepository transactionRepository;


    @Override
    public Map<LocalDate, BigDecimal> findSumTractionsByDate(LocalDate startDate, LocalDate enDate, Integer userId) {
        return null;
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return null;
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return null;
    }
}
