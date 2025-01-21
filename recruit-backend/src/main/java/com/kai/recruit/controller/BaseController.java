package com.kai.recruit.controller;

import com.google.gson.Gson;
import com.kai.recruit.constant.GlobalConst;
import com.kai.recruit.model.*;
import com.kai.recruit.service.FriendUrlService;
import com.kai.recruit.util.SessionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BaseController {
    @Resource
    private FriendUrlService friendUrlService;

	//返回错误页面
    public String errorDirect_404() { 
    	return "general/404"; 
    }

    //指向用户表现层
    public String userDirect(String viewName) {
        return GlobalConst.USER_RPATH + "/" + viewName;
    }

    //指向hr路径层
    public String hrDirect(String viewName) {
        return GlobalConst.HR_PATH + "/" + viewName;
    }

    //指向管理员路径层
    public String adminDirect(String viewName) {
        return GlobalConst.ADMIN_PATH + "/" + viewName;
    }

    //获取绑定登录对象
    public UserModel getUser(HttpServletRequest request) {
        return SessionUtil.getLoginUser(request);
    }

    public HRModel getHR(HttpServletRequest request) {
        return SessionUtil.getLoginHR(request);
    }

    public ManagerModel getManager(HttpServletRequest request) {
        return SessionUtil.getLoginManager(request);
    }

    //获取绑定登录对象Id
    public Integer getUserId(HttpServletRequest request) {
        return getUser(request).getUserId();
    }

    public Integer getHRId(HttpServletRequest request) {
        return getHR(request).getHrId();
    }

    //设置页面title
    public BaseController title(HttpServletRequest request,String title){
        request.setAttribute("title",title);
        return this;
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath +"/"+ fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
