package com.kai.recruit.mapper;

import com.kai.recruit.model.UserModel;
import com.kai.recruit.pojo.ApplicationPositionHRBO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface UserMapper {
	
	@Select("select * from user")
	ArrayList<UserModel> listUser();

	@Select("select * from user where userId = #{userId}")
	UserModel getUser(@Param("userId") int userId);

	@Select("select COUNT(*) from user")
	int getUserSize();

	@Update("update user set mobile=#{mobile},gender=#{gender},birthYear=#{birthYear},nickname=#{nickname},email=#{email},province=#{province},city=#{city}," +
		"eduDegree = #{eduDegree},graduation=#{graduation},graduateYear=#{graduateYear},major=#{major},dirDesire=#{dirDesire} where userId = #{userId}")
	int updateUser(UserModel userModel);

	@Insert("insert into user(user,avatar,password,gender,birthYear,nickname,email,province,city,eduDegree,graduation,graduateYear,major,dirDesire) " +
		"values(#{user}, 'default',#{password},#{gender},#{birthYear},#{nickname},#{email},#{province},#{city},#{eduDegree},#{graduation},#{graduateYear},#{major},#{dirDesire})")
	int saveUser(UserModel userModel);

	@Select("select * from user where user=#{user} limit 1")
	UserModel getUserByUser(@Param("user") String user);

	@Update("update user set password=#{password} where userId=#{userId}")
	int updatePwd(@Param("password") String password, @Param("userId") int userId);

	@Update("update user set avatar=#{avatar} where userId=#{userId}")
	int updateAvatar(@Param("avatar") String avatar, @Param("userId") int userId);

	@Select("select applicationId,applicationId, recentTime, resumeId, a.positionId, a.applicationState,a.reply,hrId,title,requirement,quantity,workCity,salaryUp,salaryDown,releaseDate,hits,categoryId,hrIdPub,workProvince,educationId, statePub from application as a left join position as b on a.positionId=b.positionId where resumeId=#{resumeId}")
	ArrayList<ApplicationPositionHRBO> listMyApp(int resumeId);
}