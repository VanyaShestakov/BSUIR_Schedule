package com.company;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class BSUIRSchedule {
    private ArrayList<ArrayList<Lesson>> scheduleList;

    public BSUIRSchedule(String groupNumber) {
        String jsonData = getJsonData(groupNumber);
        if (jsonData.length() == 0) {
            throw new JSONDataIsEmptyException("JSON data is empty");
        }
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONParser parser = new JSONParser();
        scheduleList = parser.parseToList(jsonObject);
    }

    private String getJsonData(String groupNumber) {
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

    @Override
    public String toString (){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scheduleList.size(); i++) {
            sb.append("------------------------\n");
            sb.append(scheduleList.get(i).get(0).getWeekDay()).append("\n");
            sb.append("------------------------\n");
            for (int j = 0; j < scheduleList.get(i).size(); j++) {
                sb.append(scheduleList.get(i).get(j)).append("\n").append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getForWeekDay(int weekDay) {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------\n");
        sb.append(scheduleList.get(weekDay).get(0).getWeekDay()).append("\n");
        sb.append("------------------------\n");
        for (int j = 0; j < scheduleList.get(weekDay).size(); j++) {
            sb.append(scheduleList.get(weekDay).get(j)).append("\n").append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

}
