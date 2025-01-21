package com.kai.recruit.service;

import com.kai.recruit.model.*;

import java.util.ArrayList;

public interface ManagerService {
    boolean managerLogin(String mobile,String password);

    ManagerModel getManagerByMobile(String mobile);
    ArrayList<UserAreaModel> userArea();
    ArrayList<CompanyModel> getAllCompanies();
    ArrayList<UserModel> getAllUsers();
    WebCountModel getWebCount();
    int addCompany(String companyName,String companyCode,String description);
    ArrayList<ApplicationModel> getAllApplications();
    ArrayList<ResumeModel> getAllResumes();
    boolean isManager(int managerId);
    int delCompany(int companyId);
    int delUser(int userId);
    boolean updatePwd(String mobile, String newPassword);
    boolean delApplication(int applicationId);
}
