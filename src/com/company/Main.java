package com.company;

import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    static final int CONNECTION_TIMEOUT = 10;

    private static final String SCHEDULES = "schedules";
    private static final String WEEKDAY = "weekDay";
    private static final String SCHEDULE = "schedule";
    private static final String SUBJECT = "subject";
    private static final String LESSON_TIME = "lessonTime";

    private static final int MONDAY = 0;
    private static final int TUESDAY = 1;
    private static final int WEDNESDAY = 2;
    private static final int THURSDAY = 3;
    private static final int FRIDAY = 4;
    private static final int SATURDAY = 5;
    private static final int SUNDAY = 6;

    private static final int FIRST_PAIR = 0;
    private static final int SECOND_PAIR = 1;
    private static final int THIRD_PAIR = 2;
    private static final int FOURTH_PAIR = 3;
    private static final int FIFTH_PAIR = 4;



    public static void main(String[] args) {
        BSUIRSchedule schedule051007 = new BSUIRSchedule("051007");
        System.out.println(schedule051007.getForWeekDay(TUESDAY));
    }


}
