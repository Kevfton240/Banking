package com.kongo.banking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@RequiredArgsConstructor
public class ObjetValidationException extends RuntimeException{

    @Getter
    private final Set<String> violations;

    @Getter
    private final String violationsSource;
}
