package com.kongo.banking.dto;


import com.kongo.banking.models.Contact;
import com.kongo.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContactDto {

    private Integer id;

    private String firstaname;

    private String lastname;

    private String email;

    private String iban;

    private Integer userId;

    public static ContactDto fromEntity(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .firstaname(contact.getFirstaname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact fromEntity(ContactDto contact){
        return Contact.builder()
                .id(contact.getId())
                .firstaname(contact.getFirstaname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(User.builder().id(contact.getUserId()).build())
                .build();
    }
}
