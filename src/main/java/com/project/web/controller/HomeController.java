package com.project.web.controller;

import com.project.web.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signUp")
    public String showSignUpForm(){
        System.out.println("회원 가입 폼");
        return "signUp";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("로그인 폼");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        System.out.println("로그아웃");
        return null;
    }


}
