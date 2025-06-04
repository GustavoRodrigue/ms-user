package com.school.ms_user.dto;

import lombok.Data;

@Data
public class ErrorResponseDto extends RuntimeException {
    public ErrorResponseDto(String message) {
        super(message);
    }
}
