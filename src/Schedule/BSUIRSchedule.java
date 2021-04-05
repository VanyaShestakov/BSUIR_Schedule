package Schedule;

import JSON.JSONParser;
import JSON.JSONRequester;
import Schedule.BSUIRLesson;
import org.json.JSONObject;

import java.util.ArrayList;

public class BSUIRSchedule {
    private final ArrayList<ArrayList<BSUIRLesson>> scheduleList;
    private final int currentWeek;

    public BSUIRSchedule(String groupNumber) {
        JSONRequester requester = new JSONRequester();
        String jsonData = requester.getGroupSchedule(groupNumber);
        currentWeek = requester.getCurrentWeek();
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONParser parser = new JSONParser();
        scheduleList = parser.parseToList(jsonObject);
    }


    @Override
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Week:").append(currentWeek).append("\n");
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
