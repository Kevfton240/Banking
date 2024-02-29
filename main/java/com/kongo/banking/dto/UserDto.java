package com.kongo.banking.dto;


import com.kongo.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull(message = ("Le prenom ne doit pas etre vide"))
    @NotEmpty(message = ("Le prenom ne doit pas etre vide"))
    @NotBlank(message = ("Le prenom ne doit pas etre vide"))
    private String firstname;

    @NotNull(message = ("Le prenom ne doit pas etre vide"))
    @NotEmpty(message = ("Le prenom ne doit pas etre vide"))
    @NotBlank(message = ("Le prenom ne doit pas etre vide"))
    private String lastname;

    @NotNull(message = ("L'email n'est pas conforme"))
    @NotEmpty(message = ("L'email n'est pas conforme"))
    @NotBlank(message = ("L'email n'est pas conforme"))
    @Email(message = ("L'email n'est pas conforme"))
    private String email;

    @NotNull(message = ("Le mot ne doit pas etre vide"))
    @NotEmpty(message = ("Le mot ne doit pas etre vide"))
    @NotBlank(message = ("Le mot ne doit pas etre vide"))
    @Size(min = 8, max = 20, message = ("Le mot de passe doit etre entre 8 et 16 caracteres"))
    private String password;

    @Past
    private LocalDateTime birthdate;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

    }

    public static User toEntity(UserDto user){
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
