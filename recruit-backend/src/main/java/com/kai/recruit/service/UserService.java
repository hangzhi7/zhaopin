package com.kai.recruit.service;

import com.kai.recruit.model.UserModel;
import com.kai.recruit.pojo.ApplicationPositionHRBO;

import java.util.ArrayList;

public interface UserService {

	UserModel getUser(int userId);

	boolean updatePwd(int userId, String oldpassword, String newpassword);

	boolean updateAvatar(String avatar, int userId);

	boolean updateUser(UserModel userModel);

	boolean registerUser(UserModel userModel);

	boolean loginUser(String mobile, String password);

	UserModel getUserByUser(String user);

	ArrayList<ApplicationPositionHRBO> listMyApp(int resumeId);
}
