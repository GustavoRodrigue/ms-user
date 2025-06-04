package com.school.ms_user.service;

import com.school.ms_user.dto.ErrorResponseDto;
import com.school.ms_user.dto.UserDto;
import com.school.ms_user.repository.UserRepository;
import com.school.ms_user.repository.entity.UserEntity;
import com.school.ms_user.repository.entity.UserEnum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    UserEntity user;

    UserDto userDto;

    @BeforeEach
    public void setUp(){
        userService = new UserService(userRepository);
        userDto = UserDto.builder()
                .name("Gustavo")
                .type(UserEnum.PROFESSOR).build();
        user = UserEntity.builder()
                .id(UUID.randomUUID())
                .name(userDto.getName())
                .type(userDto.getType()).build();

    }


    @Test
    void registerTest() {

        when(userRepository.save(any())).thenReturn(user);

        UserEntity userEntity =userService.register(userDto);
        assertEquals(userEntity, user);
        assertEquals(userEntity.getName(), userDto.getName());

    }

    @Test
    void registerExceptionTest(){
        when(userRepository.save(any()))
                .thenThrow(new RuntimeException("Erro simulado no save"));

        ErrorResponseDto thrown = assertThrows(
                ErrorResponseDto.class,
                () -> userService.register(userDto)
        );
        assertEquals("Erro simulado no save", thrown.getMessage());
    }

    @Test
    void listAllTest() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UserEntity> userEntities = userService.listAll();

        assertEquals(Collections.singletonList(user), userEntities);
        assertEquals(1, userEntities.size());

    }
    @Test
    void listAllExceptionTest(){
        when(userRepository.findAll())
                .thenThrow(new RuntimeException("Erro simulado no save"));

        ErrorResponseDto thrown = assertThrows(
                ErrorResponseDto.class,
                () -> userService.listAll()
        );
        assertEquals("Erro simulado no save", thrown.getMessage());
    }

    @Test
    void listByIdTest() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        UserEntity userEntity = userService.listById(user.getId());

        assertEquals(userEntity, user);
    }

@Test
void listByIdExceptionTest(){
    when(userRepository.findById(any()))
            .thenThrow(new RuntimeException("Erro simulado no save"));

    ErrorResponseDto thrown = assertThrows(
            ErrorResponseDto.class,
            () -> userService.listById(user.getId())
    );
    assertEquals("Erro simulado no save", thrown.getMessage());
}
    @Test
    void changeUserTest() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        UserEntity userEntity = userService.listById(user.getId());
        when(userRepository.save(any())).thenReturn(user);
        UserEntity userEntity1 = userService.changeUser(userDto, userEntity.getId());
        assertEquals(userEntity1, user);
    }
    @Test
    void changeUserNullTest() {

        UserEntity userEntity1 = userService.changeUser(userDto, user.getId());
        assertEquals(userEntity1, null);
    }
    @Test
    void changeUserExceptionTest(){
        when(userRepository.findById(any()))
                .thenThrow(new RuntimeException("Erro simulado no save"));

        ErrorResponseDto thrown = assertThrows(
                ErrorResponseDto.class,
                () -> userService.changeUser(userDto, user.getId())
        );
        assertEquals("Erro simulado no save", thrown.getMessage());
    }

    @Test
    void deleteTest() {
        userService.delete(user.getId());
    }
    @Test
    void deleteExceptionTest() {
        UUID id = UUID.randomUUID();

        doThrow(new RuntimeException("Erro simulado no delete"))
                .when(userRepository)
                .deleteById(id);

        ErrorResponseDto thrown = assertThrows(
                ErrorResponseDto.class,
                () -> userService.delete(id)
        );
        assertEquals("Erro simulado no delete", thrown.getMessage());
    }
}