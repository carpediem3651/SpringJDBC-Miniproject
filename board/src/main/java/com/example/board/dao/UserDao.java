package com.example.board.dao;

import com.example.board.dto.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository //스프링이 관리하는 Bean
public class UserDao {

    @Transactional
    public User addUser(String email, String name, String password) {
//        insert into user (email, name, password, regdate) value (?, ?, ?, now());
//        SELECT LAST_INSERT_ID();
        return null;
    }

    @Transactional
    public void mappingUserRole(int userId) {
//        insert into user_role(user_id, role_id) values (?, 1);
    }
}
