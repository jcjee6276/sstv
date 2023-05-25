package com.example.sstv.deal;

import com.example.sstv.user.User;

import java.util.Date;

public class Purchase {
    private int paymentNo;      //결제프라이머리키
    private int paymentAmount;  // 결제금액
    private Date paymentDate;   // 결제날짜
    private int paymentMethod;  //결제수단
    private User user;

    private String userId;



    @Override
    public String toString() {
        return "Purchase{" +
                "paymentNo=" + paymentNo +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod=" + paymentMethod +
                ", user=" + user +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(int paymentNo) {
        this.paymentNo = paymentNo;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

