package com.example.login.vo;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginVO {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
}
