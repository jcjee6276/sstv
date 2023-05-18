package com.example.sstv.community;

import java.util.Date;

public class Community {
    private int writingNo;

    public int getWritingNo() {
        return writingNo;
    }

    public void setWritingNo(int writingNo) {
        this.writingNo = writingNo;
    }

    private String hostUserId;

    public String getHostUserId() {
        return hostUserId;
    }

    public void setHostUserId(String hostUserId) {
        this.hostUserId = hostUserId;
    }

    private String guestUserId;

    public String getGuestUserId() {
        return guestUserId;
    }

    public void setGuestUserId(String guestUserId) {
        this.guestUserId = guestUserId;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private Date regDate;

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Community{" +
                "writingNo=" + writingNo +
                ", hostUserId='" + hostUserId + '\'' +
                ", guestUserId='" + guestUserId + '\'' +
                ", title='" + title + '\'' +
                ", regDate=" + regDate +
                ", content='" + content + '\'' +
                '}';
    }
}
