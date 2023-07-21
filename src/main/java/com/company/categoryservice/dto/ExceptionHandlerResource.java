package com.company.categoryservice.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExceptionHandlerResource {
    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErrorDto> errors=e.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            String field=fieldError.getField();
            String message=fieldError.getDefaultMessage();
            String rejectionValue=String.valueOf(fieldError.getRejectedValue());
            return new ErrorDto(field, message+"rejection Value"+rejectionValue);
        }).toList();
        return ResponseEntity.badRequest().body(
                ResponseDto.<Void>builder()
                        .success(false)
                        .code(-2)
                        .message("Validation error")
                        .errors(errors)
                        .build()
        );

    }

}
