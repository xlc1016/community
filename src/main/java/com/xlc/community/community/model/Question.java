package com.xlc.community.community.model;


import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @author :xlc
* @date: 2020-5-26
* @description: 问题发布实体
*/
@Repository
@Table(name = "question")
public class Question {


    @Id
    private Integer id;
    @Column(name = "TITLE")
    private String  title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATOR")
    private Integer creator;
    @Column(name = "GMT_CREATE")
    private Long gmtCreate;
    @Column(name = "GMT_MODIFIED")
    private Long gmtModified;
    @Column(name = "VIEW_COUNT")
    private Integer viewCount;
    @Column(name = "COMMENT_COUNT")
    private Integer commentCount;
    @Column(name = "LIKE_COUNT")
    private Integer likeCount;
    @Column(name = "TAG")
    private String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
