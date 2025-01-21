package com.kai.recruit.service.impl;

import com.kai.recruit.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kai.recruit.mapper.ManagerMapper;
import com.kai.recruit.service.ManagerService;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public boolean managerLogin(String mobile, String password) {
        if (managerMapper.getManagerByMobile(mobile) == null) {
            return false;
        }
        String passwordDB = managerMapper.getManagerByMobile(mobile).getPassword();

        if (password.equals(passwordDB)) {
            return true;
        }
        return false;
    }

    @Override
    public ManagerModel getManagerByMobile(String mobile) {
        return managerMapper.getManagerByMobile(mobile);
    }

    @Override
    public ArrayList<UserAreaModel> userArea(){
        return managerMapper.userArea();
    }

    @Override
    public ArrayList<CompanyModel> getAllCompanies() {
        return managerMapper.getAllCompanies();
    }

    @Override
    public ArrayList<UserModel> getAllUsers() {
        return managerMapper.getAllUsers();
    }

    @Override
    public WebCountModel getWebCount(){
        return managerMapper.getWebCount();
    }

    @Override
    public int addCompany(String companyName,String companyCode,String description){
        return managerMapper.addCompany(companyName,companyCode,description);
    }

    @Override
    public ArrayList<ApplicationModel> getAllApplications() {return managerMapper.getAllApplications();}

    @Override
    public ArrayList<ResumeModel> getAllResumes() {return managerMapper.getAllResumes();}

    @Override
    public  boolean isManager(int managerId) {
        if (managerMapper.isManager(managerId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int delCompany(int companyId){
        return managerMapper.delCompany(companyId);
    }

    @Override
    public int delUser(int userId){
        return managerMapper.delUser(userId);
    }

    @Override
    public boolean updatePwd(String mobile, String newPassword) {
        if (managerMapper.updatePwd(mobile, newPassword) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delApplication(int applicationId) {
        if (managerMapper.delApplication(applicationId) > 0){
            return true;
        }
        return false;
    }

    public String EncodingByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();

        //加密后的字符串
        String encStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return encStr;
    }
}
