package com.escola.ms_usuarios.dto;

import com.escola.ms_usuarios.repository.entity.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String name;
    private UserEnum type;
}
