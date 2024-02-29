package com.kongo.banking.impl;

import com.kongo.banking.dto.AccountDto;
import com.kongo.banking.exception.OperationNoPermittedException;
import com.kongo.banking.models.Account;
import com.kongo.banking.repository.AccountRepository;
import com.kongo.banking.service.AccountService;
import com.kongo.banking.validator.ObjetsValidator;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountRepository repository;
    private final ObjetsValidator<AccountDto> validator;
    @Override
    public Integer save(AccountDto dto) {

       // if(dto.getId() != null){
         //   throw new OperationNoPermittedException(
           //         "Account cannot be updated",
             //       "Save account",
               //     "Account",
                 //   "Update not permitted"
                   // );
        //}
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount && account.getUser().isActive()){
            throw new OperationNoPermittedException(
                    "The selected user already an active account",
                    "Create Account",
                    "Account service",
                    "Account creation"
            );
        }
        if(dto.getId() == null){
            account.setIban(generateRandomIban());
        }
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID:" +id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    private String generateRandomIban(){

        String iban = Iban.random(CountryCode.FR).toFormattedString();

        boolean ibanExits = repository.findByIban(iban).isPresent();
        if(ibanExits){
            generateRandomIban();
        }


        return iban;
    }
}
