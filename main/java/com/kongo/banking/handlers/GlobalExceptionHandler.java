package com.kongo.banking.handlers;


import com.kongo.banking.exception.ObjetValidationException;
import com.kongo.banking.exception.OperationNoPermittedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.concurrent.ExecutionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjetValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjetValidationException exception){

       ExceptionRepresentation representation= ExceptionRepresentation.builder()
               .errorMessage("Objet not valid exception has occurred")
               .errorSource(exception.getViolationsSource())
               .validationErrors(exception.getViolations())
               .build();

       return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
               .body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(representation);
    }

    @ExceptionHandler(OperationNoPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNoPermittedException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMsg())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("A user already exits with the provides Email")
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);

    }
}
