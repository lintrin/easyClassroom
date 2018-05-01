package com.example.administrator.myapplication.model;

import java.io.Serializable;

/**
 * 选修了某门课程的用户
 *
 * @author by JingQ on 2018/5/1.
 */

public class CourseUser implements Serializable {

    private Integer courseId;

    private Integer userId;

    private Integer userType;

    private String idNumber;

    private String userName;


    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CourseUser{" +
                "courseId=" + courseId +
                ", userId=" + userId +
                ", userType=" + userType +
                ", idNumber='" + idNumber + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
