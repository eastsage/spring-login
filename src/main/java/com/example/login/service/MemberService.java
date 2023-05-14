package com.example.login.service;

import com.example.login.domain.dto.MemberDto;
import com.example.login.domain.entity.Member;
import java.util.List;

public interface MemberService {

    void createUser(Member member);

    void modifyUser(MemberDto memberDto);

    List<Member> getUsers();

    MemberDto getUser(Long id);

    void deleteUser(Long idx);
}
