package com.polixis.task;

import com.polixis.task.request.RegisterUserRequest;
import com.polixis.task.request.UserLoginRequest;
import com.polixis.task.response.TokenResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
public class UserResource {
    @Inject
    UserService userService;

    @POST
    @Path("create")
    public Response createUser(@Valid RegisterUserRequest registerUserRequest) {
        return Response.ok(userService.createUser(registerUserRequest))
                .status(Response.Status.CREATED).build();
    }

    @POST
    @Path("login")
    public Response loginUser(@Valid UserLoginRequest userLoginRequest) {
        TokenResponse tokenResponse = userService.userLogin(userLoginRequest);
        return Response.ok().entity(tokenResponse).build();
    }

    @GET
    public Response getUsers() {
        List<UserEntity> allUsers = userService.findAll();
        return Response.ok().entity(allUsers).build();
    }
}