package com.example.sstv.streaming;

import com.example.sstv.common.Data;
import com.example.sstv.common.NodeCookie;
import com.example.sstv.user.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/")
    public Data test(HttpServletResponse response, HttpSession session) {
        NodeCookie nodeCookie = new NodeCookie();

        User user = new User();
        user.setUserId("userId");
        user.setPassword("pwd");

        session.setAttribute("user", user);


        Cookie cookie = nodeCookie.getNodeCookie(user);
        response.addCookie(cookie);

        Data data = new Data("success", user);

        return data;
    }

    @GetMapping("/logout")
    public String logout(@CookieValue("NSESSIONID") String cookieName, HttpServletResponse response) {
        NodeCookie nodeCookie = new NodeCookie();

        nodeCookie.removeNodeCookie(cookieName);
        Cookie cookie = nodeCookie.removeSpringCookie(cookieName);

        response.addCookie(cookie);

        return "logout";
    }
}
