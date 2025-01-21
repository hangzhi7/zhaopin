package com.kai.recruit.service.impl;

import com.kai.recruit.mapper.ReplyMapper;
import com.kai.recruit.model.ReplyModel;
import com.kai.recruit.service.ReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    @Override
    public boolean replyComment(int commentId, int userId, int replyUserId, String content){

        //获取当前日期时间
        java.util.Date date = new java.util.Date();
        Timestamp replyTime = new Timestamp(date.getTime());

        int result = replyMapper.saveReply(commentId,userId,replyUserId,content,replyTime);
        if(result > 0){
            return true;
        }
        return false;
    }

    @Override
    public  ArrayList<ReplyModel> getReply(int commentId){
        ArrayList<ReplyModel> replyList = replyMapper.listReply(commentId);
        return replyList;
    }

}
