package com.kai.recruit.service;

import com.kai.recruit.model.CompanyModel;

public interface CompanyService {

    CompanyModel getCompany(int comId);

    CompanyModel getCompany(String companyCode);

    boolean registerCompany(CompanyModel companyModel);

    boolean updateCompany(CompanyModel companyModel);
}
