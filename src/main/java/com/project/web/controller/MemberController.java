package com.project.web.controller;

import com.project.web.domain.Member;
import com.project.web.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Controller
public class MemberController {

    private final MemberServiceImpl memberService;

    @Autowired
    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/signUp")
    @Transactional
    public String signUp(MemberForm memberForm, Model model) {

        Member member = new Member();
        member.setId(memberForm.getId());
        member.setPw(memberForm.getPw());
        member.setName(memberForm.getName());
        member.setEmail(memberForm.getEmail());

        memberService.registerMember(member);
        return "redirect:/";
    }

    @GetMapping("/members/all")
    public String showAllMembers(Model model){
        List<Member> members = memberService.showAllMembers();
        model.addAttribute("members",members);
        return "memberList";
    }

    @PostMapping("/members/loginCheck")
    public String loginCheck(@RequestParam("id") String id,
                             @RequestParam("pw") String pw,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request
                             ){
        String result = memberService.loginCheck(id, pw);

        if ("admin".equals(result)) {
            System.out.println("관리자 모드");
            List<Member> members = memberService.showAllMembers();
            model.addAttribute("members", members);
            model.addAttribute("hideAuthPageText", "remove");
            System.out.println(result);
            return "/admin";
        } else if (!"error".equals(result)) {
            System.out.println("로그인 성공 - 일반 회원: " + result);

            // 여기에서 추가적으로 처리할 내용을 작성
            // result에는 회원의 이름이 들어있습니다.
            // 예를 들어, model.addAttribute("userName", result);와 같이 모델에 추가할 수 있습니다.

            return "redirect:/";
        }

        System.out.println("로그인 실패");
        redirectAttributes.addFlashAttribute("loginError", "아이디 또는 비밀번호를 확인 해주세요.");
        return "redirect:/login";
    }




}

