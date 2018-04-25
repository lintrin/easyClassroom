package com.example.administrator.myapplication.api;

/**
 * @author by JingQ on 2018/4/25.
 */

public class CheckInApi {

    /**
     * 基础URL
     */
    private static final String BASE_CHECKIN_URL = BaseUrl.baseUrl + "checkIn/";

    /**
     *
     */
    public static final String CHECKIN_ADD = BASE_CHECKIN_URL + "add";

    /**
     *
     */
    public static final String CHECKIN_LIST_BY_COURSE_RECORD_ID = BASE_CHECKIN_URL + "getListByCourseRecordId?courseRecordId=";

    /**
     *
     */
    public static final String CHANGE_STATUS = BASE_CHECKIN_URL + "changeStatus";
}
