package com.example.sstv.fan.restController;

import com.example.sstv.common.Data;
import com.example.sstv.fan.Fan;
import com.example.sstv.fan.Service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fan/*")
public class fanRestController {
    @Autowired
    private FanService fanService;

    @Autowired
    public fanRestController(FanService fanService) { this.fanService = fanService; }

    @PostMapping(value="addFollow")
    public Data addFollow(@RequestBody Fan fan){
        fanService.addFollow(fan);
        Data data = new Data("success", "팔로우 완료");
        return data;
    }

    @PostMapping(value="addBlacklist")
    public Data addBlacklist(@RequestBody Fan fan){
        fanService.addBlacklist(fan);
        Data data = new Data("success", "블랙리스트 등록 완료");
        return data;
    }

    @GetMapping(value="getFollow/{userId}")
    public Data getFollow(@PathVariable String userId){
        System.out.println(userId);
        System.out.println(fanService.getFollowList(userId));
        Data data = new Data("success", fanService.getFollowList(userId));
        return data;
    }
    @GetMapping(value="getFollowing/{followUser}")
    public Data getFollowing(@PathVariable String followUser){
        System.out.println(fanService.getFollowingList(followUser));
        Data data = new Data("success", fanService.getFollowingList(followUser));
        return data;
    }

    @PostMapping(value="removeFollow")
    public Data removeFollow(@RequestBody Fan fan){
        fanService.removeFollow(fan);
        Data data = new Data("success", "follow 취소 완료");
        return data;
    }

    @PostMapping(value="removeBalcklist")
    public Data removeBlacklist(@RequestBody Fan fan){
        System.out.println("userId :: "+fan.getUserId()+" balckUser :: "+fan.getBlackUser());
        fanService.removeBlacklist(fan);
        Data data = new Data("success", "blacklist 해제 완료.");
        return data;
    }

}
