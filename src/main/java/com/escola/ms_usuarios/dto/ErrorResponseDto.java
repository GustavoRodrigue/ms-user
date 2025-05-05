package com.escola.ms_usuarios.dto;

import lombok.Data;

@Data
public class ErrorResponseDto extends RuntimeException {
    public ErrorResponseDto(String message) {
        super(message);
    }
}
