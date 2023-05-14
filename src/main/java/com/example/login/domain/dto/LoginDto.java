package com.example.login.domain.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
}
