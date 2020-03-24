package com.xlc.community.community.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

/**
* @author :xlc
* @date: 2020-3-20
* @description: user dao
*/
@Repository
@Table(name = "user")
public class User {

    @Id
    private Integer id;
    @Column(name = "name")
    private String  name;
    @Column(name="accountId")
    private String accountId;
    @Column(name="token")
    private String token;
    @Column(name="gmtCreate")
    private Date gmtCreate;
    @Column(name="gmtModified")
    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
