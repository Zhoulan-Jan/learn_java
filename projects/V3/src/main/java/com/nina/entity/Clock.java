package com.nina.entity;

import java.util.Date;

public class Clock {
    private Integer clockid;

    private Integer userid;

    private String username;

    private String startTime;

    private String endTime;

    private String startStatus;

    private String endStatus;

    private String time;

    public Integer getClockid() {
        return clockid;
    }

    public void setClockid(Integer clockid) {
        this.clockid = clockid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(String startStatus) {
        this.startStatus = startStatus;
    }

    public String getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(String endStatus) {
        this.endStatus = endStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clockid=").append(clockid);
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", startStatus=").append(startStatus);
        sb.append(", endStatus=").append(endStatus);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }
}