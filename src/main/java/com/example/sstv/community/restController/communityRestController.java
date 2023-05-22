package com.example.sstv.community.restController;

import com.example.sstv.common.Data;
import com.example.sstv.community.Comments;
import com.example.sstv.community.Community;
import com.example.sstv.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/community/*")
public class communityRestController {
    private CommunityService communityService;

    @Autowired
    public communityRestController(CommunityService communityService){
        this.communityService = communityService;
    }


    @GetMapping(value="getWriting/{writingNo}")
    public Data getWriting(@PathVariable int writingNo) {


        Data data = new Data("success", communityService.getWriting(writingNo));

        return data;
    }

    @PostMapping(value="addWriting", consumes = "application/json;")
    public Data addWriting(@RequestBody Community community) {
        System.out.println("test community : "+community);
        community.setHostUserId("user1");
        community.setGuestUserId("user3");
        communityService.addWriting(community);
        Data data = new Data("success", "성공");
        return data;
    }

    @GetMapping(value="deleteWriting/{writingNo}")
    public Data deleteWriting(@PathVariable int writingNo){
        communityService.deleteWriting(writingNo);
        Data data = new Data("success", writingNo+"삭제 성공");
        return data;
    }

    @GetMapping(value="updateWriting/{writingNo}")
    public Data updateWriting(@PathVariable int writingNo){

        Data data = new Data("success",communityService.getWriting(writingNo));

        return data;
    }

    @PostMapping(value="updateWriting", consumes = "application/json;")
    public Data updateWriting(@RequestBody Community community){
        System.out.println(community);
        communityService.updateWriting(community);
        Data data = new Data("success", "업데이트 성공");
        return data;

    }

    @PostMapping(value="addComments", consumes = "application/json;")
    public Data addComments(@RequestBody Comments comments) {
        System.out.println("test : "+ comments);

        communityService.addComments(comments);

        Data data = new Data("success", "댓글 추가 성공");
        return data;
    }

    @GetMapping(value="getCommentsList/{writingNo}")
    public Data getCommentsList(@PathVariable int writingNo){
        Map<String, Object> map = communityService.getCommentsList(writingNo);
        Data data = new Data("success", map.get("list"));
        return data;
    }

    @GetMapping(value="deleteComments/{commentsNo}")
    public Data deleteComments(@PathVariable int commentsNo ){
        communityService.deleteComments(commentsNo);
        Data data = new Data("success", "댓글 삭제 성공");
        return data;
    }

    @GetMapping(value="writingList") // User 받아서 writinglist 걸러내기 @pathvariable userid 추가
    public Data getWritingList() {
        Map<String, Object> map = communityService.getWritingList();
        map.put("count", communityService.getWritingCount());
        Data data = new Data("success",  map.get("list"), map.get("count"));
        return data;
    }

}
