package com.kongo.banking.service;

import com.kongo.banking.dto.AuthenticationRequest;
import com.kongo.banking.dto.AuthenticationResponse;
import com.kongo.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto, Number>{

   Integer validateAccount(Integer id);
   Integer invalidateAccount(Integer id);


    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
