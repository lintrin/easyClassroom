package com.example.administrator.myapplication.model;

public class TeacherClass {
    private int id;
    private int signCount;
    private int studentCount;
    private String signCode;

    public TeacherClass(int id, int signCount, int studentCount, String signCode) {
        this.id = id;
        this.signCount = signCount;
        this.studentCount = studentCount;
        this.signCode = signCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSignCount() {
        return signCount;
    }

    public void setSignCount(int signCount) {
        this.signCount = signCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }
}
