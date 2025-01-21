package com.kai.recruit.model;

import java.sql.Timestamp;

public class CommentModel {

    private int commentId;
    private int type;
    private String content;
    private Timestamp releaseTime;
    private int userId;
    private int positionId;

    public int getCommentId() {return this.commentId;}

    public void setCommentId(int commentId) {this.commentId = commentId;}

    public int getType() {return this.type;}

    public void setType(int type) {this.type = type;}

    public String getContent() {return this.content;}

    public void setContent(String content) {this.content = content;}

    public Timestamp getReleaseTime() {return this.releaseTime;}

    public void setReleaseTime(Timestamp releaseTime) {this.releaseTime = releaseTime;}

    public int getUserId() {return this.userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getPositionId() {return this.positionId;}

    public void setPositionId(int positionId) {this.positionId = positionId;}


}
