package com.example.sstv.user.restController;

import com.example.sstv.common.Data;
import com.example.sstv.common.NodeCookie;
import com.example.sstv.common.Search;
import com.example.sstv.fan.Service.FanService;
import com.example.sstv.user.CoinHistroy;
import com.example.sstv.user.Service.UserService;
import com.example.sstv.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user/*")
public class userRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private FanService fanService;

    @Autowired
    public userRestController(UserService userService) {
        this.userService = userService;}

    @PostMapping(value="addUser")
    public Data addUser(@RequestBody User user){
        userService.addUser(user);
        Data data = new Data("success", "(일반)회원가입 완료");
        return data;
    }

    @GetMapping(value="addSNSUser")
    public Data addSNSUser(@RequestParam(value = "code", required = false) String code, HttpSession session, HttpServletRequest request) throws Exception {
        String access_Token ="";
        access_Token = userService.getAccessToken(code);
        System.out.println("token 발급 완료! :: " +access_Token);

        //회원 정보 받아오기(from 네이버)
        Map<String, Object> userInfo = userService.getUserInfo(access_Token);
        System.out.println("userInfo..! 넌 내꺼얌! :: "+userInfo);
        String snsUserId = (String)userInfo.get("id");
        System.out.println(snsUserId);

        session.setAttribute("user", snsUserId);
        session = request.getSession();

        Data data = new Data("success", snsUserId);
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

        //DB에 저장된 user정보 info로 가져오고 blackList 정보 추가
        User info = userService.getUser(user.getUserId());
        info.setBlackList(fanService.getBlackList(info.getUserId()));
        System.out.println(info.getUserId());
        System.out.println("blackList CHECK :: " +fanService.getBlackList(info.getUserId()));

        if(user.getPassword().equals(info.getPassword())){
            //회원 정보&블랙리스트 세션에 저장
            System.out.println("아이디 패스워드 일치");
            session.setAttribute("user", info);
//            Cookie cookie = nodeCookie.getNodeCookie(info);
//            response.addCookie(cookie);
        }
//        List<String> blackList = fanService.getBlackList(info.getUserId()).getBlackUser();
        //로그인되며, 회원탈퇴 절차 취소
        userService.removeUserCancle(user);

        Data data = new Data("success", info);
        return data;
    }

    @GetMapping(value="login")
    public Data loginSessionCheck(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            return null;
        }
        Data data = new Data("success",user);
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
    public Data findId(@RequestBody String phone) throws Exception {
        System.out.println("phone..? "+phone);
        //json 형식으로 받아온 data 처리
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(phone);
        String phoneNum = jsonNode.get("phone").asText();
        System.out.println("phone :: "+phoneNum);

        String userId = userService.findId(phoneNum).getUserId();

        if(userService.getUser(userId).getUserType() == 1) {
            System.out.println("해당 회원은 sns회원");
            userId = null;
        }

        System.out.println("userService.findId(phone) :: " +userId);

        Data data = new Data("success", "userId");
        return data;
    }

    @PostMapping (value="findPasswd")
    public Data findPasswd(@RequestBody User user){
        System.out.println("비밀번호 찾기");

            userService.findPasswd(user);

        Data data = new Data("success", user);
        return data;
    }

    @PostMapping (value="getAdminUserList")
    public Data getAdminUserList(@RequestBody Search search ) {
        System.out.println("전체 회원목록 조회");
        System.out.println(search.getSearchKeyword());
        System.out.println(search.getSearchCondition());
        System.out.println("search ..?"+search);
        System.out.println("searchCondition :: "+search.getSearchCondition());
        System.out.println("searchKeyword ::"+search.getSearchKeyword());

        Data data = new Data("success", userService.getAdminUserlist(search));
        return data;
    }

    @PostMapping (value="sendSMS")
    public Data sendSMS(@RequestBody String phone, HttpSession session , HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        //json 형식으로 받아온 data 처리
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(phone);
        String phoneCheck = jsonNode.get("phone").asText();
        System.out.println("phoneCheck :: "+phoneCheck);

        //인증번호 랜덤한 6자리 생성
        Random random = new Random();
        int min = 100000; // 최소값 (6자리)
        int max = 999999; // 최대값 (6자리)
        int randomNumber = random.nextInt(max - min + 1) + min;
        String rand = String.valueOf(randomNumber);
        //랜덤번호 인증을 위해 session에 값 저장 (3분)
        session.setAttribute("randCode",rand);
        session.setMaxInactiveInterval(180);

        //문자 발송
        userService.sendSMS(phoneCheck, rand, request);
        Data data = new Data("success", "문자 전송 완료");
        return data;
    } //RequestBody-json으로 받아오니 원하는 값만 받아오지 않았음({"phone":"010~~~~"})으로 받아와서 Request Param으로 수정

    @PostMapping (value="phoneCheck")
    public Data phoneCheck(@RequestBody String code, HttpSession session) throws JsonProcessingException {
        System.out.println(code);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(code);
        String smsCode = jsonNode.get("code").asText();
        System.out.println("smsCode :: "+smsCode);

        if (session != null && session.getAttribute("randCode") != null) {
            String smsRand = (String) session.getAttribute("randCode");
            if (smsRand.equals(smsCode)) {
                session.removeAttribute("randCode");
                System.out.println("인증 번호 일치.");
            }
            Data data = new Data("success", "휴대폰 인증 완료");
            return data;
        } else {
            Data data = new Data("fail","인증 실패");
            return data;
        }
    }
    @PostMapping ( value="addCoinHistory")
    public Data addCoinHistory(@RequestBody CoinHistroy coinHistroy){
        userService.addCoinHistory(coinHistroy);
        Data data = new Data("success", "코인 사용내역 등록 완료.");
        return data;
    }

    @GetMapping ( value="getCoinHistory/{userId}")
    public Data getCoinHistory(@PathVariable String userId){
        System.out.println(userId);
        Data data = new Data("success", userService.getCoinHistory(userId));
        return data;
    }

    @GetMapping (value="kakaoLogin")
    public Data kakaoLogin(@RequestParam(value = "code", required = false) String code,HttpSession session) throws Exception {
        System.out.println(code);
        //발급 받아온 접근 token
        String access_token = userService.getkakaoToken(code);
        System.out.println("access_token :: "+access_token);
        Map<String, Object> kakaoUserInfo = userService.getKakaoInfo(access_token);
        String snsUserId = (String)kakaoUserInfo.get("id");
        System.out.println(snsUserId);

        session.setAttribute("user", snsUserId);

        Data data = new Data("success",kakaoUserInfo);
        return data;
    }


}
