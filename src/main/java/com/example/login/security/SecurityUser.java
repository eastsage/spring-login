package com.example.login.security;

import com.example.login.entity.Member;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Slf4j
@Getter @Setter
public class SecurityUser extends User {
    private Member member;

    public SecurityUser(Member member) {
        super(member.getUserId(), member.getPassword(), new ArrayList<SimpleGrantedAuthority>());
        log.info("SecurityUser member.username = {}", member.getUserId());
        log.info("SecurityUser member.password = {}", member.getPassword());
        this.member = member;
    }
}
