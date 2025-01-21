package com.kai.recruit.service;

import com.kai.recruit.model.CategoryModel;

import java.util.ArrayList;

public interface CategoryService {

    // 通过categoryId查询返回分类
    CategoryModel getCategory(int categoryId);

    boolean updateSecond(String second, int categoryId);

    boolean delCategory(int categoryId);

    ArrayList<CategoryModel> getAll();

    CategoryModel getCategoryByName(String categoryName);

    boolean addCategory(String firstCategoryName, String secondCategoryName,String description);
}
