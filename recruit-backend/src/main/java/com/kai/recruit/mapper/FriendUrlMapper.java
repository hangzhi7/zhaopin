package com.kai.recruit.mapper;

import com.kai.recruit.model.FriendUrlsModel;
import org.apache.ibatis.annotations.*;
import com.kai.recruit.model.CategoryModel;

import java.util.ArrayList;

public interface FriendUrlMapper {

    @Insert("insert into friendurl(url, name) values(#{url},#{name})")
    int CreateUrl(@Param("url") String url, @Param("name") String name);

    @Select("select * from friendurl")
    ArrayList<FriendUrlsModel> listUrls();

    @Update("update friendurl set url=#{url},name=#{name} where urlId=#{urlId}")
    int updateFriendUrl(@Param("url") String url, @Param("name") String name, @Param("urlId") int urlId);

    @Delete("delete from friendurl where urlId=#{urlId}")
    int delFriendUrl(@Param("urlId") int urlId);
}
