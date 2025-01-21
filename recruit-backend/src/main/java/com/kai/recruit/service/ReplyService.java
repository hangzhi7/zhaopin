package com.kai.recruit.service;

import com.kai.recruit.model.ReplyModel;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface ReplyService {

    ArrayList<ReplyModel> getReply(int commentId);

    boolean replyComment(int commentId, int userId, int replyUserId, String content);
}
