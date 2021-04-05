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

    public String getForCurrentWeek() {
        return getForWeek(currentWeek);
    }

    public String getForWeek(int weekNumber) {
        if (weekNumber < 1 || weekNumber > 4) {
            throw new WeekNumberDoesNotExistsException("Week number should be in range [1; 4]");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Week:").append(currentWeek).append("\n");
        for (int i = 0; i < scheduleList.size(); i++) {
            sb.append("------------------------\n");
            sb.append(scheduleList.get(i).get(0).getWeekDay()).append("\n");
            sb.append("------------------------\n");
            for (int j = 0; j < scheduleList.get(i).size(); j++) {
                if (scheduleList.get(i).get(j).getWeeks().contains(weekNumber)){
                    sb.append(scheduleList.get(i).get(j)).append("\n").append("\n");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
