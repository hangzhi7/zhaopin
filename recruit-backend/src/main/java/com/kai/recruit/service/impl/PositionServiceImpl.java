package com.kai.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.kai.recruit.model.DepartmentModel;
import com.kai.recruit.model.PositionModel;
import com.kai.recruit.model.UserModel;
import com.kai.recruit.mapper.CompanyMapper;
import com.kai.recruit.mapper.DepartmentMapper;
import com.kai.recruit.mapper.PositionMapper;
import com.kai.recruit.pojo.PositionCompanyBO;
import com.kai.recruit.service.PositionService;
import com.kai.recruit.util.RecPositionUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private CompanyMapper companyMapper;

    /**
     * 分页推荐职位
     *
     * @param user
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<PositionCompanyBO> recPosition(UserModel user, int page, int limit) {

        //所有职位列表
        List<PositionModel> posList = new ArrayList<>();
        posList = positionMapper.listPosAll();

        //计算得推荐职位列表
        List<PositionCompanyBO> recList = new ArrayList<>();

        //所有职位Id -> 点击量
        HashMap<Integer, Integer> posMap = new HashMap<Integer, Integer>();
        for (PositionModel pos : posList) {
            posMap.put(pos.getPositionId(), pos.getHits());
        }

        RecPositionUtil rec = new RecPositionUtil();

        //返回推荐职位ArrayList
        recList = rec.recommend(posMap, user);

//        PageHelper.startPage(page,limit);
//        PageInfo<PositionCompanyBO> pageInfo = new PageInfo<>(recList);       0-6 6-12 12-18

        //(p-1)*6 6p
        LOGGER.debug("Exit recPosition method");

        return recList.subList(limit * page - limit, limit * page);
    }

    /**
     * 分页职位搜索
     *
     * @param keyword
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo<PositionCompanyBO> searchPosition(String keyword,int categoryId, String workProvince,String workCity,int educationId, int page, int limit) {

        PageHelper.startPage(page, limit);

        List<PositionCompanyBO> searchList = positionMapper.listSearchPos( "%"+keyword+"%" ,categoryId,workProvince,workCity,educationId);

        return new PageInfo<>(searchList);
    }

    // 各分类职位索引页
    @Override
    public PageInfo<PositionCompanyBO> listPosition(int categoryId, int page, int limit) {
        int total = positionMapper.countCategoryPos(categoryId);
        PageHelper.startPage(page, limit);
        List<PositionCompanyBO> posList = positionMapper.listCategoryPos(categoryId);
        PageInfo<PositionCompanyBO> pagination = new PageInfo<>(posList);
        pagination.setTotal(total);
        return pagination;
    }

    /**
     * 根据职位Id查找返回职位
     *
     * @param positionId
     * @return
     */
    @Override
    public PositionModel getPositionById(int positionId) {
        return positionMapper.getPosition(positionId);
    }

    /**
     *采用PositionModel而不是PositionCompanyBO，因为我不想让hr权限过高
     * @param hrid
     * @return
     */
    @Override
    public PageInfo<PositionModel> listPositionByHr(int hrid,int page, int limit) {
        int total = positionMapper.countHRPos(hrid);
        PageHelper.startPage(page, limit);
        List<PositionModel> posList = listPositionByHr(hrid);
        PageInfo<PositionModel> pagination = new PageInfo<>(posList);
        pagination.setTotal(total);
        return pagination;
    }

    @Override
    public List<PositionModel> listPositionByHr(int hrid) {
        return positionMapper.listHRPos(hrid);
    }


    @Override
    public boolean updateHits(int positionId) {
        if (positionMapper.updateHits(positionId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int deletePosition(int positionId) {
        return positionMapper.delete(positionId);
    }

    @Override
    public int updatePosition(PositionModel positionModel) {
        return positionMapper.updatePosition(positionModel);
    }

    @Override

    public int updatePositionState(int statePub, int posId) {

        return positionMapper.updatePositionState(statePub,posId);
    }

    @Override
    public int savePosition(PositionModel positionModel) {
        return positionMapper.savePosition(positionModel);
    }

    @Override
    public boolean setCategory(int categoryName, int description) {
        if (positionMapper.setCategory(categoryName, description) > 0){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<PositionModel> listPosAll() {
        return positionMapper.listPosAll();
    }

}
