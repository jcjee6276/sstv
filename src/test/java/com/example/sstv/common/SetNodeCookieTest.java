package com.example.sstv.common;

import com.example.sstv.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SetNodeCookieTest {

    @Test
    void SetNodeCookieTest() {
        User user = new User();
        user.setUserId("이동욱");
        user.setPassword("password");

        SetNodeCookie setNodeCookie = new SetNodeCookie();

        setNodeCookie.sendUserToNodeServer(user);
    }
}
