package com.kai.recruit.mapper;

import org.apache.ibatis.annotations.*;
import com.kai.recruit.model.CompanyModel;

/**
 * <p>
 *       private int companyId;
 private String companyName;
 private int companyLogo;
 private String description;
 private int state;
 private String companyCode;
 * </p>
 */
public interface CompanyMapper {

    @Select("select * from company where companyId = #{comId}")
    CompanyModel getCompanyById(@Param("comId") int comId);

    @Select("select * from company where companyCode = #{companyCode}")
    CompanyModel getCompanyByCode(@Param("companyCode") String companyCode);

    @Select("select * from company where companyName = #{companyName}")
    CompanyModel getCompanyByName(@Param("companyName") String companyName);

    @Insert({"insert into company(companyName,companyLogo,description,state,companyCode)"
            +"values(#{companyName},#{companyLogo},#{description},#{state},#{companyCode})"})
    int saveCompany(CompanyModel companyModel);

    @Update("update company set companyCode = #{companyCode},companyLogo = #{companyLogo},description = #{description},state = #{state},city = #{city},phone = #{phone} where companyId = #{companyId}")
    int updateCompany(@Param("companyId") int companyId, @Param("companyName") String companyName, @Param("companyLogo") int companyLogo, @Param("description") String description,@Param("state") int state,@Param("city") String city, @Param("phone") String phone);
}
