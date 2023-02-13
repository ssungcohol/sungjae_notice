package com.example.sungjae_notice.controller;

import com.example.notice_test.dto.LoginRequestDto;
import com.example.notice_test.dto.SignupRequestDto;
import com.example.notice_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    // templates 에 있는 html 반환 컨트롤러
    // 구현 해도 그만 안해도 그만 api 작성 시 코드 구현 필요

    private final UserService userService; // 의존성 주입 & UserService와 연결

    // 회원가입 부분 Client 로부터 값 받음
    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    // =======================================================
    @PostMapping("/signup")
    public String signup(SignupRequestDto signupRequestDto) {
        //@RequestBody 사용시에는 Json 형식으로 해야한다.
        // 이 상황의 경우에는 text 즉, x-www...이 형식에 k-v 로 입력해야지 값이 들어옴
        userService.signup(signupRequestDto);
        return "redirect:/api/user/login";
    }

    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto) {
        userService.login(loginRequestDto);
        return "redirect:/api/Notice";
    }
}
