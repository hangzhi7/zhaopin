package com.kai.recruit.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.kai.recruit.model.CategoryModel;
import com.kai.recruit.mapper.CategoryMapper;
import com.kai.recruit.service.CategoryService;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 通过categoryId查询返回分类
     * @param categoryId
     * @return
     */
    @Override
    public CategoryModel getCategory(int categoryId){

        return categoryMapper.getCategory(categoryId);
    }

    @Override
    public boolean updateSecond(String secondCategoryName, int categoryId){
        if (categoryMapper.updateSecond(secondCategoryName,categoryId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delCategory(int categoryId){
        if (categoryMapper.delCategory(categoryId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<CategoryModel> getAll() {
        return categoryMapper.getAll();
    }

    @Override
    public boolean addCategory(String firstCategoryName, String secondCategoryName, String description) {
        if (categoryMapper.addCategory(firstCategoryName, secondCategoryName, description) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public CategoryModel getCategoryByName(String categoryName){
        return categoryMapper.getCategoryByName(categoryName);
    }

}
