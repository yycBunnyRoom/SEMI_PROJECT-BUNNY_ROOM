package com.yyc.bunnyroom.common.eNum;

public enum WeekDay {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String description;


    WeekDay(String description){this.description = description;}

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}


