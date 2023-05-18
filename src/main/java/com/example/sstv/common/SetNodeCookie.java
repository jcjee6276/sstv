package com.example.sstv.common;

import com.example.sstv.user.User;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;


/**
 * 1. spring에서 로그인시 해당 user의 정보를 node로 보냄
 * 2. 노드에서 user의 sessionID를 보냄
 * 3. spring에서는 이 sessionID를 받아서 user의 브라우저에 심어준다.
 */
public class SetNodeCookie {

    public void sendUserToNodeServer(User user) {
        String url = "http://localhost:3000/nodeCookie";


        RestTemplate restTemplate = new RestTemplate();

        //set Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Data data = new Data("success", user);

        HttpEntity<Data> httpEntity = new HttpEntity<>(data, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        String responseBody = response.getBody();
        System.out.println("[SetNodeCookie] responseBody = " + responseBody);

    }
}
