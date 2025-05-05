package com.escola.ms_usuarios.dto;

import com.escola.ms_usuarios.repository.entity.UsuarioEnum;
import lombok.Data;

@Data
public class UsuarioDto {
    private String nome;
    private UsuarioEnum tipo;
}
