package com.kongo.banking.repository;

import com.kongo.banking.models.TransactionType;
import com.kongo.banking.models.Transation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transation, Integer> {
    List<Transation> findAllByUserId(Integer userId);

    @Query("select sum(t.amount) from Transation t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max(abs(t.amount)) as amount from Transation t where t.user.id = :userId and t.type = :transactionType")
    BigDecimal findHighestAmountByTransationType(@Param("userId") Integer userId, @Param("transactionType") TransactionType transactionType);
    @Query("select t.creationDate, sum(t.amount) from Transation t where t.user.id = :userId and t.creationDate between :start and :end")
    Map<LocalDate, BigDecimal> findSumTransactionsByDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("userId") Integer userId);




}
