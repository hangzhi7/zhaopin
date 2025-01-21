package com.kai.recruit.model;

import java.sql.Timestamp;

public class ApplicationModel {
    private int applicationId;
    private int applicationState; // 0:尚未处理 1：同意 2：拒绝
    private Timestamp recentTime;

    private int resumeId;
    private int positionId;
    private int hrId;
    private String reply;

    public int getApplicationId() {return this.applicationId;}

    public void setApplicationId(int applicationId) {this.applicationId=applicationId;}

    public String getReply() {return this.reply;}

    public void setReply(String reply) {this.reply=reply;}

    public int getApplicationState() {return this.applicationState;}

    public void setApplicationState(int applicationState) {this.applicationState=applicationState;}

    public Timestamp getRecentTime() {return this.recentTime;}

    public void setRecentTime(Timestamp recentTime) {this.recentTime = recentTime;}

    public int getResumeId() {return this.resumeId;}

    public void setResumeId(int resumeId) {this.resumeId = resumeId;}

    public int getPositionId() {return this.positionId;}

    public void setPositionId(int positionId) {this.positionId = positionId;}

    public int getHrId() {return this.hrId;}

    public void setHrId(int hrId) {this.hrId = hrId;}
}
