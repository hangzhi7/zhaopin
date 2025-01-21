package com.kai.recruit.service.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.kai.recruit.mapper.ApplicationMapper;
import com.kai.recruit.mapper.PositionMapper;
import com.kai.recruit.mapper.ResumeMapper;
import com.kai.recruit.model.ApplicationModel;
import com.kai.recruit.model.PositionModel;
import com.kai.recruit.model.ResumeModel;
import com.kai.recruit.pojo.ApplicationPositionHRBO;
import com.kai.recruit.service.ApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("ApplicationService")
public class ApplicationServiceImpl implements ApplicationService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private ApplicationMapper applicationMapper;
    private ResumeMapper resumeMapper;
    private PositionMapper positionMapper;

    @Override
    public boolean applyPosition(int resumeId, int positionId, int hrId) {

        Timestamp time = new Timestamp(System.currentTimeMillis());
        applicationMapper.saveApplication(time, resumeId, positionId,hrId);

        if (applicationMapper.getApplication(resumeId, positionId) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isApplyPosition(int resumeId, int positionId) {

        if (applicationMapper.getApplication(resumeId, positionId) == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<ApplicationModel> listApplyNoDeal(int resumeId) {
        List<ApplicationModel> applicationList = applicationMapper.listApplicationNoDeal(resumeId);
        return applicationList;
    }

    @Override
    public List<ApplicationModel> listApplyDealed(int resumeId) {
        List<ApplicationModel> applicationList = applicationMapper.listApplicationDealed(resumeId);
        return applicationList;
    }

    @Override
    public List<ApplicationModel> listApplyAll(int resumeId) {
        List<ApplicationModel> applicationList = applicationMapper.listApplicationAll(resumeId);
        return applicationList;
    }

    @Override
    public List<ApplicationModel> listApplyInfoByHr(int hrId) {
        List<ApplicationModel> applicationList = applicationMapper.listApplicationByHrId(hrId);
        return applicationList;
    }

    @Override
    public boolean allowApplication(int applicationId, String reply) {
        int result = applicationMapper.allowApplication(applicationId,reply);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean refuseApplication(int applicationId, String reply) {
        int result = applicationMapper.refuseApplication(applicationId, reply);
        if (result > 0) {
            return true;
        }
        return false;
    }
}
