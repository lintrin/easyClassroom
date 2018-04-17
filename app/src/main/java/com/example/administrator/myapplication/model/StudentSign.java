package com.example.administrator.myapplication.model;

public class StudentSign {
    private String signTime;
    private String courseName;
    private String signState;

    public StudentSign(String signTime, String courseName, String signState) {
        this.signTime = signTime;
        this.courseName = courseName;
        this.signState = signState;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState;
    }
}
