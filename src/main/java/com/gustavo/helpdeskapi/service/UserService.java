package com.gustavo.helpdeskapi.service;

import com.gustavo.helpdeskapi.dto.UserCreateDTO;
import com.gustavo.helpdeskapi.dto.UserDTO;
import com.gustavo.helpdeskapi.entity.User;
import com.gustavo.helpdeskapi.exception.ResourceNotFoundException;
import com.gustavo.helpdeskapi.mapper.UserMapper;
import com.gustavo.helpdeskapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserCreateDTO dto) {

        User user = UserMapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return UserMapper.toDTO(savedUser);

    }

    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper::toDTO).toList();

    }

    public UserDTO getUserById(Long id){

        User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Usuário não encontrado!"));

        return UserMapper.toDTO(user);

    }


    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Usuário não encontrado!"));

        userRepository.delete(user);
    }

    public User updateUser(Long id, User userData){
        User user = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Usuário Não Encontrado!"));

        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setRole(userData.getRole());

        return userRepository.save(user);
    }

}
