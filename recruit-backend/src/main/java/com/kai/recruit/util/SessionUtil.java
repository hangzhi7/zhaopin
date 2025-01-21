package com.kai.recruit.util;

import com.kai.recruit.constant.GlobalConst;
import com.kai.recruit.model.HRModel;
import com.kai.recruit.model.UserModel;
import com.kai.recruit.model.ManagerModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	// 返回当前登录 用户
	public static UserModel getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		return (UserModel)session.getAttribute(GlobalConst.USER_LOGIN_SESSION_KEY);
	}

	// 返回当前登录 HR
    public static HRModel getLoginHR(HttpServletRequest request){
	    HttpSession session = request.getSession();
	    if(session == null){
	        return null;
	    }
	    return (HRModel)session.getAttribute(GlobalConst.HR_LOGIN_SESSION_KEY);
    }
	// 返回当前登录 Manager

	public static ManagerModel getLoginManager(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session == null){
			return null;
		}
		return (ManagerModel)session.getAttribute(GlobalConst.MANAGER_LOGIN_SESSION_KEY);
	}

}
