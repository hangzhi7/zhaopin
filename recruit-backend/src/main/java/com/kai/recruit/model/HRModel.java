package com.kai.recruit.model;

import java.util.Date;

public class HRModel {

    private int hrId;
    private String hr;
    private String hrMobile;
    private String hrPassword;
    private String hrName;
    private String hrEmail;
    private String description;
    private String companyName;
    private String companyAddress;
    private Date createTime;

    public int getHrId() {
        return this.hrId;
    }

    public void setHrId(int hrId) {
        this.hrId=hrId;
    }

    public String getHr() {
        return this.hr;
    }

    public void setHr(String hr) {
        this.hr=hr;
    }

    public String getHrMobile() {
        return this.hrMobile;
    }

    public void setHrMobile(String hrMobile) {
        this.hrMobile=hrMobile;
    }

    public String getHrPassword() {
        return this.hrPassword;
    }

    public void setHrPassword(String hrPassword) {
        this.hrPassword=hrPassword;
    }

    public String getHrName() {
        return this.hrName;
    }

    public void setHrName(String hrName) {
        this.hrName=hrName;
    }

    public String getHrEmail() {
        return this.hrEmail;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail=hrEmail;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public String getCompanyName() {return this.companyName;}

    public void setCompanyName(String companyName) {this.companyName=companyName;}

    public String getCompanyAddress() {return this.companyAddress;}

    public void setCompanyAddress(String companyAddress) {this.companyAddress=companyAddress;}

    public Date getCreateTime() {return this.createTime;}

    public void setCreateTime(Date createTime) {this.createTime=createTime;}

}
