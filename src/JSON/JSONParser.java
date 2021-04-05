package JSON;

import Schedule.BSUIRLesson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class JSONParser {
    private static final String SCHEDULES = "schedules";
    private static final String WEEKDAY = "weekDay";
    private static final String SCHEDULE = "schedule";
    private static final String SUBJECT = "subject";
    private static final String LESSON_TIME = "lessonTime";
    private static final String EMPLOYEE = "employee";
    private static final String FIO = "fio";
    private static final String LESSON_TYPE = "lessonType";

    public JSONParser() {

    }

    public ArrayList<ArrayList<BSUIRLesson>> parseToList(JSONObject jsonObject) {
        ArrayList<ArrayList<BSUIRLesson>> weekDays = new ArrayList<>();
        for (int currDay = 0; currDay < jsonObject.getJSONArray(SCHEDULES).length(); currDay++) {
            int pairsAmount = jsonObject.getJSONArray(SCHEDULES).
                    getJSONObject(currDay).
                    getJSONArray(SCHEDULE).length();

            ArrayList<BSUIRLesson> currPairs = new ArrayList<>();
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
                JSONArray weeksJSON = jsonObject.
                        getJSONArray(SCHEDULES).
                        getJSONObject(currDay).
                        getJSONArray(SCHEDULE).getJSONObject(currPair).getJSONArray("weekNumber");
                HashSet<Integer> weeks = new HashSet<>();
                for (int i = 0; i < weeksJSON.length(); i++) {
                    weeks.add((int) weeksJSON.get(i));
                }
                BSUIRLesson currLesson = new BSUIRLesson(subjectName, time, teacher, type, weekDay, weeks);
                currPairs.add(currLesson);
            }
            weekDays.add(currPairs);
        }
        return weekDays;
    }
}
