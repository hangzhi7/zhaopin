package com.kai.recruit.service;

import com.github.pagehelper.PageInfo;
import com.kai.recruit.model.PositionModel;
import com.kai.recruit.model.UserModel;
import com.kai.recruit.pojo.PositionCompanyBO;

import java.util.ArrayList;
import java.util.List;


public interface PositionService {

    /**
     * 分页推荐职位
     *
     * @param user
     * @return
     */
    List<PositionCompanyBO> recPosition(UserModel user, int page, int limit);

    /**
     * 分页职位搜索
     *
     * @param keyword
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PositionCompanyBO> searchPosition(String keyword,int categoryId, String workProvince,String workCity,int educationId, int page, int limit);

    /**
     * 各分类职位索引页
     *
     * @param categoryId
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PositionCompanyBO> listPosition(int categoryId, int page, int limit);

    /**
     * 根据职位Id查找返回职位
     *
     * @param positionId
     * @return
     */
    PositionModel getPositionById(int positionId);

    /**
     * 根据hrid查询返回职位
     *
     * @param hrid
     * @return
     */
    PageInfo<PositionModel> listPositionByHr(int hrid, int page, int limit);

    List<PositionModel> listPositionByHr(int hrid);

    /**
     * 点击量+1
     *
     * @param positionId
     * @return
     */
    boolean updateHits(int positionId);

    /**
     * delete
     *
     * @param positionId
     */
    int deletePosition(int positionId);

    /**
     * update
     */
    int updatePosition(PositionModel positionModel);

    int updatePositionState(int statePub, int posId);

    int savePosition(PositionModel positionModel);

    boolean setCategory(int categoryId, int positionId);

    ArrayList<PositionModel> listPosAll();

//    List<PositionModel> showPosition(int hrId);
}
