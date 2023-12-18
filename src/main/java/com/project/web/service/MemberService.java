package com.project.web.service;

import com.project.web.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member registerMember(Member member);

    List<Member> showAllMembers();

    String loginCheck(String id, String pw);

    Optional<Member> findOne(Long num);


}
