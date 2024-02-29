package com.kongo.banking.impl;

import com.kongo.banking.dto.TransationDto;
import com.kongo.banking.dto.UserDto;
import com.kongo.banking.models.TransactionType;
import com.kongo.banking.models.Transation;
import com.kongo.banking.models.User;
import com.kongo.banking.repository.TransactionRepository;
import com.kongo.banking.repository.UserRepository;
import com.kongo.banking.service.TransactionService;
import com.kongo.banking.service.UserService;
import com.kongo.banking.validator.ObjetsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final ObjetsValidator<TransationDto> validator;

    @Override
    public Integer save(TransationDto dto) {
        validator.validate(dto);
        Transation transation = TransationDto.toEntity(dto);
        BigDecimal transationMultiplier = BigDecimal.valueOf(getTransactionMultiplier(transation.getType()));
        BigDecimal amount = transation.getAmount().multiply(transationMultiplier);
        transation.setAmount(amount);
        return repository.save(transation).getId();

    }

    @Override
    public List<TransationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(TransationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransationDto findById(Integer id) {
        return repository.findById(id)
                .map(TransationDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No transaction was found with the ID:" +id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }
    private int getTransactionMultiplier(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1: 1;
    }

    @Override
    public List<TransationDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TransationDto::fromEntity)
                .collect(Collectors.toList());
    }
}
