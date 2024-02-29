package com.kongo.banking.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface StatisticsService {

    Map<LocalDate, BigDecimal> findSumTractionsByDate(LocalDate startDate, LocalDate enDate, Integer userId);
    BigDecimal getAccountBalance(Integer userId);
    BigDecimal highestTransfert(Integer userId);
    BigDecimal highestDeposit(Integer userId);
}
