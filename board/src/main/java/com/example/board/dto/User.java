package com.example.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor //기본생성자
@ToString //toString메소드를 자동으로 만들어준다. toString메소드는 객체를 문자열로 출력해준다.
public class User {
    private int userId;
    private String email;
    private String name;
    private String password;
    private LocalDateTime regdate;
}
