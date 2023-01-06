package com.example.board.controller;

import com.example.board.dto.Board;
import com.example.board.dto.LoginInfo;
import com.example.board.service.BoardService;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시물 목록을 보여준다.
    // classpath:/template/list.html
    @GetMapping("/")
    public String list(@RequestParam(name = "page", defaultValue = "1") int pagwStr, HttpSession session, Model model) {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        model.addAttribute("loginInfo", loginInfo);

        int totalCount = boardService.getTotalCount(); // 11
        List<Board> list = boardService.getBoards(page); // page가 1,2,3,4 ....
        int pageCount = totalCount / 10; // 1
        if(totalCount % 10 > 0){ // 나머지가 있을 경우 1page를 추가
            pageCount++;
        }
        int currentPage = page;
//        System.out.println("totalCount : " + totalCount);
//        for(Board board : list){
//            System.out.println(board);
//        }
        model.addAttribute("list", list);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", currentPage);
        return "list";
    }

    @GetMapping("/board")
    public String board(@RequestParam("boardId") int boardId, Model model) {
        System.out.println("id : "+id);

//        id에 해당하는 게시물을 읽어온다.
//        id에 해당하는 게시물의 조회수를 1증가한다.
        Board board = boardService.getBoard(boardId);
        model.addAttribute("board", board);
        return "board";
    }

    @GetMapping("/writeForm")
    public String writeForm(HttpSession session, Model model){
//        로그인 한 사용자만 글을 써야한다.
//        세션에서 로그인 한 정보를 읽어들인다.
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
//        로그인을 하지 않았다면(=세션에 로그인 정보가 없으면), 로그인(/loginform)으로 자동이동시킨다.
        if(loginInfo == null) {
            return "redirect:/loginform";
        }
        model.addAttribute("loginInfo", loginInfo);
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpSession session
    ) {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
//        로그인을 하지 않았다면(=세션에 로그인 정보가 없으면), 로그인(/loginform)으로 자동이동시킨다.
        if(loginInfo == null) {
            return "redirect:/loginform";
        }
//        로그인한 사용자만 글을 써여한다.
//        세션에서 로그인한 정보를 읽어들인다. 로그인을 하지 않았다면 리스트보기로 자동 이동 시킨다.
        System.out.println("title : " + title);
        System.out.println("content :" + content);
//        로그인 한 회원 정보 + 제목, 내용을 저장한다.

        boardService.addBoard(loginInfo.getUserId(), title, content);

        return "redirect:/"; // 리스트 보기로 리다이렉트한다.
    }

    @GetMapping("/delete")
    public String delete(
        @RequestParam("boardId") int boardId,
        HttpSession session
    ) {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        if(loginInfo == null) {
            return "redirect:/loginform";
        }

        boardService.deleteBoard(loginInfo.getUserId(), boardId);
        return "redirect:/";
    }
}
