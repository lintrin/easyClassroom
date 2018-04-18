package com.example.administrator.myapplication.model;

import java.io.Serializable;

public class News implements Serializable {

    /**
     * id : 44
     * title : 五一放假
     * content : 放假日期从xxxx-xxxx，请同学们在家做好作业
     * createId : 1
     * createUserName : Yao
     * createTime : 1522029352000
     */

    private int id;
    private String title;
    private String content;
    private int createId;
    private String createUserName;
    private long createTime;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createId=" + createId +
                ", createUserName='" + createUserName + '\'' +
                ", createTime=" + createTime +
                ", date='" + date + '\'' +
                '}';
    }
}
