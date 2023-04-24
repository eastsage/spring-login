//package com.example.login.service;
//
//import com.example.login.entity.Member;
//import com.example.login.repository.MemberRepository;
//import com.example.login.security.SecurityUser;
//import java.util.Optional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Member> findMember = memberRepository.findByUserId(username);
//        log.info("loadUserByUsername member.findMember = {}", findMember);
//        if (!findMember.isPresent()) throw new UsernameNotFoundException("존재하지 않는 username 입니다.");
//
//        log.info("loadUserByUsername member.username = {}", username);
//
//        return new SecurityUser(findMember.get());
//    }
//}
