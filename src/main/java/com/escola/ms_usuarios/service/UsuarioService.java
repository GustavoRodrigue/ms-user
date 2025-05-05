package com.escola.ms_usuarios.service;

import com.escola.ms_usuarios.dto.ErrorResponseDto;
import com.escola.ms_usuarios.dto.UsuarioDto;
import com.escola.ms_usuarios.repository.UsuarioRepository;
import com.escola.ms_usuarios.repository.entity.UsuarioEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuariorepository;

    public UsuarioEntity cadastra(UsuarioDto usuarioDto){
        log.info("ServiceCurso - criar - Dados recebidos: {}",usuarioDto);
        try {
            UsuarioEntity usuario =new UsuarioEntity();

            usuario.setId(UUID.randomUUID());
            usuario.setNome(usuarioDto.getNome());
            usuario.setTipo(usuarioDto.getTipo());

            return usuariorepository.save(usuario);

        } catch (Exception e) {
            throw new ErrorResponseDto(e.getMessage());
        }
    }

    public List<UsuarioEntity> litartodos(){

        try {
            return usuariorepository.findAll();
        }catch (Exception e) {
            throw new ErrorResponseDto(e.getMessage());
        }
    }

    public UsuarioEntity listarporid(UUID id){
        log.info("ServiceCurso - listarporid - Dados recebidos: {}",id);
        try {
        Optional<UsuarioEntity> curso = usuariorepository.findById(id);

        if (curso.isPresent()){
            return curso.get();
        }else{
            return null;
        }

        }catch (Exception e) {
            throw new ErrorResponseDto(e.getMessage());
        }
    }

    public UsuarioEntity alterusuario(UsuarioDto usuarioDto, UUID id){
        log.info("ServiceCurso - alterarusuario - Dados recebidos: {}",usuarioDto, id);
        try{
            UsuarioEntity usuario = listarporid(id);

            if (usuario !=null){
                usuario.setNome(usuarioDto.getNome());
                usuario.setTipo(usuarioDto.getTipo());

                return usuariorepository.save(usuario);
            }else{
                return null;
            }

        }catch (Exception e) {
            throw new ErrorResponseDto(e.getMessage());
        }

    }

    public void deleter(UUID id){
        log.info("ServiceCurso - deletar - Dados recebidos: {}",id);
       try{
           usuariorepository.deleteById(id);
       }catch (Exception e) {
           throw new ErrorResponseDto(e.getMessage());
       }
    }

}
