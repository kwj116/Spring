package com.project.web.service;

import com.project.web.domain.Member;
import com.project.web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public Member registerMember(Member member) {

        System.out.println("회원가입이 완료되었습니다.");
        return memberRepository.save(member);
    }

    //회원 정보를 db에 저장
    @Override
    public List<Member> showAllMembers(){
        return memberRepository.findAll();
    }//모든 회원정보를 가져옴

    @Override
    public String loginCheck(String id, String pw) {
        if ("admin".equals(id) && "1234".equals(pw)){
            return "admin";
        }
        Optional<Member> byIdAndPw = memberRepository.findByIdAndPw(id, pw);
        if (byIdAndPw.isPresent()){
            return "success";
        }
        else{
            return "error";
        }
    }//로그인 체크 함수

    @Override
    public Optional<Member> findOne(Long num) {
        return memberRepository.findById(num);
    }

//    public boolean isIdDuplicate(String id) {
////        return memberRepository.findById(id).isPresent();;
//    }



}
