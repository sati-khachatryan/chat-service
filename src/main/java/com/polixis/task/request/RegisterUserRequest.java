package com.polixis.task.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterUserRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "Username must contain at least one letter")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 3, max = 20, message = "password must be between 3 and 12 characters")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "password must contain at least one letter")
    private String password;

    @NotBlank(message = "passwordRepeated is required")
    private String passwordRepeated;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }
}
