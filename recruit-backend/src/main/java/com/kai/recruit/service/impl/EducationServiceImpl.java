package com.kai.recruit.service.impl;

import com.kai.recruit.mapper.EducationMapper;
import com.kai.recruit.model.EducationModel;
import com.kai.recruit.model.FriendUrlsModel;
import com.kai.recruit.service.EducationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service("EducationService")
public class EducationServiceImpl implements EducationService {
    @Resource
    private EducationMapper educationMapper;

    @Override
    public boolean createEducation(EducationModel educationModel){
        String name = educationModel.getName();
        int level = educationModel.getLevel();
        if (educationMapper.createEducation(name, level) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<EducationModel> listEducation(){
        return educationMapper.listEducation();
    }

    @Override
    public boolean delEducation(EducationModel educationModel){
        int id = educationModel.getId();
        if(educationMapper.delEducation(id) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEducation(EducationModel educationModel){
        int id = educationModel.getId();
        String name = educationModel.getName();
        int level = educationModel.getLevel();
        if (educationMapper.updateEducation(id, name, level) > 0) {
            return true;
        }
        return false;
    }
}
