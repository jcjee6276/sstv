package com.example.sstv.fan.restController;

import com.example.sstv.common.Data;
import com.example.sstv.fan.Fan;
import com.example.sstv.user.Service.UserService;
import com.example.sstv.fan.Service.FanService;
import com.example.sstv.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fan/*")
public class fanRestController {
    @Autowired
    private FanService fanService;
    @Autowired
    private UserService userService;

    @Autowired
    public fanRestController(FanService fanService, UserService userService) {
        this.fanService = fanService;
        this.userService = userService;
    }

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
        System.out.println("controller의 userId :: "+userId);
        System.out.println("fanlist 잘 받아오나..?"+fanService.getFollowList(userId));
        //follow 유저의 id List
        List<String> followList = fanService.getFollowList(userId);
        List<User> followUserList = new ArrayList<>();
        System.out.println("닉네임으로 출력해보자!"+followList);
        //list에 담긴 id로 userNickname을 조회
        for (int i = 0; i< followList.size(); i++){
            if( followList != null){
                String id = followList.get(i);
                followUserList.add(userService.getUserNickname(id));
            }
        }
        System.out.println("nickName 제대로 가져오니..? :: "+followUserList);

        List<String> followUserNicknameList = new ArrayList<>();

        for (User user : followUserList) {
            if (user != null) {
                String userNickname = user.getUserNickname();
                followUserNicknameList.add(userNickname);
            }
        }
        System.out.println("팔로우 회원 닉네임 ..?"+followUserNicknameList);


        Data data = new Data("success", followUserNicknameList);
        return data;
    }
    @GetMapping(value="getFollowing/{followUser}")
    public Data getFollowing(@PathVariable String followUser){

        List<String> followingList = fanService.getFollowingList(followUser);
        List<User> followingUserList = new ArrayList<>();
        System.out.println("닉네임으로 출력해보자!"+followingList);
        //list에 담긴 id로 userNickname을 조회
        for (int i = 0; i< followingList.size(); i++){
            if( followingList != null){
                String follower = followingList.get(i);
                followingUserList.add(userService.getUserNickname(follower));
            }
        }
        List<String> followingUserNicknameList = new ArrayList<>();
        for (User user : followingUserList) {
            if (user != null) {
                String userNickname = user.getUserNickname();
                followingUserNicknameList.add(userNickname);
            }
        }
        Data data = new Data("success", followingUserNicknameList);
        return data;
    }

    @GetMapping(value="getBlackListOwner/{blackUser}")
    public Data getBlackListOwner(@PathVariable String blackUser){
        fanService.getBlackListOwner(blackUser);
        System.out.println(fanService.getBlackListOwner(blackUser));
        Data data = new Data("success",fanService.getBlackListOwner(blackUser));
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
