package com.example.sstv.community.service;

import com.example.sstv.common.Search;
import com.example.sstv.community.Comments;
import com.example.sstv.community.Community;
import com.example.sstv.community.DAO.CommunityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunityService {
    private final CommunityDAO communityDao;

    @Autowired
    public CommunityService(CommunityDAO communityDao) {
        this.communityDao = communityDao;
    }

    public Community getWriting(int writingNo) {
        return communityDao.getWriting(writingNo);
    }

    public void addWriting(Community community) {
         communityDao.addWriting(community);
    }

    public void deleteWriting(int writingNo) {
        communityDao.deleteWriting(writingNo);
    }

    public void updateWriting(Community community){
         communityDao.updateWriting(community);
    }

    public void addComments(Comments comments){
        communityDao.addComments(comments);
    }

    public Map<String, Object> getCommentsList(int writingNo){
        List<Comments> list = (List<Comments>) communityDao.getCommentsList(writingNo);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("list", list);
        return map;
    }

    public void deleteComments(int writingNo){
        communityDao.deleteComments(writingNo);
    }

    public Map<String, Object> getWritingList(String hostUserId) {
        List<Community> list = (List<Community>) communityDao.getWritingList(hostUserId);

        Map<String, Object> map = new HashMap<String,Object>();

        map.put("list", list);

        return map;
    }

    public int getWritingCount(String hostUserId) {
        int count = communityDao.getWritingCount(hostUserId);
        return count;
    }

    public Map<String, Object> getSearchWriting(String searchKeyword) {
        List<Search> list = (List<Search>) communityDao.getSearchWriting((searchKeyword));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);

        return map;
    }
}
