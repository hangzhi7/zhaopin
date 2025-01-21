package com.kai.recruit.pojo;

import com.kai.recruit.model.HRModel;
import com.kai.recruit.model.PositionModel;

import java.sql.Timestamp;

public class ApplicationPositionHRBO extends PositionModel{

    private int applicationId;
    private int applicationState;
    private Timestamp recentTime;
    private int resumeId;
    String reply;
    private int hrId;
//    private String hrMobile;
//    private String hrName;
//    private String hrEmail;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(int applicationState) {
        this.applicationState = applicationState;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Timestamp getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Timestamp recentTime) {
        this.recentTime = recentTime;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

//    public String getHrMobile() {
//        return hrMobile;
//    }
//
//    public void setHrMobile(String hrMobile) {
//        this.hrMobile = hrMobile;
//    }
//
//    public String getHrName() {
//        return hrName;
//    }
//
//    public void setHrName(String hrName) {
//        this.hrName = hrName;
//    }
//
//    public String getHrEmail() {
//        return hrEmail;
//    }
//
//    public void setHrEmail(String hrEmail) {
//        this.hrEmail = hrEmail;
//    }
}
