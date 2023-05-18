package com.example.sstv.streaming.DAO;

import com.example.sstv.streaming.User;
import org.apache.ibatis.annotations.Mapper;


/*
* Mapper의 id와 interface의 method명은 동일해야 @Mapper를 통해 Mapper.xml의 정보를 가져 올 수 있다.
* */
@Mapper
public interface UserDAO {
    void addUser(User user);
    User getUser(String userId);
}