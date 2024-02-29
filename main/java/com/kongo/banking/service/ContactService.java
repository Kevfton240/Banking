package com.kongo.banking.service;

import com.kongo.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto, Number>{

    List<ContactDto> findAllUserId(Integer userId);
}
