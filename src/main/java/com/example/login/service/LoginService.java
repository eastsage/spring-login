package com.example.login.service;

import com.example.login.entity.Member;
import com.example.login.repository.MemberRepository;
import com.example.login.security.SecurityUser;
import com.example.login.utils.JwtUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs = 1000 * 60 * 30l;
    private final MemberRepository memberRepository;

    public Member login(String userId, String password) {
        return memberRepository.findFirstByUserId(userId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    public String loginJwt(String userId, String password) {
        return JwtUtil.createJwt(userId, secretKey, expiredMs);
    }

//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//
//        Optional<Member> findMember = memberRepository.findFirstByUserId(userId);
//
//        if (!findMember.isPresent()) throw new UsernameNotFoundException("존재하지 않는 username 입니다.");
//
//        log.info("loadUserByUsername member.userId = {}", userId);
//
//        return new SecurityUser(findMember.get());
//    }
}
