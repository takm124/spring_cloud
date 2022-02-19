package com.example.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin { // 로그인 처리를 위한 model class

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email cannot be less than two chars")
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be greater than eight chars")
    private String password;
}
