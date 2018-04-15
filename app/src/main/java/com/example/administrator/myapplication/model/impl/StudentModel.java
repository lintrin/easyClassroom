package com.example.administrator.myapplication.model.impl;

import com.example.administrator.myapplication.model.Student;

public class StudentModel {
    private static StudentModel instance;
    private Student student;

    public static StudentModel getInstance() {
        if (instance==null){
            synchronized (StudentModel.class){
                if (instance==null)
                    instance = new StudentModel();
            }
        }
        return instance;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
