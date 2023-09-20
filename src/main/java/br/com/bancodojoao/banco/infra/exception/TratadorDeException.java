package br.com.bancodojoao.banco.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

}
