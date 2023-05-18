package com.example.sstv.community.service;

import com.example.sstv.community.Community;
import com.example.sstv.community.DAO.CommunityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
