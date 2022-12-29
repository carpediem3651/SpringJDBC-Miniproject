package com.example.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor //기본생성자
public class User {
    private int userId;
    private String email;
    private String name;
    private String password;
    private String regdate;
}
