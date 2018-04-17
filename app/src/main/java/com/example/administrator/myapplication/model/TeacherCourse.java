package com.example.administrator.myapplication.model;

import java.io.Serializable;

public class TeacherCourse implements Serializable{

    private String className;
    private String season;

    public TeacherCourse(String className, String season) {
        this.className = className;
        this.season = season;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }


}
