package com.escola.ms_usuarios.controller;

import com.escola.ms_usuarios.dto.UserDto;
import com.escola.ms_usuarios.repository.entity.UserEntity;
import com.escola.ms_usuarios.repository.entity.UserEnum;
import com.escola.ms_usuarios.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    UserDto userDto;
    UserEntity user;

    @BeforeEach
    public void setUp(){
        userController = new UserController(userService);
        userDto = UserDto.builder()
                .name("Gustavo")
                .type(UserEnum.ALUNO).build();
        user = UserEntity.builder()
                .id(UUID.randomUUID())
                .name(userDto.getName())
                .type(userDto.getType()).build();
    }

    @Test
    void register() {
        ResponseEntity<UserEntity> userEntity = userController.register(userDto);
        assertNotNull(userEntity);
    }

    @Test
    void listAll() {
        ResponseEntity<List<UserEntity>> listResponseEntity = userController.listAll();
        assertNotNull(listResponseEntity);
    }

    @Test
    void listById() {
        ResponseEntity<UserEntity> userEntity = userController.listById(user.getId());
        assertNotNull(userEntity);
    }

    @Test
    void changeUser() {
        UUID id = UUID.randomUUID();
        var userController1 =userController.changeUser(userDto, id);
        assertNotNull(userController1);
    }

    @Test
    void delete() {
        UUID id = UUID.randomUUID();
        var userController1 =userController.delete(id);
        assertNotNull(userController1);
    }
}