package com.xlc.community.community.model;

import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Date;

@Repository
@Table(name = "user")
public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.accountId
     *
     * @mbg.generated
     */
    private String accountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.token
     *
     * @mbg.generated
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.gmtCreate
     *
     * @mbg.generated
     */
    private Date gmtcreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.gmtModified
     *
     * @mbg.generated
     */
    private Date gmtmodified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.avatarUrl
     *
     * @mbg.generated
     */
    private String avatarurl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.accountId
     *
     * @return the value of user.accountId
     *
     * @mbg.generated
     */
    public String getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.accountId
     *
     * @param accountid the value for user.accountId
     *
     * @mbg.generated
     */
    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.token
     *
     * @return the value of user.token
     *
     * @mbg.generated
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.token
     *
     * @param token the value for user.token
     *
     * @mbg.generated
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.gmtCreate
     *
     * @return the value of user.gmtCreate
     *
     * @mbg.generated
     */
    public Date getGmtcreate() {
        return gmtcreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.gmtCreate
     *
     * @param gmtcreate the value for user.gmtCreate
     *
     * @mbg.generated
     */
    public void setGmtcreate(Date gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.gmtModified
     *
     * @return the value of user.gmtModified
     *
     * @mbg.generated
     */
    public Date getGmtmodified() {
        return gmtmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.gmtModified
     *
     * @param gmtmodified the value for user.gmtModified
     *
     * @mbg.generated
     */
    public void setGmtmodified(Date gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.avatarUrl
     *
     * @return the value of user.avatarUrl
     *
     * @mbg.generated
     */
    public String getAvatarurl() {
        return avatarurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.avatarUrl
     *
     * @param avatarurl the value for user.avatarUrl
     *
     * @mbg.generated
     */
    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }
}