package com.example.administrator.myapplication.model;

import java.io.Serializable;

public class Course implements Serializable{


    /**
     * id : 13
     * createDate : 20180326113627
     * modifyDate : null
     * name : React前端学习
     * briefIntroduction : 基础课程
     * classroomId : null
     * beginPeriod : 2017
     * endPeriod : 2018
     * term : 1
     * day : 6
     * lesson : 16
     * code : GTUJW3
     * classBegin : 3
     * classEnd : 4
     * buildingNumber : 10
     * classroom : 405
     * teachers : null
     */

    private int id;
    private String createDate;
    private String modifyDate;
    private String name;
    private String briefIntroduction;
    private String classroomId;
    private String beginPeriod;
    private String endPeriod;
    private int term;
    private int day;
    private int lesson;
    private String code;
    private int classBegin;
    private int classEnd;
    private int buildingNumber;
    private String classroom;
    private String teachers;

    public Course() {
    }

    public Course(String name, String beginPeriod, String endPeriod, int term) {
        this.name = name;
        this.beginPeriod = beginPeriod;
        this.endPeriod = endPeriod;
        this.term = term;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(String beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getClassBegin() {
        return classBegin;
    }

    public void setClassBegin(int classBegin) {
        this.classBegin = classBegin;
    }

    public int getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(int classEnd) {
        this.classEnd = classEnd;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }
}
