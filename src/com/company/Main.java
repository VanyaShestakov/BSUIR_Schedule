package com.company;

import org.json.HTTP;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
        String jsonData = getJsonData("051007");
        JSONObject jsonObject = new JSONObject(jsonData);
        StringBuilder mondaySchedule = new StringBuilder();
        mondaySchedule.append(jsonObject.getString("todayDate")).append("\n").
        append(jsonObject.getJSONArray(SCHEDULES).
            getJSONObject(0).
            getString(WEEKDAY)).append("\n").
        append(jsonObject.getJSONArray(SCHEDULES).
            getJSONObject(0).
            getJSONArray(SCHEDULE).
            getJSONObject(FIRST_PAIR).
            getString(SUBJECT)).append("\n").
        append(jsonObject.getJSONArray(SCHEDULES).
            getJSONObject(0).
            getJSONArray(SCHEDULE).
            getJSONObject(FIRST_PAIR).
            getString(LESSON_TIME)).append("\n").
        append(jsonObject.getJSONArray(SCHEDULES).
            getJSONObject(0).
            getJSONArray(SCHEDULE).
            getJSONObject(FIRST_PAIR).
            getJSONArray("employee").getJSONObject(0).getString("lastName")).append("\n");


        System.out.println(mondaySchedule);

    }

    static String getJsonData(String groupNumber) {
        try {
            URL url = new URL("https://journal.bsuir.by/api/v1/studentGroup/schedule?studentGroup=" + groupNumber);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder jsonData = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    jsonData.append(line);
                }

                return jsonData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
