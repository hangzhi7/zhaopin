package com.kai.recruit.model;

public class DepartmentModel {

    private int departmentId;
    private String departmentName;
    private String description;
    private int companyId;

    public int getDepartmentId() {return this.departmentId;}

    public void setDepartmentId(int departmentId) {this.departmentId=departmentId;}

    public String getDepartmentName() {return this.departmentName;}

    public void setDepartmentName(String departmentName) {this.departmentName=departmentName;}

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description=description;}

    public int getCompanyId() {return this.companyId;}

    public void setCompanyId(int companyId) {this.companyId=companyId;}
}
