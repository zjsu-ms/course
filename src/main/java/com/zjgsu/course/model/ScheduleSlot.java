package com.zjgsu.course.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * 课程时间安排实体类
 */
public class ScheduleSlot {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer expectedAttendance;

    public ScheduleSlot() {
    }

    public ScheduleSlot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, Integer expectedAttendance) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.expectedAttendance = expectedAttendance;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getExpectedAttendance() {
        return expectedAttendance;
    }

    public void setExpectedAttendance(Integer expectedAttendance) {
        this.expectedAttendance = expectedAttendance;
    }
}
