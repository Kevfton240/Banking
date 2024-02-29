package com.kongo.banking.repository;

import com.kongo.banking.dto.ContactDto;
import com.kongo.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByUserId(Integer userId);
}
