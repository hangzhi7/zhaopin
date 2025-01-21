package com.kai.recruit.model;

public class CategoryModel {

    private int categoryId;
    private String firstCategoryName;
    private String secondCategoryName;
    private String description;

    public int getCategoryId() {return this.categoryId;}

    public void setCategoryId(int categoryId) {this.categoryId=categoryId;}

    public String getFirstCategoryName() {return this.firstCategoryName;}

    public void setFirstCategoryName(String firstCategoryName) {this.firstCategoryName=firstCategoryName;}

    public String getSecondCategoryName() {return this.secondCategoryName;}

    public void setSecondCategoryName(String secondCategoryName) {this.secondCategoryName=secondCategoryName;}

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description = description;}
}
