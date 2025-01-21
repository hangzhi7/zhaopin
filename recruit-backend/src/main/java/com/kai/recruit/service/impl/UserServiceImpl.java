package com.kai.recruit.service.impl;

import com.kai.recruit.pojo.ApplicationPositionHRBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.kai.recruit.model.UserModel;
import com.kai.recruit.mapper.UserMapper;
import com.kai.recruit.service.UserService;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service("UserService")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private UserMapper userMapper;

    @Override
    public UserModel getUser(int userId) {
        return userMapper.getUser(userId);
    }

    @Override
    public boolean updatePwd(int userId, String oldpassword, String newpassword) {
        String orginpassword = this.getUser(userId).getPassword();
        String encPass = EncodingByMd5(oldpassword);

        if(!encPass.equals(orginpassword)) {
            return false;
        }
        encPass = EncodingByMd5(newpassword);

        if (userMapper.updatePwd(encPass, userId) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAvatar(String avatar, int userId){

        if (userMapper.updateAvatar(avatar,userId ) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(UserModel userModel) {

        if (userMapper.updateUser(userModel) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean registerUser(UserModel userModel) {

        String user = userModel.getUser();
        if (userMapper.getUserByUser(user) != null) {
            return false;
        }

        String password = userModel.getPassword();

        String encPass = EncodingByMd5(password);
        userModel.setPassword(encPass);
        int result = userMapper.saveUser(userModel);

        if (result > 0) {
            return true;
        }
        return false;

    }

    @Override
    public boolean loginUser(String user, String password) {

        if (userMapper.getUserByUser(user) == null) {
            return false;
        }
        String passwordDB = userMapper.getUserByUser(user).getPassword();

        if (EncodingByMd5(password).equals(passwordDB)) {
            return true;
        }
        return false;
    }

    @Override
    public UserModel getUserByUser(String user) {

        return userMapper.getUserByUser(user);
    }


    @Override
    public ArrayList<ApplicationPositionHRBO> listMyApp(int resumeId) {
        return userMapper.listMyApp(resumeId);
    }


    public static String EncodingByMd5(String str)  {
        try{
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();

            //加密后的字符串
            String encStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
            return encStr;
        }catch (Exception e){
            throw new RuntimeException("MD5加密失败");
        }

    }

}
