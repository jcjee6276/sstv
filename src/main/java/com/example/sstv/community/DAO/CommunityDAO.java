package com.example.sstv.community.DAO;

import com.example.sstv.community.Comments;
import com.example.sstv.community.Community;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Mapper
public interface CommunityDAO {
    Community getWriting(int writingNo);

    void addWriting(Community community);

    void deleteWriting(int writingNo);

    void updateWriting(Community community);

    void addComments(Comments comments);

    List<Comments> getCommentsList(int writingNo);

    void deleteComments(int commentsNo);

    List<Community> getWritingList(String hostUserId);

    int getWritingCount(String hostUserId);
}
