package com.example.sstv.community.DAO;

import com.example.sstv.community.Comments;
import com.example.sstv.community.Community;
import org.apache.ibatis.annotations.Mapper;

import java.util.concurrent.CountDownLatch;

@Mapper
public interface CommunityDAO {
    Community getWriting(int writingNo);

    Community addWriting(Community community);

    void deleteWriting(int writingNo);

    void updateWriting(Community community);

    Comments addComments(Comments comments);
}
