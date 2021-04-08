package com.company;

import Schedule.BSUIRSchedule;

public class Main {

    public static void main(String[] args) {
        BSUIRSchedule schedule051007 = new BSUIRSchedule("051007");
        System.out.println(schedule051007.getForCurrentWeek());
    }


}
