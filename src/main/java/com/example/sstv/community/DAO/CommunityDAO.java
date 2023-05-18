package com.example.sstv.community.DAO;

import com.example.sstv.community.Community;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityDAO {
    Community getWriting(int writingNo);
}
