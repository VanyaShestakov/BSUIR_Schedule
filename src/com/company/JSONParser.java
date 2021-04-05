package com.company;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    private static final String SCHEDULES = "schedules";
    private static final String WEEKDAY = "weekDay";
    private static final String SCHEDULE = "schedule";
    private static final String SUBJECT = "subject";
    private static final String LESSON_TIME = "lessonTime";
    private static final String EMPLOYEE = "employee";
    private static final String FIO = "fio";
    private static final String LESSON_TYPE = "lessonType";



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


    public JSONParser() {

    }

    public ArrayList<ArrayList<Lesson>> parseToList(JSONObject jsonObject) {
        ArrayList<ArrayList<Lesson>> weekDays = new ArrayList<>();
        for (int currDay = 0; currDay < jsonObject.getJSONArray(SCHEDULES).length(); currDay++) {
            int pairsAmount = jsonObject.getJSONArray(SCHEDULES).
                    getJSONObject(currDay).
                    getJSONArray(SCHEDULE).length();

            ArrayList<Lesson> currPairs = new ArrayList<>();
            for (int currPair = 0; currPair < pairsAmount; currPair++) {
                String subjectName = jsonObject.
                        getJSONArray(SCHEDULES).
                        getJSONObject(currDay).
                        getJSONArray(SCHEDULE).
                        getJSONObject(currPair).
                        getString(SUBJECT);
                String time = jsonObject.
                        getJSONArray(SCHEDULES).
                        getJSONObject(currDay).
                        getJSONArray(SCHEDULE).
                        getJSONObject(currPair).
                        getString(LESSON_TIME);
                String teacher;
                try {
                    teacher = jsonObject.
                            getJSONArray(SCHEDULES).
                            getJSONObject(currDay).
                            getJSONArray(SCHEDULE).
                            getJSONObject(currPair).
                            getJSONArray(EMPLOYEE).
                            getJSONObject(0).
                            getString(FIO);
                } catch (JSONException e) {
                    teacher = "-";
                }
                String type = jsonObject.
                        getJSONArray(SCHEDULES).
                        getJSONObject(currDay).
                        getJSONArray(SCHEDULE).
                        getJSONObject(currPair).
                        getString(LESSON_TYPE);
                String weekDay = jsonObject.
                        getJSONArray(SCHEDULES).
                        getJSONObject(currDay).
                        getString(WEEKDAY);

                Lesson currLesson = new Lesson(subjectName, time, teacher, type, weekDay);
                currPairs.add(currLesson);
            }
            weekDays.add(currPairs);
        }
        return weekDays;
    }
}
