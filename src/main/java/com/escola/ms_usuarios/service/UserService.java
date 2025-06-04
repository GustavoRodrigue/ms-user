package com.escola.ms_usuarios.service;

import com.escola.ms_usuarios.dto.ErrorResponseDto;
import com.escola.ms_usuarios.dto.UserDto;
import com.escola.ms_usuarios.repository.UserRepository;
import com.escola.ms_usuarios.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity register(UserDto userDto){
        log.info("ServiceCurso - criar - Dados recebidos: {}",userDto);
        try {
            UserEntity user = new UserEntity();

            user.setId(UUID.randomUUID());
            user.setName(userDto.getName());
            user.setType(userDto.getType());

            return userRepository.save(user);

        } catch (Exception e) {
            throw new ErrorResponseDto(e.getMessage());
        }
    }

    public List<UserEntity> listAll(){

        try {
            return userRepository.findAll();
        }catch (Exception e) {
            throw new ErrorResponseDto(e.getMessage());
        }
    }

    public UserEntity listById (UUID id){
        log.info("ServiceCurso - listarporid - Dados recebidos: {}",id);
        try {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()){
            return user.get();
        }else{
            return null;
        }

        }catch (Exception e) {
            throw new ErrorResponseDto(e.getMessage());
        }
    }

    public UserEntity changeUser(UserDto userDto, UUID id){
        log.info("ServiceCurso - alterarusuario - Dados recebidos: {}",userDto, id);
        try{
            UserEntity user = listById(id);

            if (user !=null){
                user.setName(userDto.getName());
                user.setType(userDto.getType());

                return userRepository.save(user);
            }else{
                return null;
            }

        }catch (Exception e) {
            throw new ErrorResponseDto(e.getMessage());
        }

    }

    public void delete(UUID id){
        log.info("ServiceCurso - deletar - Dados recebidos: {}",id);
       try{
           userRepository.deleteById(id);
       }catch (Exception e) {
           throw new ErrorResponseDto(e.getMessage());
       }
    }

}
