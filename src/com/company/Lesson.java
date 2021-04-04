package com.company;

public class Lesson {
    private String subjectName;
    private String time;
    private Teacher teacher;
    private String type;
    private String week;

    public Lesson(String subjectName, String time, Teacher teacher, String type) {
        this.subjectName = subjectName;
        this.time = time;
        this.teacher = teacher;
        this.type = type;
    }
}
