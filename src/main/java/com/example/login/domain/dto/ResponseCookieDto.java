package com.example.login.domain.dto;

import lombok.Data;

@Data
public class ResponseCookieDto {
    public String userId;
    public String password;
    public String sessionId;
}
