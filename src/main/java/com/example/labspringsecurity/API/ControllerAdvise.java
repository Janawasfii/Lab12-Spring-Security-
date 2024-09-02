package com.example.labspringsecurity.API;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(value = APIException.class)
    public ResponseEntity ApiException(APIException e){
        return ResponseEntity.status(400).body(e.getMessage());}

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
