package com.kai.recruit.service;

import com.github.pagehelper.PageInfo;
import com.kai.recruit.pojo.UserCommentBO;
import java.util.ArrayList;

public interface CommentService {

    PageInfo<UserCommentBO> listComment(int positionId, int page, int limit);

    ArrayList<UserCommentBO> getComments(int positionId);

    boolean commentPosition(int type, String content, int userId, int positionId);
}
