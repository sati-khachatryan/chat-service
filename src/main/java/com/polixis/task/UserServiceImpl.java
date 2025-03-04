package com.polixis.task;

import com.polixis.task.exception.PasswordsDoNotMatchException;
import com.polixis.task.exception.UserAlreadyExistsException;
import com.polixis.task.exception.UserNotFoundException;
import com.polixis.task.exception.WrongPasswordException;
import com.polixis.task.request.RegisterUserRequest;
import com.polixis.task.request.UserLoginRequest;
import com.polixis.task.response.TokenResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepository;

    @Transactional
    public UserEntity createUser(RegisterUserRequest registerUserRequest) {
        PanacheQuery<UserEntity> userName = userRepository.find("username", registerUserRequest.getUsername());
        if (userName.count() > 0) {
            throw new UserAlreadyExistsException(registerUserRequest.getUsername());
        }
        if (!registerUserRequest.getPassword().equals(registerUserRequest.getPasswordRepeated())) {
            throw new PasswordsDoNotMatchException();
        }

        UserEntity userEntity = registerUserRequestToUserEntity(registerUserRequest);
        userRepository.persist(userEntity);
        return userEntity;
    }

    public UserEntity findByUsername(String username) {
        PanacheQuery<UserEntity> entity = userRepository.find("username", username);
        if (entity.count() == 0) {
            throw new UserNotFoundException(username);
        } else return entity.firstResult();
    }

    public TokenResponse userLogin(@Valid UserLoginRequest userLoginRequest) {
        Optional<UserEntity> userEntityOpt =
                userRepository.find("username", userLoginRequest.getUsername()).firstResultOptional();

        if (userEntityOpt.isEmpty()) {
            throw new UserNotFoundException(userLoginRequest.getUsername());
        }

        UserEntity userEntity = userEntityOpt.get();
        if (!userEntity.verifyPassword(userLoginRequest.getPassword())) {
            throw new WrongPasswordException();
        }

        var token = JwtUtil.generateToken(userEntity.getUsername());

        return new TokenResponse(token, userEntity.id);
    }

    public List<UserEntity> findByIds(List<Long> ids) {
        var users = userRepository.findByIds(ids);
        if (users.size() < ids.size()) {
            throw new UserNotFoundException(ids);
        }
        return users;
    }

    private static UserEntity registerUserRequestToUserEntity(RegisterUserRequest registerUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerUserRequest.getUsername());
        userEntity.setPassword(registerUserRequest.getPassword());
        return userEntity;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll().stream().toList();
    }
}
