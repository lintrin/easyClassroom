package com.example.administrator.myapplication.model;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class Homework {


    /**
     * id : 45
     * homeworkUrl : http://115.159.192.69:9000/class/homework/第一次作业_20180421081249/周杰伦吹胡萝卜.jpg
     * markHomeworkUrl : http://115.159.192.69:9000/class/mark_homework/周杰伦吹胡萝卜.jpg_20180421093353/周杰伦吹胡萝卜2.jpg
     * homeworkName : 周杰伦吹胡萝卜.jpg
     * uploadDate : 20180421093353
     * createDate : 20180421093353
     * modifyDate : null
     * homeworkOuterId : 1
     * score : 4.0
     * comment : 继续努力
     * uploadName : CCC
     * courseId : 19
     * userId : 15
     * idNumber : 201400001
     */

    private int id;
    private String homeworkUrl;
    private String markHomeworkUrl;
    private String homeworkName;
    private String uploadDate;
    private String createDate;
    private Object modifyDate;
    private int homeworkOuterId;
    private String score;
    private String comment;
    private String uploadName;
    private int courseId;
    private int userId;
    private String idNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHomeworkUrl() {
        return homeworkUrl;
    }

    public void setHomeworkUrl(String homeworkUrl) {
        this.homeworkUrl = homeworkUrl;
    }

    public String getMarkHomeworkUrl() {
        return markHomeworkUrl;
    }

    public void setMarkHomeworkUrl(String markHomeworkUrl) {
        this.markHomeworkUrl = markHomeworkUrl;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Object modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getHomeworkOuterId() {
        return homeworkOuterId;
    }

    public void setHomeworkOuterId(int homeworkOuterId) {
        this.homeworkOuterId = homeworkOuterId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
