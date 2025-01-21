package com.kai.recruit.service.impl;

import com.kai.recruit.mapper.CategoryMapper;
import com.kai.recruit.mapper.FriendUrlMapper;
import com.kai.recruit.model.FriendUrlsModel;
import com.kai.recruit.service.FriendUrlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service("FriendUrlService")
public class FriendUrlServiceImpl implements FriendUrlService {

    @Resource
    private FriendUrlMapper friendUrlMapper;

    @Override
    public ArrayList<FriendUrlsModel> listUrls() {
        return friendUrlMapper.listUrls();
    }

    @Override
    public boolean createUrl(String url, String name) {
        if (friendUrlMapper.CreateUrl(url, name) > 0) {
            return  true;
        }
        return false;
    }

    public boolean updateUrl(String url, String name, int urlId) {
        if (friendUrlMapper.updateFriendUrl(url,name,urlId) > 0) {
            return true;
        }
        return false;
    }

    public boolean delUrl(int urlId) {
        if (friendUrlMapper.delFriendUrl(urlId) > 0) {
            return true;
        }
        return false;
    }
}
