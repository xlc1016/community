package com.xlc.community.community.controller;
/**
* @author :xlc
* @date: 2020-3-19
* @description:获取 get github user information
*/

public class GithubUser {
    private Long id; // gitHub id
    private String name;
    private String bio; // gitbub 个人描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
