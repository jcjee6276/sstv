package com.example.sstv.deal.DAO;

//<<<<<<< HEAD:src/main/java/com/example/sstv/streaming/DAO/UserDAO.java

import com.example.sstv.common.Search;
import com.example.sstv.deal.Purchase;
import com.example.sstv.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/*
 * Mapper의 id와 interface의 method명은 동일해야 @Mapper를 통해 Mapper.xml의 정보를 가져 올 수 있다.
 * */
@Mapper
public interface PurchaseDAO {

    //    void addPurchase(Purchase purchase);
    Purchase getPurchaseList(int paymentNo);

    void addPurchase(Purchase purchase);

}
