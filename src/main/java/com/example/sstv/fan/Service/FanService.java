package com.example.sstv.fan.Service;


import com.example.sstv.fan.DAO.FanDAO;
import com.example.sstv.fan.Fan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FanService {
    private FanDAO fanDAO;

    @Autowired
    public FanService(FanDAO fanDAO) {
        this.fanDAO = fanDAO;
    }
    public void addFollow(Fan fan){
        fanDAO.addFollow(fan);
    }
    public void addBlacklist(Fan fan){
        fanDAO.addBlacklist(fan);
    }
    //내가 팔로우한 회원 조회
    public List<String> getFollowList(String userId) {
        List<Fan> fanList = fanDAO.getFollow(userId);
        List<String> getFollowList = new ArrayList<>();
        for (Fan fan : fanList) {
            if (fan != null && fan.getUserId() != null) {
                getFollowList.add(fan.getUserId());
            }
        }
        return getFollowList;
    }
    //나를 팔로우한 회원 조회
    public List<String> getFollowingList(String followUser) {
        List<Fan> fanList = fanDAO.getFollowing(followUser);
        List<String> getFollowingList = new ArrayList<>();
        for (Fan fan : fanList) {
            if (fan != null && fan.getUserId() != null) {
                getFollowingList.add(fan.getFollowUser());
            }
        }
        return getFollowingList;
    }
    // 블랙리스트 조회
    public List<String> getBlackList(String userId) {
            List<Fan> fanList = fanDAO.getBlackList(userId);
            List<String> getBlackList = new ArrayList<>();
            for (Fan fan : fanList) {
                if (fan != null && fan.getBlackUser() != null) {
                    getBlackList.add(fan.getBlackUser());
                }
            }
            return getBlackList;
    }
    public void removeFollow(Fan fan){
        fanDAO.removeFollow(fan);
    }
    public void removeBlacklist(Fan fan){
        fanDAO.removeBlacklist(fan);
    }

}