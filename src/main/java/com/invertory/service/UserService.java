package com.invertory.service;

import com.invertory.dto.UserRequest;
import com.invertory.entity.User;
import com.invertory.exception.EmailAlreadyExistsException;
import com.invertory.exception.UsernameAlreadyExistsException;
import com.invertory.mapper.UserMapper;
import com.invertory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String registerUser(UserRequest request){
        User user = UserMapper.toEntity(request);

        boolean isEmailExists = userRepository.existsByEmail(request.email());

        if(isEmailExists)
            throw new EmailAlreadyExistsException(String.format("Email already exists : %s ", request.email()));

        boolean isUserExists = userRepository.existsByUserName(request.username());

        if(isUserExists)
            throw new UsernameAlreadyExistsException(String.format("%s, Username already exist ", request.username()));

        User savedUser = userRepository.save(user);
        return savedUser != null ? "User Saved successfully" : "User not saved something went wrong";
    }
}
