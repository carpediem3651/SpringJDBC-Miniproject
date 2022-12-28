package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
