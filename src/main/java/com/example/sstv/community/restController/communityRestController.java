package com.example.sstv.community.restController;

import com.example.sstv.common.Data;
import com.example.sstv.community.Community;
import com.example.sstv.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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

    @GetMapping(value="addWriting")
    public Data addWriting(Community community) {
        community.setGuestUserId("user1");
        community.setHostUserId("user3");
        community.setTitle("test111");
        community.setContent("qwer222");
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



}
