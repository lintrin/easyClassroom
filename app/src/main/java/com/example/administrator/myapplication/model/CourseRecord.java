package com.example.administrator.myapplication.model;

import java.io.Serializable;

/**
 * 课时
 *
 * @author by JingQ on 2018/4/27.
 */

public class CourseRecord implements Serializable{

    private Integer id;

    private String courseName;

    private String courseFileUrl;

    private Integer courseId;

    private String date;

    private String uploadEndTime;

    private Integer classBegin;

    private Integer classEnd;

    private Integer classroomId;

    /**
     * 签到识别码
     */
    private String checkCode;

    /**
     * 是否签到
     */
    private Boolean isCheckIn;

    /**
     * 全部学生人数
     */
    private Integer allStudentCount;

    /**
     * 已签到人数
     */
    private Integer checkCount;

    /**
     * 上课时间 yyyy-MM-dd
     */
    private String classTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseFileUrl() {
        return courseFileUrl;
    }

    public void setCourseFileUrl(String courseFileUrl) {
        this.courseFileUrl = courseFileUrl;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUploadEndTime() {
        return uploadEndTime;
    }

    public void setUploadEndTime(String uploadEndTime) {
        this.uploadEndTime = uploadEndTime;
    }

    public Integer getClassBegin() {
        return classBegin;
    }

    public void setClassBegin(Integer classBegin) {
        this.classBegin = classBegin;
    }

    public Integer getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(Integer classEnd) {
        this.classEnd = classEnd;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Boolean getCheckIn() {
        return isCheckIn;
    }

    public void setCheckIn(Boolean checkIn) {
        isCheckIn = checkIn;
    }

    public Integer getAllStudentCount() {
        return allStudentCount;
    }

    public void setAllStudentCount(Integer allStudentCount) {
        this.allStudentCount = allStudentCount;
    }

    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }
}
