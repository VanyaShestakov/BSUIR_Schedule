package Schedule;

import java.util.HashSet;

public class BSUIRLesson {
    private String subjectName;
    private String time;
    private String teacher;

    public HashSet<Integer> getWeeks() {
        return weeks;
    }

    private String type;
    private HashSet<Integer> weeks;
    private String weekDay;

    public BSUIRLesson(String subjectName, String time, String teacher, String type, String weekDay, HashSet<Integer> weeks) {
        this.subjectName = subjectName;
        this.time = time;
        this.teacher = teacher;
        this.type = type;
        this.weekDay = weekDay;
        this.weeks = weeks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(subjectName).
                append("(").
                append(type).
                append(")").
                append(" ").
                append(time).
                append("\n").
                append(teacher);
        return sb.toString();
    }

    public String getWeekDay() {
        return weekDay;
    }
}
