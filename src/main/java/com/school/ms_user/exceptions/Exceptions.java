package com.school.ms_user.exceptions;

import com.school.ms_user.dto.ErrorDto;
import com.school.ms_user.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class Exceptions {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> bedRequest(HttpMessageNotReadableException ex){
        ErrorDto error = new ErrorDto();
        error.setStatus("Ocorreu um erro de status '400'");
        error.setDetail("Dados invalidos!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler (NoResourceFoundException.class)
    public ResponseEntity<ErrorDto> notFound(){
        ErrorDto error = new ErrorDto();
        error.setStatus("Ocorreu um erro de status '404'");
        error.setDetail("EndPoint que não existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
    @ExceptionHandler (ErrorResponseDto.class)
    public ResponseEntity<ErrorDto> servererror(){
        ErrorDto error = new ErrorDto();
        error.setStatus("Ocorreu um erro de status '500'");
        error.setDetail("Erro ao acessar no banco de dados");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
