package com.example.login;

import com.example.login.entity.Member;
import com.example.login.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoginApplication {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Bean
    public void addTempUser() {
        String encoded = bCryptPasswordEncoder.encode("asdasdASD!@#");
        memberRepository.save(new Member(
                "umjunsik", encoded, "sd@sd.sd", "엄준식"));
    }

}
