package com.project.web;


import com.project.web.domain.Member;
import com.project.web.repository.MemberRepository;
import com.project.web.service.MemberService;
import com.project.web.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberServiceImpl memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setId("testI2d");
        member.setPw("testP2222w");
        member.setName("testName");
        member.setEmail("test@test.com");

        Member registerMember = memberService.registerMember(member);
        //when
        Optional<Member> newMember = memberService.findOne(registerMember.getNum());
        //then
        Assertions.assertThat(newMember).isPresent();
        Assertions.assertThat(newMember.get().getNum()).isEqualTo(registerMember.getNum());


    }
}
