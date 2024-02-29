package com.kongo.banking.impl;


import com.kongo.banking.dto.ContactDto;
import com.kongo.banking.models.Contact;
import com.kongo.banking.repository.ContactRepository;
import com.kongo.banking.service.ContactService;
import com.kongo.banking.validator.ObjetsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {


    private final ContactRepository repository;
    private final ObjetsValidator<ContactDto> validator;
    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.fromEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No contact was found the ID:" +id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    @Override
    public List<ContactDto> findAllUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
