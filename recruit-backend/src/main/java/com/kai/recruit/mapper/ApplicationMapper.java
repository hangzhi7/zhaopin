package com.kai.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.kai.recruit.model.ApplicationModel;
import com.kai.recruit.pojo.ApplicationPositionHRBO;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface ApplicationMapper {

    @Select("select * from application where resumeId = #{resumeId}")
    ArrayList<ApplicationModel> listApplicationAll(@Param("resumeId") int resumeId);

    @Select("select * from application where resumeId = #{resumeId} and applicationState=0")
    ArrayList<ApplicationModel> listApplicationNoDeal(@Param("resumeId") int resumeId);

    @Select("select * from application where resumeId = #{resumeId} and applicationState<>0")
    ArrayList<ApplicationModel> listApplicationDealed(@Param("resumeId") int resumeId);

    @Select("select * from application where hrId = #{HrId} and applicationState=0")
    ArrayList<ApplicationModel> listApplicationByHrId(@Param("HrId") int HrId);

    @Select("select * from application where resumeId = #{resumeId} and positionId = #{posId} limit 1")
    ApplicationModel getApplication(@Param("resumeId") int resumeId, @Param("posId") int posId);

    @Update("update application set applicationState=1, reply = #{reply} where applicationId=#{applicationId}")
    int allowApplication(@Param("applicationId") int applicationId,@Param("reply") String reply);

    @Update("update application set applicationState=2, reply = #{reply} where applicationId=#{applicationId}")
    int refuseApplication(@Param("applicationId") int applicationId,@Param("reply") String reply);

    @Insert("insert into application(applicationState,recentTime,resumeId,positionId,hrId) values (0,#{recentTime},#{resumeId},#{positionId},#{hrId})")
    int saveApplication(@Param("recentTime") Timestamp recentTime, @Param("resumeId") int resumeId, @Param("positionId") int positionId, @Param("hrId") int hrId);

     /**
      * 申请处理完成：查询返回 申请 职位 处理hr信息
      * @param resumeId
      * @return
      */
     @Select("select a.applicationId,a.state,a.recentTime,a.resumeId,p.*,h.hrId,h.hrMobile,h.hrName,h.hrEmail\n" +
             "from application a,position p,hr h\n" +
             "where a.positionId = p.positionId and a.hrId = h.hrId \n" +
             "and a.hrId is not null and a.resumeId = #{resumeId}\n" +
             "order by recentTime;")
     ArrayList<ApplicationPositionHRBO> listAppPosHR(@Param("resumeId") int resumeId);

     /**
      * 申请简历
      * @param resumeId
      * @return
      */
     @Select("select a.applicationId,a.state,a.recentTime,a.resumeId,p.*,h.hrId,h.hrMobile,h.hrName,h.hrEmail\n" +
             "from application a,position p,hr h\n" +
             "where a.positionId = p.positionId and p.hrIdPub = h.hrId \n" +
             "and a.hrId is null and a.resumeId = #{resumeId}\n" +
             "order by p.releaseDate;")
     ArrayList<ApplicationPositionHRBO> listAppPosHRPub(@Param("resumeId") int resumeId);

}
