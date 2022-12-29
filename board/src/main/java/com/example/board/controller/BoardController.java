package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
    // 게시물 목록을 보여준다.
    @GetMapping("/")
    public String list() {
        return "list";
    }

    @GetMapping("/board")
    public String board(@RequestParam("id") int id) {
        return "board";
    }

    @GetMapping("/writeForm")
    public String writeForm(){
//        로그인 한 사용자만 글을 써야한다.
//        세션에서 로그인 한 정보를 읽어들인다. 로그인을 하지 않았다면 list보기로 자동이동.
//        로그인 한 정보 +
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
//        로그인한 사용자만 글을 써여한다.
//        세션에서 로그인한 정보를 읽어들인다. 로그인을 하지 않았다면 리스트보기로 자동 이동 시킨다.
        System.out.println("title : " + title);
        System.out.println("content :" + content);
//        로그인 한 회원 정보 + 제목, 내용을 저장한다.

        return "redirect:/"; // 리스트 보기로 리다이렉트한다.

    }
}
