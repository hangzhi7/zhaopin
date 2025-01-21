package com.kai.recruit.service;

import com.kai.recruit.model.FriendUrlsModel;

import java.util.ArrayList;

public interface FriendUrlService {
    ArrayList<FriendUrlsModel> listUrls();

    boolean createUrl(String url, String name);

    boolean updateUrl(String url, String name, int urlId);

    boolean delUrl(int urlId);
}
