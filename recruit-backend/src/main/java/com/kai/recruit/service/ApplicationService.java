package com.kai.recruit.service;


import com.kai.recruit.model.ApplicationModel;
import com.kai.recruit.pojo.ApplicationPositionHRBO;

import java.util.List;

public interface ApplicationService {

    boolean applyPosition(int resumeId, int positionId, int hrId);

    List<ApplicationModel> listApplyNoDeal(int resumeId);

    List<ApplicationModel> listApplyDealed(int resumeId);

    List<ApplicationModel> listApplyAll(int resumeId);

    List<ApplicationModel> listApplyInfoByHr(int hrId);

    boolean isApplyPosition(int resumeId, int positionId);

    boolean allowApplication(int applicationId, String reply);

    boolean refuseApplication(int applicationId, String reply);

}
