package com.kai.recruit.pojo;

import com.kai.recruit.model.PositionModel;

public class PositionCompanyBO extends PositionModel {

    private int hrId;
    private String companyName;
    private String description;

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
