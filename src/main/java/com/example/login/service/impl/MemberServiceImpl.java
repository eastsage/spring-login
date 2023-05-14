package com.example.login.service.impl;

import com.example.login.domain.dto.MemberDto;
import com.example.login.domain.entity.Member;
import com.example.login.domain.entity.Role;
import com.example.login.repository.MemberRepository;
import com.example.login.repository.RoleRepository;
import com.example.login.service.MemberService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("userService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(Member member){

        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        member.setUserRoles(roles);
        memberRepository.save(member);
    }

    @Transactional
    @Override
    public void modifyUser(MemberDto memberDto){

        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberDto, Member.class);

        if(memberDto.getRoles() != null){
            Set<Role> roles = new HashSet<>();
            memberDto.getRoles().forEach(role -> {
                Role r = roleRepository.findByRoleName(role);
                roles.add(r);
            });
            member.setUserRoles(roles);
        }
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(member);

    }

    @Transactional
    public MemberDto getUser(Long id) {

        Member member = memberRepository.findById(id).orElse(new Member());
        ModelMapper modelMapper = new ModelMapper();
        MemberDto memberDto = modelMapper.map(member, MemberDto.class);

        List<String> roles = member.getUserRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());

        memberDto.setRoles(roles);
        return memberDto;
    }

    @Transactional
    public List<Member> getUsers() {
        return memberRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        memberRepository.deleteById(id);
    }
}