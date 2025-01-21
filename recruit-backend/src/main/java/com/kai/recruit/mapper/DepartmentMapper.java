package com.kai.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.kai.recruit.model.DepartmentModel;

import java.util.List;

public interface DepartmentMapper {

    @Select("select * from department where departmentId = #{departId}")
    DepartmentModel getDepartmentById(@Param("departId") int departId);

    @Select("select * from department where companyId = #{companyId}")
    List<DepartmentModel> getDepartmentByCompany(@Param("companyId") int companyId);

    @Select("select count(*) from department where companyId = #{companyId}")
    int getDepartmentCount(@Param("companyId") int companyId);

    @Insert("insert into department(departmentName,description, companyId) values(#{departmentName}, #{description}, #{companyId})")
    int addDepartment(@Param("departmentName") String departmentName, @Param("description") String description, @Param("companyId") int companyId);
}
