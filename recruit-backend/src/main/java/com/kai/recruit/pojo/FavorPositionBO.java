package com.kai.recruit.pojo;

import com.kai.recruit.model.PositionModel;

public class FavorPositionBO extends PositionModel {

    private int favorId;
    private int userId;

    public int getFavorId() {
        return favorId;
    }

    public void setFavorId(int favorId) {
        this.favorId = favorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
