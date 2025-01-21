package com.kai.recruit.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.kai.recruit.model.PositionModel;
import com.kai.recruit.pojo.PositionCompanyBO;

import java.util.ArrayList;

public interface PositionMapper {

    @Select("select * from position where statePub = 1")
    ArrayList<PositionModel> listPosAll();

    @Select("select * from position where positionId = #{posId} and statePub = 1")
    PositionModel getPosition(@Param("posId") int posId);

    @Select("select * from position where hrIdPub = #{hrId} and statePub!=2")
    ArrayList<PositionModel> listHRPos(@Param("hrId") int hrId);

//    @Select("select p.*,c.* from position p,department d,company c \n" +
//            "where p.departmentId = d.departmentId and d.companyId = c.companyId \n" +
//            "and title like #{keyword} and statePub = 1 \n" +
//            "and companyName like #{companyName} and requirement like #{eduDegree} \n" +
//            "order by #{order} DESC")
//    ArrayList<PositionCompanyBO> listSearchPos(@Param("keyword") String keyword,@Param("companyName") String companyName,@Param("eduDegree") String eduDegree,@Param("order") String order);

    @Select("<script>select * from position p LEFT JOIN hr c on c.hrId = p.hrIdPub where  statePub = 1 "+
            "<if test=\"!keyword.isEmpty()\"> and (title like #{keyword} OR companyName like #{keyword}) </if>" +
            "<if test=\"categoryId !=0 \"> and categoryId = #{categoryId}</if>" +
            "<if test=\"!workProvince.isEmpty()\">and workProvince = #{workProvince} </if>" +
            "<if test=\"!workCity.isEmpty()\">and workCity = #{workCity} </if>" +
            "<if test=\"educationId !=0 \">and educationId = #{educationId} </if>" +
            "order by positionId DESC </script>")
    ArrayList<PositionCompanyBO> listSearchPos(@Param("keyword") String keyword,@Param("categoryId") int categoryId,@Param("workProvince") String workProvince,@Param("workCity") String workCity,@Param("educationId") int educationId);

    @Select("select p.*,c.* from position p,department d,company c\n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId \n" +
            "and categoryId = #{categoryId} and statePub = 1 \n" +
            "order by releaseDate DESC")
    ArrayList<PositionCompanyBO> listCategoryPos(@Param("categoryId") int categoryId);

    @Select("select count(*) from position where categoryId = #{categoryId}")
    int countCategoryPos(@Param("categoryId") int categoryId);

    @Update("update position set hits = hits+1 where positionId = #{positionId}")
    int updateHits(@Param("positionId") int positionId);

    @Select("select p.*,c.* from position p,department d,company c \n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId and p.positionId = #{posId} limit 1")
    PositionCompanyBO listPosCompany(@Param("posId") int posId);

    @Select("select count(*) from position where hrIdPub=#{hrIdPub} and statePub !=2")
    int countHRPos(@Param("hrIdPub") int hrIdPub);

    @Delete("delete position where positionId = #{posId}")
    int delete(@Param("posId") int posId);

    @Update("update position set title = #{title},requirement=#{requirement},quantity=#{quantity}," +
            "workCity=#{workCity},salaryUp=#{salaryUp},salaryDown=#{salaryDown}," +
            "workProvince=#{workProvince},statePub=#{statePub}" +
            " where positionId = #{posId}")
    int updatePosition(PositionModel positionModel);

    @Update("update position set statePub= #{statePub} where positionId = #{posId}")
    int updatePositionState(@RequestParam("statePub") int statePub, @RequestParam("posId") int posId);

    @Insert("insert into position(title,requirement,quantity,workCity,salaryUp,salaryDown,releaseDate,workProvince,statePub," +
            "categoryId,hrIdPub) " +
            "values(#{title},#{requirement},#{quantity},#{workCity},#{salaryUp},#{salaryDown},#{releaseDate},#{workProvince},#{statePub}," +
            "#{categoryId},#{hrIdPub}")
    int savePosition(PositionModel positionModel);

    @Update("update position set categoryId=#{categoryId} where positioinId=#{positionId}")
    int setCategory(@Param("categoryId") int categoryId, @Param("positionId") int positionId);

}

