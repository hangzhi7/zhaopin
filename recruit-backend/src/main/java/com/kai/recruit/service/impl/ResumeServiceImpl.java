package com.kai.recruit.service.impl;

import org.springframework.stereotype.Service;
import com.kai.recruit.model.ResumeModel;
import com.kai.recruit.mapper.ResumeMapper;
import com.kai.recruit.service.ResumeService;

import javax.annotation.Resource;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Resource
    private ResumeMapper resumeMapper;

    @Override
    public ResumeModel getResumeById(int userId) {

        return resumeMapper.getResumeById(userId);
    }

    @Override
    public boolean updateResume(ResumeModel resumeModel) {

        if (resumeMapper.saveResume(resumeModel) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean createResume(ResumeModel resumeModel) {

        if (resumeMapper.createResume(resumeModel) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ResumeModel getResumeByRId(int resumeId) {
        return  resumeMapper.getResumeByRId(resumeId);
    }

}
