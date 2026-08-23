package com.invertory.mapper;

import com.invertory.dto.UserRequest;
import com.invertory.entity.User;


public interface UserMapper {
    public static User toEntity(UserRequest request){
        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setUserName(request.username());
        user.setPassword(request.password());
        return  user;
    }

}
