package com.kai.recruit.pojo;

import com.kai.recruit.model.PositionModel;

import java.sql.Timestamp;

public class CommentPositionBO extends PositionModel{

    private int commentId;
    private int type;
    private String content;
    private Timestamp releaseTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }
}
