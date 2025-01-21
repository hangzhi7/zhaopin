package com.kai.recruit.service;

import com.kai.recruit.model.EducationModel;
import java.util.ArrayList;

public interface EducationService {

    ArrayList listEducation();

    boolean createEducation(EducationModel educationModel);

    boolean delEducation(EducationModel educationModel);

    boolean updateEducation(EducationModel educationModel);

}
