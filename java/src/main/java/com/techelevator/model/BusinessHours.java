package com.techelevator.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BusinessHours {
    private int weekday_id;
    private String weekday_name;
    private LocalTime open_time;
    private LocalTime close_time;


    public int getWeekday_id() {
        return weekday_id;
    }

    public void setWeekday_id(int weekday_id) {
        this.weekday_id = weekday_id;
    }

    public String getWeekday_name() {
        return weekday_name;
    }

    public void setWeekday_name(String weekday_name) {
        this.weekday_name = weekday_name;
    }

    public LocalTime getOpen_time() {
        return open_time;
    }

    public void setOpen_time(LocalTime open_time) {
        this.open_time = open_time;
    }

    public LocalTime getClose_time() {
        return close_time;
    }

    public void setClose_time(LocalTime close_time) {
        this.close_time = close_time;
    }

    public BusinessHours(int weekday_id, String weekday_name, LocalTime open_time, LocalTime close_time) {
        this.weekday_id = weekday_id;
        this.weekday_name = weekday_name;
        this.open_time = open_time;
        this.close_time = close_time;
    }

    public BusinessHours() {

    }
}
