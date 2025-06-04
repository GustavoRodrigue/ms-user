package com.school.ms_user.controller;

import com.school.ms_user.dto.UserDto;
import com.school.ms_user.repository.entity.UserEntity;
import com.school.ms_user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserEntity> register(@RequestBody UserDto user){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(userService.register(user));
    }

    @GetMapping()
    public ResponseEntity<List<UserEntity>> listAll(){
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> listById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.listById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> changeUser(@RequestBody UserDto user, @PathVariable UUID id){
        return ResponseEntity.ok(userService.changeUser(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
