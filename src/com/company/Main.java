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
        String jsonData = getJsonData("051007");
        JSONObject jsonObject = new JSONObject(jsonData);
        StringBuilder schedule = new StringBuilder();
      for (int currDay = 0; currDay < jsonObject.getJSONArray(SCHEDULES).length(); currDay++) {

          schedule.append("-----------------------\n").
                  append(jsonObject.getJSONArray(SCHEDULES).
                          getJSONObject(currDay).
                          getString(WEEKDAY)).append("\n-----------------------\n");

          for (int currPair = 0; currPair < jsonObject.getJSONArray(SCHEDULES).getJSONObject(currDay).getJSONArray(SCHEDULE).length(); currPair++) {
              schedule.append(jsonObject.getJSONArray(SCHEDULES).
                      getJSONObject(currDay).
                      getJSONArray(SCHEDULE).
                      getJSONObject(currPair).
                      getString(SUBJECT)).append(" (").
                      append(jsonObject.getJSONArray(SCHEDULES).
                              getJSONObject(currDay).
                              getJSONArray(SCHEDULE).
                              getJSONObject(currPair).
                              getString("lessonType")).append(") ").
                      append(jsonObject.getJSONArray(SCHEDULES).
                              getJSONObject(currDay).
                              getJSONArray(SCHEDULE).
                              getJSONObject(currPair).
                              getString(LESSON_TIME)).append("\n");

              try {
                  schedule.append(jsonObject.getJSONArray(SCHEDULES).
                          getJSONObject(currDay).
                          getJSONArray(SCHEDULE).
                          getJSONObject(currPair).
                          getJSONArray("employee").getJSONObject(0).getString("fio")).append("\n");
              } catch (JSONException e) {
                  schedule.append("--\n");
              }
              schedule.append("\n");
          }
      }

        System.out.println(schedule);

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
