package com.example.administrator.myapplication.model.impl;

import library.http.BaseRequest;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.server.CourseServer;

public class CourseModel {
    private static CourseModel instance;
    private Course course;

    public static CourseModel getInstance() {
        if (instance==null){
            synchronized (CourseModel.class){
                if (instance==null)
                    instance = new CourseModel();
            }
        }
        return instance;
    }


    public void  addCourse(Course course, BaseRequest.OnRequestListener listener){
        CourseServer.addCourse(course,listener);
    }

    public void getCourse(int id, BaseRequest.OnRequestListener listener){
        CourseServer.getCourse(id,listener);
    }

    public void getCourseList(BaseRequest.OnRequestListener listener){
        CourseServer.getCourseList(listener);
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}
