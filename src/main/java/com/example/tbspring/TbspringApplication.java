package com.example.tbspring;

import com.example.tbspring.dao.DUserDao;
import com.example.tbspring.dao.NUserDao;
import com.example.tbspring.dao.UserDao;
import com.example.tbspring.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TbspringApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SpringApplication.run(TbspringApplication.class, args);

        UserDao userDao = new NUserDao();

        User nUser = new User();
        nUser.setId("ramen");
        nUser.setName("이중혁");
        nUser.setPassword("indivi");

        userDao.add(nUser);

        System.out.println(nUser.getId() + " N등록 성공");

        User user1 = userDao.get(nUser.getId());
        System.out.println("NUser name : " + user1.getName());
        System.out.println("success search NUser Info");
//------------------------------------------------

        UserDao userDao1 = new DUserDao();

        User dUser = new User();
        dUser.setId("Dramen");
        dUser.setName("이중혁");
        dUser.setPassword("indivi");

        userDao1.add(dUser);

        System.out.println(dUser.getId() + " D등록 성공");

        User dUser1 = userDao.get(dUser.getId());
        System.out.println("D User name : " + dUser1.getName());
        System.out.println("success search DUser Info");
    }

}
