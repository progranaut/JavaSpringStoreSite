package com.ivlev.javaSpringStoreSite.repository;

import com.ivlev.javaSpringStoreSite.entity.Auth;
import com.ivlev.javaSpringStoreSite.feign.FeignImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AuthRepository {

    private HashMap<String, Auth> authHashMap = new HashMap<String, Auth>();

    public Auth addAuth(String sessionId, Auth auth) {
        authHashMap.put(sessionId, auth);
        return getAuth(sessionId);
    }

    public void delAuth(String sessionId) {
        authHashMap.remove(sessionId);
    }

    public boolean containAuth(String sessionId) {
        return authHashMap.containsKey(sessionId);
    }

    public Auth getAuth(String sessionId) {
        return authHashMap.get(sessionId);
    }

}
