package com.polixis.task;

import com.polixis.task.exception.PasswordsDoNotMatchException;
import com.polixis.task.exception.UserAlreadyExistsException;
import com.polixis.task.exception.UserNotFoundException;
import com.polixis.task.exception.WrongPasswordException;
import com.polixis.task.response.ExResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        if (throwable instanceof PasswordsDoNotMatchException || throwable instanceof UserAlreadyExistsException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ExResponse(Response.Status.BAD_REQUEST, throwable.getMessage())).build();
        }

        if (throwable instanceof UserNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExResponse(Response.Status.NOT_FOUND, throwable.getMessage())).build();
        }
        if (throwable instanceof WrongPasswordException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ExResponse(Response.Status.BAD_REQUEST, throwable.getMessage())).build();

        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ExResponse(Response.Status.INTERNAL_SERVER_ERROR, throwable.getMessage())).build();

    }
}
