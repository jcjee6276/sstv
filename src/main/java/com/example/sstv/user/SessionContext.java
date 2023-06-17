package com.example.sstv.user;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionContext {

    private Map<String, HttpSession> sessions;


    public SessionContext() {
        sessions = new HashMap<>();
    }

    public void storeSession(HttpSession session) {
        sessions.put(session.getId(), session);
    }

    public HttpSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }
}

