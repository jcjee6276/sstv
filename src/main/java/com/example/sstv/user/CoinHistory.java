package com.example.sstv.user;

import java.util.Date;

public class CoinHistory {
    private int coinHistoryNo;
    private String userId;
    private int ticketProdNo;
    private int prodName;
    private int price;
    private Date payDate;

    @Override
    public String toString() {
        return "CoinHistory{" +
                "coinHistoryNo=" + coinHistoryNo +
                ", userId='" + userId + '\'' +
                ", ticketProdNo=" + ticketProdNo +
                ", prodName=" + prodName +
                ", price=" + price +
                ", payDate=" + payDate +
                '}';
    }

    public int getCoinHistoryNo() {
        return coinHistoryNo;
    }

    public void setCoinHistoryNo(int coinHistoryNo) {
        this.coinHistoryNo = coinHistoryNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTicketProdNo() {
        return ticketProdNo;
    }

    public void setTicketProdNo(int ticketProdNo) {
        this.ticketProdNo = ticketProdNo;
    }

    public int getProdName() {
        return prodName;
    }

    public void setProdName(int prodName) {
        this.prodName = prodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
