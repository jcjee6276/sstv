package com.example.sstv.community.restController;

import com.example.sstv.common.Data;
import com.example.sstv.community.Community;
import com.example.sstv.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class communityRestController {
    private CommunityService communityService;

    @Autowired
    public communityRestController(CommunityService communityService){
        this.communityService = communityService;
    }


    @GetMapping("/community")
    public Data test(Community community) {
       community = communityService.getWriting(1);

        Data data = new Data("success", community);

        return data;
    }

}
