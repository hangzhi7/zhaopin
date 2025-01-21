package com.kai.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.kai.recruit.model.DepartmentModel;
import com.kai.recruit.mapper.DepartmentMapper;
import com.kai.recruit.service.DepartmentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public DepartmentModel getDepartment(int departId) {
        return departmentMapper.getDepartmentById(departId);
    }

    @Override
    public List<DepartmentModel> getDepartmentByCompany(int companyId) {
        return departmentMapper.getDepartmentByCompany(companyId);
    }

    @Override
    public PageInfo<DepartmentModel> getDepartmentByCompany(int companyId, int page, int limit) {
        int total = departmentMapper.getDepartmentCount(companyId);
        PageHelper.startPage(page, limit);
        List<DepartmentModel> deplist = getDepartmentByCompany(companyId);
        PageInfo<DepartmentModel> pagination = new PageInfo<>(deplist);
        pagination.setTotal(total);
        return pagination;
    }

    @Override
    public  boolean addDepartment(String departmentName, String description, int companyId) {
        if(departmentMapper.addDepartment(departmentName,description,companyId) > 0) {
            return true;
        }
        return false;
    }

}
