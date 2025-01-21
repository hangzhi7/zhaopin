package com.kai.recruit.model;

import java.sql.Timestamp;

public class ReplyModel {

    private int replyId;
    private int commentId;
    private int userId;
    private int replyUserId;
    private String content;
    private Timestamp replyTime;

    public int getReplyId() {return this.replyId;}

    public void setReplyId(int replyId) {this.replyId = replyId;}

    public int getCommentId() {return this.commentId;}

    public void setCommentId(int commentId) {this.commentId = commentId;}

    public int getReplyUserId() {return this.replyUserId;}

    public void setReplyUserId(int replyUserId) {this.replyUserId = replyUserId;}

    public String getContent() {return this.content;}

    public void setContent(String content) {this.content = content;}

    public Timestamp getReplyTime() {return this.replyTime;}

    public void setReplyTime(Timestamp replyTime) {this.replyTime = replyTime;}

    public int getUserId() {return this.userId;}

    public void setUserId(int userId) {this.userId = userId;}

}
