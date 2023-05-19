package com.example.sstv.user.DAO;

//<<<<<<< HEAD:src/main/java/com/example/sstv/streaming/DAO/UserDAO.java

import com.example.sstv.common.Search;
import com.example.sstv.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/*
* Mapper의 id와 interface의 method명은 동일해야 @Mapper를 통해 Mapper.xml의 정보를 가져 올 수 있다.
* */
@Mapper
public interface UserDAO {

    void addUser(User user);
    User getUser(String userId);
    User findId(String phone);
    User findPasswd(String phone);
    List<User> getAdminUserlist(Search search);
    void removeUserStart(User user);
    void removeUserCancle(User user);
    void updateUser(User user);

}
