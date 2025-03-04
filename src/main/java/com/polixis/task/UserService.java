package com.polixis.task;

import com.polixis.task.request.RegisterUserRequest;
import com.polixis.task.request.UserLoginRequest;
import com.polixis.task.response.TokenResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    UserEntity createUser(RegisterUserRequest registerUserRequest);

    TokenResponse userLogin(@Valid UserLoginRequest userLoginRequest);

    List<UserEntity> findByIds(List<Long> ids);

    UserEntity findByUsername(String username);

    List<UserEntity> findAll();
}
