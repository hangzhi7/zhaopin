package com.kai.recruit.service;

import com.kai.recruit.model.ApplicationModel;
import com.kai.recruit.model.HRModel;
import com.kai.recruit.model.PositionModel;
import com.kai.recruit.pojo.ApplicationPositionHRBO;

import java.util.ArrayList;
import java.util.List;

public interface HRService {

    HRModel getHR(int hrId);

    ArrayList<HRModel> listHR();

    boolean updateHr(HRModel HRModel);

    boolean delHR(int hrId);

    boolean registerHR(HRModel HRModel);

    boolean loginHR(String hr, String password);

    HRModel getHRByHr(String hr);

    ArrayList<ApplicationModel> queryApplication(int hrId);

    int addPostion(PositionModel positionModel);

    int delPostion(int positionId);

    boolean updatePostion(PositionModel positionModel);

    boolean updatePwd(int hrId, String oldpassword, String newPassword);

    ArrayList<PositionModel> listAllPosition();



//    List<PositionModel> showPosition(int hrId);
}