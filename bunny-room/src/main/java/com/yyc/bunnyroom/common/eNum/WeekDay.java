package com.yyc.bunnyroom.common.eNum;

public enum WeekDay {
    MONDAY(1, "월"),
    TUESDAY(2, "화"),
    WEDNESDAY(3, "수"),
    THURSDAY(4, "목"),
    FRIDAY(5, "금"),
    SATURDAY(6, "토"),
    SUNDAY(0, "일");

    private final int value; // int 값을 저장할 필드 추가
    private final String description;

    WeekDay(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}


