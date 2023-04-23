package com.example.login.vo;

import lombok.Data;

@Data
public class ResponseCookieVO {
    public String userId;
    public String password;
    public String sessionId;
}
