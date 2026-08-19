package com.gustavo.helpdeskapi.mapper;

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

}
