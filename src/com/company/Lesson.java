package com.company;

public class Lesson {
    private String subjectName;
    private String time;
    private String teacher;
    private String type;
    private int week;
    private String weekDay;

    public Lesson(String subjectName, String time, String teacher, String type, String weekDay) {
        this.subjectName = subjectName;
        this.time = time;
        this.teacher = teacher;
        this.type = type;
        this.weekDay = weekDay;
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
