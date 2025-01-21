package com.kai.recruit.mapper;

import com.kai.recruit.model.*;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;


public interface ManagerMapper {
    @Select("SELECT COUNT(*) FROM manager WHERE managerId = #{managerId} AND password = md5(#{password})")
    Integer backLogin(@Param("mobile") String mobile, @Param("password") String password);

    @Select("SELECT * FROM manager WHERE mobile=#{mobile}")
    ManagerModel getManagerByMobile(@Param("mobile") String mobile);

    @Select("SELECT COUNT(*) AS usernum,province AS area FROM user GROUP BY province")
    ArrayList<UserAreaModel> userArea();

    @Select("SELECT * FROM company")
    ArrayList<CompanyModel> getAllCompanies();

    @Select("SELECT * FROM user")
    ArrayList<UserModel> getAllUsers();

    @Select("SELECT COUNT(*) AS companynum,(SELECT COUNT(applicationId) FROM application) AS offernum, (SELECT COUNT(userId) FROM user) AS usernum, (SELECT max(hits) FROM position) AS visitnum FROM company")
    WebCountModel getWebCount();

    @Insert("INSERT INTO company(companyLogo,state,companyName,companyCode,description) VALUES (7,1,#{companyName},#{companyCode},#{description})")
    Integer addCompany(@Param("companyName") String companyName,@Param("companyCode") String companyCode,@Param("description") String description);

    @Select("select * from application")
    ArrayList<ApplicationModel> getAllApplications();

    @Select("select * from resume")
    ArrayList<ResumeModel> getAllResumes();

    @Select("select count(*) from manager where managerId=#{managerId}")
    Integer isManager(@Param("managerId") int managerId);

    @Delete("delete from hr where hrId=#{hrId}")
    int delCompany(@Param("hrId") int hrId);

    @Delete("delete from user where userId=#{userId}")
    int delUser(@Param("userId") int userId);

    @Update("update manager set password=#{newPassword} where mobile=#{mobile}")
    int updatePwd(@Param("mobile") String mobile, @Param("newPassword") String newPassword);

    @Delete("delete from application where applicationId=#{applicationId}")
    int delApplication(@Param("applicationId") int applicationId);
}
