package com.example.sstv.community;

import java.util.Date;

public class Comments {
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    private Community community;
    private int commentsNo;

    public int getCommentsNo() {
        return commentsNo;
    }

    public void setCommentsNo(int commentsNo) {
        this.commentsNo = commentsNo;
    }

    public String getCommentsUserId() {
        return commentsUserId;
    }

    public void setCommentsUserId(String commentsUserId) {
        this.commentsUserId = commentsUserId;
    }

    public int getWritingNo() {
        return writingNo;
    }

    public void setWritingNo(int writingNo) {
        this.writingNo = writingNo;
    }

    public int getReplayNo() {
        return replayNo;
    }

    public void setReplayNo(int replayNo) {
        this.replayNo = replayNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    private String commentsUserId;
    private int writingNo;

    @Override
    public String toString() {
        return "Comments{" +
                "community=" + community +
                ", commentsNo=" + commentsNo +
                ", commentsUserId='" + commentsUserId + '\'' +
                ", writingNo=" + writingNo +
                ", replayNo=" + replayNo +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    private int replayNo;
    private String content;
    private Date regDate;


}
