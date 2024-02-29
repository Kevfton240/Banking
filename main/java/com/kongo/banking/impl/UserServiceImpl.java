package com.kongo.banking.impl;

import com.kongo.banking.configsecurity.JwtUtils;
import com.kongo.banking.dto.AccountDto;
import com.kongo.banking.dto.AuthenticationRequest;
import com.kongo.banking.dto.AuthenticationResponse;
import com.kongo.banking.dto.UserDto;
import com.kongo.banking.models.Role;
import com.kongo.banking.models.User;
import com.kongo.banking.repository.RoleRepository;
import com.kongo.banking.repository.UserRepository;
import com.kongo.banking.service.AccountService;
import com.kongo.banking.service.UserService;
import com.kongo.banking.validator.ObjetsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final ObjetsValidator<UserDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;



    @Override
    public Integer save(UserDto dto) {
       validator.validate(dto);
        User user =UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();

    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("No user found with the provided ID" +id));
    }

    @Override
    public void delete(Integer id) {

        userRepository.deleteById(id);

    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No user was found for account validation"));

        //user.setActive(true);

        AccountDto account =AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);

        user.setActive(true);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No user was found for user account validation"));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();

    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(
                findOrCreateRole(ROLE_USER)

        );
        User savedUser = userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstname() + "" +savedUser.getLastname());
        String token = jwtUtils.generateToken(savedUser);
        return AuthenticationResponse.builder()
                .token(token)
                .build();

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userRepository.findByEmail(request.getEmail()).get();
        final  String token = jwtUtils.generateToken(user);
       return AuthenticationResponse.builder()
               .token(token)
               .build();
}

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }
}






