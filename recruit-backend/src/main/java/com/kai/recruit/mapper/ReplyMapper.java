package com.kai.recruit.mapper;

import com.kai.recruit.model.ReplyModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface ReplyMapper {

    @Select("select * from reply where commentId = #{commentId} ORDER BY replyId DESC")
    ArrayList<ReplyModel> listReply(@Param("commentId") int commentId);

     @Insert("insert into reply(commentId,userId,replyUserId,content,replyTime) values (#{commentId},#{userId},#{replyUserId},#{content},#{replyTime})")
     int saveReply( @Param("commentId") int commentId, @Param("userId") int userId,@Param("replyUserId") int replyUserId, @Param("content") String content, @Param("replyTime") Timestamp replyTime);
}

