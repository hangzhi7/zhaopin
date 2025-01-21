package com.kai.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.kai.recruit.model.ResumeModel;

public interface ResumeMapper {

    @Select("select resumeId from resume where userId = #{userId} limit 1")
    int getResumeId(@Param("userId") int userId);

    @Select("select * from resume where userId = #{userId} limit 1")
    ResumeModel getResumeById(@Param("userId") int userId);

    @Update("update resume set ability = #{ability},honour = #{honour},internshipExperience=#{internshipExperience},workExperience=#{workExperience}," +
            "certificate = #{certificate},projectExperience=#{projectExperience},selfAssessment=#{selfAssessment},jobDesire = #{jobDesire} where resumeId = #{resumeId}")
    int saveResume(ResumeModel resumeModel);

    @Insert("insert into resume(userId) " +
            "values (#{userId})")
    int createResume(ResumeModel resumeModel);

    @Select("select * from resume where resumeId=#{resumeId}")
    ResumeModel getResumeByRId(@Param("resumeId") int resumeId);

}
