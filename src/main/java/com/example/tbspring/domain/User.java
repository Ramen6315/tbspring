package com.example.tbspring.domain;

import com.example.tbspring.Level;

public class User {
    private String id;
    private String name;
    private String password;
    private int login;
    private int recommend;
    private Level level;

    public User() {
    }

    public User(String id, String name, String password, int login, int recommend, Level level) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
        this.recommend = recommend;
        this.level = level;
    }

    public boolean canUpgradeSilver() {
        return this.level == Level.BASIC && this.login >= 50;
    }

    public boolean canUpgradeGold() {
        return this.level == Level.SILVER && this.recommend >= 30;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getLogin() {
        return login;
    }

    public int getRecommend() {
        return recommend;
    }

    public Level getLevel() {
        return level;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
