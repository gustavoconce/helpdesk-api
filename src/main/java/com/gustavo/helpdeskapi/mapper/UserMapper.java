package com.gustavo.helpdeskapi.mapper;

import com.gustavo.helpdeskapi.dto.UserCreateDTO;
import com.gustavo.helpdeskapi.dto.UserDTO;
import com.gustavo.helpdeskapi.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public static User toEntity(UserCreateDTO dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return user;
    }

}
