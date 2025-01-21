package com.kai.recruit.mapper;

import org.apache.ibatis.annotations.*;
import com.kai.recruit.model.CategoryModel;

import java.util.ArrayList;

public interface CategoryMapper {

    @Select("select * from category where categoryId = #{categoryId}")
    CategoryModel getCategory(@Param("categoryId") int categoryId);

    @Select("select * from category")
    ArrayList<CategoryModel> getAll();

    @Update("update category set secondCategoryName=#{secondCategoryName} where categoryId=#{categoryId}")
    Integer updateSecond(@Param("secondCategoryName") String secondCategoryName, @Param("categoryId") int categoryId);

    @Delete("delete from category where categoryId=#{categoryId}")
    Integer delCategory(@Param("categoryId") int categoryId);

    @Insert("insert into category(firstCategoryName, secondCategoryName, description) values(#{firstCategoryName}, #{secondCategoryName}, #{description})")
    Integer addCategory(@Param("firstCategoryName") String firstCategoryName, @Param("secondCategoryName") String secondCategoryName, @Param("description") String description);

    @Select("select categoryId from category where categoryName=#{categoryName}")
    CategoryModel getCategoryByName(@Param("categoryName") String categoryName);
}
