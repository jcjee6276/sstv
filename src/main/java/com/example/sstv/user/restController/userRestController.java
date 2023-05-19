package com.example.sstv.user.restController;

import com.example.sstv.common.Data;
import com.example.sstv.common.NodeCookie;
import com.example.sstv.user.Service.UserService;
import com.example.sstv.user.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/user/*")
public class userRestController {
    @Autowired
    private UserService userService;

    @Autowired
    public userRestController(UserService userService) { this.userService = userService; }

    @PostMapping(value="addUser")
    public Data addUser(@RequestBody User user){
        userService.addUser(user);
        Data data = new Data("success", "(일반)회원가입 완료");
        return data;
    }

    @GetMapping(value="addSNSUser")
    public Data addSNSUser(@PathVariable(value = "code", required = false) String code, HttpSession session, HttpServletRequest request) throws Exception {
        String access_Token ="";
        access_Token = userService.getAccessToken(code);
        System.out.println("token 발급 완료! :: " +access_Token);

        //회원 정보 받아오기(from 네이버)
        Map<String, Object> userInfo = userService.getUserInfo(access_Token);
        System.out.println("userInfo..! 넌 내꺼얌! :: "+userInfo);

        User user = new User();
        String id = (String)userInfo.get("id");
        String name = (String)userInfo.get("name");
        String email = (String)userInfo.get("email");
        String phone = (String)userInfo.get("mobile");
        user.setUserId(id);
        user.setUserName(name);
        user.seteMail(email);
        user.setPhone(phone);

        session.setAttribute("user", user);
        session = request.getSession();

        Data data = new Data("success", userInfo);
        return data;
    }

    // 회원 정보 조회
    @GetMapping(value="getUser/{userId}")
    public Data getUser(@PathVariable String userId) {
        System.out.println("회원 정보 조회");
        Data data = new Data("success", userService.getUser(userId));
        return data;
    }

    @PostMapping(value="login")
    public Data login(@RequestBody User user , HttpSession session, HttpServletResponse response){
        System.out.println("여기는 ! Login RestController");

        NodeCookie nodeCookie = new NodeCookie();

        User info = userService.getUser(user.getUserId());
        System.out.println(info.getUserId());
        if(user.getPassword().equals(info.getPassword())){
            session.setAttribute("user", info);
//            Cookie cookie = nodeCookie.getNodeCookie(info);
//            response.addCookie(cookie);
        }
        userService.removeUserCancle(user);


        Data data = new Data("success", info);
        return data;
    }

    @GetMapping(value = "logout")
    public Data logout(HttpSession session){
        System.out.println("logout.. BYEBYE!");
        session.invalidate();

        Data data = new Data("success", "logout");
        return data;
    }

    @PostMapping(value = "removeUserStart")
    public Data removeUserStart(@RequestBody User user){
        System.out.println("removeUser Start");
        userService.removeUserStart(user);

        Data data = new Data("success", user);
        return data;
    }

    @PostMapping(value="updateUser")
    public Data updateUser(@RequestBody User user){
        System.out.println("updateUser..!");
        userService.updateUser(user);

        Data data = new Data("success", user);
        return data;
    }

    @GetMapping(value="checkUserId/{userId}")
    public Data checkUserId(@PathVariable String userId){
        System.out.println("아이디Check");
        String isEnabled;
        if (userService.checkUserId(userId) == true){
            isEnabled = "useOK";
        }else{
            isEnabled = "useNo";
        }
        Data data = new Data("success", isEnabled);
        return data;
    }

    @GetMapping(value="checkUserNickname/{UserNickname}")
    public Data checkUserNickname(@PathVariable String UserNickname){
        System.out.println("닉네임Check");
        String isEnabled;
        if (userService.checkUserNickname(UserNickname) == true){
            isEnabled = "useOK";
        }else{
            isEnabled = "useNo";
        }
        Data data = new Data("success", isEnabled);
        return data;
    }

    @PostMapping (value="findId")
    public Data findId(@RequestParam String phone){
        System.out.println("아이디 찾기");

        Data data = new Data("success", userService.findId(phone));
        return data;
    }

    @PostMapping (value="findPasswd")
    public Data findPasswd(@RequestBody User user){
        System.out.println("비밀번호 찾기");

            userService.findPasswd(user);

        Data data = new Data("success", user);
        return data;
    }

}
