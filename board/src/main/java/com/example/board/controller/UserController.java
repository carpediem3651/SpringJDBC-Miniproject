package com.example.board.controller;

import com.example.board.dto.LoginInfo;
import com.example.board.dto.User;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/userRegForm")
    public String userRegForm() {
        return "userRegForm";
    }

    @PostMapping("/userReg")
    public String userReg (@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password)
    {
        System.out.println("name : " + name);
        System.out.println("email : " + email);
        System.out.println("password : " + password);

        userService.addUser(name, email, password);
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/loginform")
    public String loginform() {
        return "loginform";
    }

    @PostMapping("/login")
    public String login(
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession httpSession) //Spring이 자동으로 session을 처리하는 HttpSession객체를 넣어준다.
    {
//        email에 해당하는 회원 정보를 읽어온 후
//        아이디와 비밀번호가 맞다면 세션에 회원정보를 저장한다.
        System.out.println("email : " + email);
        System.out.println("password : " + password);

        try {
            User user = userService.getUser(email);
            if(user.getPassword().equals(password)) {
                System.out.println("암호가 같습니다.");
                LoginInfo loginInfo = new LoginInfo(user.getUserId(), user.getEmail(), user.getEmail());
                httpSession.setAttribute("loginInfo", loginInfo);
                System.out.println("세션에 로그인 정보가 저장된다.");
            } else {
                throw new RuntimeException("암호가 일치하지 않음");
            }
        } catch (Exception e) {
            return "redirect:/loginform?error=true";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
//        세션에서 회원정보를 삭제한다.
        httpSession.removeAttribute("loginInfo");
        return "redirect:/";
    }
}
