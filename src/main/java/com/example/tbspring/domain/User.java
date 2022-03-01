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
    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if(nextLevel == null) {
            throw  new IllegalArgumentException(this.level + "은 업그레이드가 불가능 합니다.");
        } else {
            this.level = nextLevel;
        }
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

    public boolean canUpgradeLevels() {
        switch(this.level) {
            case BASIC : return (this.login >= 50);
            case SILVER : return (this.recommend >= 30);
            case GOLD : return false;
            default : throw new IllegalArgumentException("Unknown Level");
        }
    }
}
