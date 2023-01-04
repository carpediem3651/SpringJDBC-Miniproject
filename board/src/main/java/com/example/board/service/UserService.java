package com.example.board.service;

import com.example.board.dao.UserDao;
import com.example.board.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //스프링이 관리하는 Bean
@RequiredArgsConstructor //lombok이 final 필드를 초기화하는 생성자를 자동으로 생성
public class UserService {
//    @Autowired
//    UserDao userdao;

//    생성자주입: Spring이 UserService를 Bean으로 생성할 때 스프링 콘테이너에서 UserDao에 대한 Bean이 있는지보고
//    Bean을 UserService 생성자에 매개변수로 주입한다.
    private final UserDao userDao;
//    public UserService(UserDao userDao) {
//        this.userDao = userDao;
//    }

    // 보통 서비스에서는 @Transactional을 붙여 하나의 Tx로 처리한다.
    // Spring boot는 Tx를 처리하는 Tx관리자를 가지고 있다.
    @Transactional
    public User addUser(String name, String email, String password) {
        User user = userDao.addUser(email, name, password);
        userDao.mappingUserRole(user.getUserId()); //권한부여
        return user;
    }

    @Transactional
    public User getUser(String email) {
        return userDao.getUser(email);
    }
}
