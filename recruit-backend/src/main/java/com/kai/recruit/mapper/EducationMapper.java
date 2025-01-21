package com.kai.recruit.mapper;

import com.kai.recruit.model.EducationModel;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface EducationMapper {
    @Select("select * from education order by level")
    ArrayList<EducationModel> listEducation();

    @Insert("insert into education (name, level) values (#{name},#{level})")
    int createEducation(@Param("name") String name, @Param("level") int level);

    @Update("update education set name=#{name}, level=#{level} where id=#{id}")
    int updateEducation(@Param("id") int id,@Param("name") String name,  @Param("level") int level);

    @Delete("delete from education where id=#{id}")
    int delEducation(@Param("id") int id);
}
