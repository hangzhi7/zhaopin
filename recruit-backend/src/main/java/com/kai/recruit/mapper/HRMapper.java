package com.kai.recruit.mapper;

import org.apache.ibatis.annotations.*;
import com.kai.recruit.model.ApplicationModel;
import com.kai.recruit.model.HRModel;
import com.kai.recruit.model.PositionModel;

import java.util.ArrayList;

/**
 * <P>
 private int hrId;
 private String hrMobile;
 private String hrPassword;
 private String hrName;
 private String hrEmail;
 private String description;
 private int departmentId;
 * </P>
 */
public interface HRMapper {
    /**
     * <p>`hrId` int(11) NOT NULL AUTO_INCREMENT,
     `hrMobile` varchar(11) NOT NULL,
     `hrPassword` varchar(500) NOT NULL,
     `hrName` varchar(50) DEFAULT NULL,
     `hrEmail` varchar(50) DEFAULT NULL,
     `description` longtext,
     `departmentId` int(11) NOT NULL,</p>
     *
     */

    @Select("select * from hr")
    ArrayList<HRModel> listHR();

    @Delete("delete from hr where hrId = #{hrId}")
    int delHR(@Param("hrId") int hrId);

    @Select("select * from hr where hrId = #{hrId}")
    HRModel getHR(@Param("hrId") int hrId);

    @Select("select COUNT(*) from hr")
    int getHRSize();

    @Update("update hr set hrPassword=#{newPassword} where hrId=#{hrId}")
    int updatePwd(@Param("hrId") int hrId, @Param("newPassword") String newPassword);

    @Update("update hr set hrName=#{hrName},hrMobile=#{hrMobile},hrEmail=#{hrEmail},description=#{description},companyName=#{companyName},companyAddress=#{companyAddress},createTime=#{createTime} where hrId=#{hrId}")
    int updateHr(HRModel hr);

    @Insert({"insert into hr(hr,hrPassword) values(#{hr},#{password})"})
    int saveHR(@Param("hr") String hr, @Param("password") String password);

    @Select("select * from hr where hr = #{hr} limit 1")
    HRModel getHRByHr(@Param("hr") String hr);

    @Select("select * from application where hrId =  #{hrId}")
    ArrayList<ApplicationModel> queryApplication(@Param("hrId") int hrId);

    @Insert("INSERT INTO `position`( `title`, `requirement`, `quantity`, `workCity`, `salaryUp`, `salaryDown`,   `statePub`,  `categoryId`,  `hrIdPub`, `workProvince`,`hits`, `releaseDate`, `educationId`) VALUES (#{positionModel.title},#{positionModel.requirement},#{positionModel.quantity},#{positionModel.workCity},#{positionModel.salaryUp},#{positionModel.salaryDown},#{positionModel.statePub},#{positionModel.categoryId},#{positionModel.hrIdPub},#{positionModel.workProvince},0, #{positionModel.releaseDate}, #{positionModel.educationId})")
    int addPostion(@Param("positionModel")  PositionModel positionModel);

    @Update("update position set statePub=2 where positionId = #{positionId}")
    int delPostion(@Param("positionId")int  positionId);

    @Update("update position set title=#{title},requirement=#{requirement},quantity=#{quantity},workCity=#{workCity},salaryUp=#{salaryUp},salaryDown=#{salaryDown},"+
            "statePub=#{statePub},categoryId=#{categoryId},workProvince=#{workProvince},hrIdPub=#{hrIdPub},educationId=#{educationId} WHERE positionId=#{positionId}")
    int updatePostion(PositionModel positionModel);

    @Select("select * from position where statePub!=2")
    ArrayList<PositionModel> listAllPositioni();
}
