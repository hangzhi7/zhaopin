package com.kai.recruit.service.impl;

import org.springframework.stereotype.Service;
import com.kai.recruit.model.CompanyModel;
import com.kai.recruit.mapper.CompanyMapper;
import com.kai.recruit.service.CompanyService;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public CompanyModel getCompany(int comId){
        return companyMapper.getCompanyById(comId);
    }

    @Override
    public CompanyModel getCompany(String companyCode) {

        return companyMapper.getCompanyByCode(companyCode);
    }

    @Override
    public boolean registerCompany(CompanyModel companyModel) {
        String companyCode = companyModel.getCompanyCode();

        if(companyMapper.getCompanyByCode(companyCode) != null) {
            return false;
        }

        int result = -1;

        result = companyMapper.saveCompany(companyModel);

       if (result > 0) {
                return true;
       }
       return false;


    }

    @Override
    public boolean updateCompany(CompanyModel companyModel) {
        int companyId = companyModel.getCompanyId();
        String companyName = companyModel.getCompanyName();
        int companyLogo = companyModel.getCompanyLogo();
        String description = companyModel.getDescription();
        int state = companyModel.getState();
        String city = companyModel.getCity();
        String phone = companyModel.getPhone();

        if(companyMapper.updateCompany(companyId,companyName,companyLogo,description,state,city,phone)> 0) {
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
