package com.example.sstv.user.Service;

import com.example.sstv.common.Search;
import com.example.sstv.user.DAO.UserDAO;
import com.example.sstv.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserService {
    private UserDAO userDAO;

//    @Autowired
//    public void addUser(User user) {
//        userDAO.addUser(user);
//    }

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public void addUser(User user){
        userDAO.addUser(user);
    }
    public User getUser(String userId) {
        return userDAO.getUser(userId);
    }
    public User findId(String phone) {
        return userDAO.findId(phone);
    }
    public void findPasswd(User user) {
        userDAO.findPasswd(user);
    }
    public List<User> getAdminUserlist(Search search) {
        return userDAO.getAdminUserlist(search);
    }
    public void removeUserStart(User user) {
        userDAO.removeUserStart(user);
    }
    public void removeUserCancle(User user) {
        userDAO.removeUserCancle(user);
    }
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
    public boolean checkUserId(String userId) {
        boolean result=true;
        User user=userDAO.getUser(userId);
        if(user != null) {
            result=false; //--> 사용할 수 있을 경우 true
        }
        return result;
    }
    public boolean checkUserNickname(String userNickname) {
        boolean result=true;
        User user=userDAO.getUser(userNickname);
        if(user != null) {
            result=false; //--> 사용할 수 있을 경우 true
        }
        return result;
    }


    public String getAccessToken(String authorize_code) throws IOException {
        System.out.println("토큰 주세요..");
        String access_Token = "";
        String refresh_Token = "";
        String url = "https://nid.naver.com/oauth2.0/token";

        URL apiurl;
        apiurl = new URL(url);

        // HTTP 연결 생성
        HttpURLConnection con = (HttpURLConnection) apiurl.openConnection();

        // HTTP 요청 메소드 설정
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        // HTTP 요청에 필요한 파라미터 설정
        String postParams = "grant_type=authorization_code" +
                "&client_id=" + "oxyovmQ_xk_uAaUdHUKu" +
                "&redirect_uri=" + "http://192.168.0.21:8080/user/addSNSUser" +
                "&code=" + authorize_code +
                "&client_secret=" + "uFi6q1_u5O";
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        
        System.out.println("전달 파라미터 확인.. :: "+postParams);
        // HTTP 요청 본문에 파라미터 추가
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();

        int responseCode = con.getResponseCode();
        System.out.println("200..!!! :: " + responseCode);

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        String result = "";

        while ((line = br.readLine()) != null) {
            result += line;
        }
        System.out.println("response body : " + result);


        ObjectMapper obj = new ObjectMapper(); // jsonSimple 사용
        Map<String, Object> naver_token = obj.readValue(result, Map.class);

        System.out.println("naver token 발급 : " + naver_token);

        access_Token = naver_token.get("access_token").toString(); // access_token 추출
        refresh_Token = naver_token.get("refresh_token").toString(); // refresh_token 추출

        System.out.println("접근 토큰 : " + access_Token);
        System.out.println("갱신 토큰 : " + refresh_Token);


        return access_Token;
    }

    public Map<String, Object> getUserInfo(String access_Token) throws Exception {

        System.out.println("네이버야.. 정보.. 줄 수 있겠니..?");
        Map<String, Object> userInfo = new HashMap<>();
        String openApi = "https://openapi.naver.com/v1/nid/me";

        try {
            URL url = new URL(openApi);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestProperty("Authorization", "Bearer " + access_Token);
            connection.setRequestMethod("POST");

            int resCode = connection.getResponseCode();
            System.out.println("정보 잘 가져오니..? 200...!" +resCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result ="";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response Body : "+result);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> naverInfo = objectMapper.readValue(result, Map.class);

            System.out.println("naverInfo..? : "+naverInfo);


            Map<String, Object> properties = (Map<String, Object>) naverInfo.get("response");

            String id = properties.get("id").toString().substring(0,10);
            String email = properties.get("email").toString();
            String name = properties.get("name").toString();
            String mobile = properties.get("mobile").toString();

            userInfo.put("mobile", mobile);
            userInfo.put("name", name);
            userInfo.put("email", email);
            userInfo.put("id", id);

            if ( checkUserId(id) != false){
                User user = new User();
                user.setUserId(id);
                user.setPassword(id);
                user.seteMail(email);
                user.setUserName(name);

                userDAO.addSNSUser(user);

            }

        }catch (Exception e){
            System.out.println(e);
        }


        return userInfo;
    }
}
