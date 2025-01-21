package com.kai.recruit.service;

import com.kai.recruit.model.ResumeModel;

public interface ResumeService {

    ResumeModel getResumeById(int userId);

    boolean updateResume(ResumeModel resumeModel);

    boolean createResume(ResumeModel resumeModel);

    ResumeModel getResumeByRId(int resumeId);
}
