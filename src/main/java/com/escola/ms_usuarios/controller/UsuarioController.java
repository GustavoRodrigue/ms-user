package com.escola.ms_usuarios.controller;

import com.escola.ms_usuarios.dto.UsuarioDto;
import com.escola.ms_usuarios.repository.entity.UsuarioEntity;
import com.escola.ms_usuarios.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioservice;

    @PostMapping()
    public ResponseEntity<UsuarioEntity> cadastrar(@RequestBody UsuarioDto usuario){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(usuarioservice.cadastra(usuario));
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioEntity>> listatodos(){
        return ResponseEntity.ok(usuarioservice.litartodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> listarporid(@PathVariable UUID id){
        return ResponseEntity.ok(usuarioservice.listarporid(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> alterarusuario(@RequestBody UsuarioDto usuarioDto, @PathVariable UUID id){
        return ResponseEntity.ok(usuarioservice.alterusuario(usuarioDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delear(@PathVariable UUID id){
        usuarioservice.deleter(id);
        return ResponseEntity.noContent().build();
    }


}
