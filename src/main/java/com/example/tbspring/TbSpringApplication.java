package com.example.tbspring;

import com.example.tbspring.dao.*;
import com.example.tbspring.domain.User;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class TbSpringApplication {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("applicationContext.xml");

        SpringApplication.run(TbSpringApplication.class, args);

        User nUser = new User();
        nUser.setId("ramen");
        nUser.setName("이중혁");
        nUser.setPassword("indivi");

        UserDao userDao = new UserDao();

        userDao.deleteAll();

        System.out.println(nUser.getId() + " N등록 성공");

//        User user1 = userDao.get(nUser.getId());
//        System.out.println("NUser name : " + user1.getName());
//        System.out.println("success search NUser Info");
//------------------------------------------------
//        UserDao userDao1 = new DaoFactory().userDao();
//        userDao1.deleteAll();
//        User dUser = new User();
//        dUser.setId("Dramen");
//        dUser.setName("이중혁");
//        dUser.setPassword("indivi");
//        userDao1.add(dUser);
//
//        System.out.println(dUser.getId() + " D등록 성공");

//        User dUser1 = userDao.get(dUser.getId());
//        System.out.println("D User name : " + dUser1.getName());
//        System.out.println("success search DUser Info");
        //----------------

//        System.out.println(userDao + "userDao");
//        System.out.println(userDao1 + "userDao1");


    }
}