package com.kongo.banking.service;

import com.kongo.banking.dto.TransationDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransationDto, Number>{

    List<TransationDto> findAllByUserId(Integer userId);
}
