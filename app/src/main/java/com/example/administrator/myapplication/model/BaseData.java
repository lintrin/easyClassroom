package com.example.administrator.myapplication.model;

public class BaseData<T> {
    private T body;
    private HeadData head;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public HeadData getHead() {
        return head;
    }

    public void setHead(HeadData head) {
        this.head = head;
    }
}
