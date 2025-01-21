package com.kai.recruit.model;

public class CompanyModel {

    private int companyId;
    private String companyName;
    private int companyLogo;
    private String description;
    private int state;
    private String companyCode;
    private String city;
    private String phone;

    public String getCity() {return this.city;}

    public void setCity(String city) {this.city=city;}

    public String getPhone() {return this.phone;}

    public void setPhone(String phone) {this.phone=phone;}

    public int getCompanyId() {return this.companyId;}

    public void setCompanyId(int companyId) {this.companyId=companyId;}

    public String getCompanyName() {return this.companyName;}

    public void setCompanyName(String companyName) {this.companyName=companyName;}

    public int getCompanyLogo() {return this.companyLogo;}

    public void setCompanyLogo(int companyLogo) {this.companyLogo=companyLogo;}

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description=description;}

    public int getState() {return this.state;}

    public void setState(int state) {this.state=state;}

    public String getCompanyCode() {return this.companyCode;}

    public void setCompanyCode(String companyCode) {this.companyCode=companyCode;}
}
