package com.kai.recruit.service;

import com.github.pagehelper.PageInfo;
import com.kai.recruit.model.DepartmentModel;

import java.util.List;

public interface DepartmentService {

    /**
     * 通过Id返回部门
     * @param departId
     * @return
     */
    DepartmentModel getDepartment(int departId);

    List<DepartmentModel> getDepartmentByCompany(int companyId);

    PageInfo<DepartmentModel> getDepartmentByCompany(int companyId, int page, int limit);

    boolean addDepartment(String departmentName, String description, int companyId);

}
