package com.ivlev.javaSpringStoreSite.service;

import com.ivlev.javaSpringStoreSite.entity.Auth;
import com.ivlev.javaSpringStoreSite.feign.FeignAuthImpl;
import com.ivlev.javaSpringStoreSite.model.SignInRequest;
import com.ivlev.javaSpringStoreSite.model.SignInAuthResponse;
import com.ivlev.javaSpringStoreSite.model.RefreshAuthRequest;
import com.ivlev.javaSpringStoreSite.model.RefreshAuthResponse;
import com.ivlev.javaSpringStoreSite.repository.AuthRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    private final FeignAuthImpl feign;

    public ResponseEntity<?> signInUser(SignInRequest signInRequest, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<SignInAuthResponse> signInResp = feign.signInUser(signInRequest);

        if (!signInResp.getStatusCode().is2xxSuccessful()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!signInResp.hasBody()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SignInAuthResponse signInAuthResponse = signInResp.getBody();

        addAuth(request.getSession().getId(), signInAuthResponse);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    public void addAuth(String sessionId, SignInAuthResponse signInAuthResponse) {
        authRepository.addAuth(sessionId, castAuth(signInAuthResponse));
    }

    public void delAuth(String sessionId) {
        authRepository.delAuth(sessionId);
    }

    public boolean containAuth(String sessionId) {
        return authRepository.containAuth(sessionId);
    }

    public Auth getAuth(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        return authRepository.getAuth(sessionId);
    }

    public Auth refreshAuth(String sessionId) {
        Auth auth = authRepository.getAuth(sessionId);
        ResponseEntity<RefreshAuthResponse> response = feign.refreshAuth(RefreshAuthRequest.builder()
                        .refreshToken(auth.getRefreshToken())
                .build());

        if (response.getStatusCode().value() != 200) {
            return null;
        }

        if (!response.hasBody()) {
            return null;
        }

        RefreshAuthResponse authResponse = response.getBody();

        auth.setRefreshToken(authResponse.getRefreshToken());
        auth.setToken(authResponse.getAccessToken());
        auth.setTime(LocalDateTime.now());

        return authRepository.addAuth(sessionId, auth);

    }

    public String getToken(HttpServletRequest request) {

        String sessionId = request.getSession().getId();

        Auth auth = getAuth(request);

        if (auth.getTime().plusMinutes(9).isAfter(LocalDateTime.now())) {
            return auth.getToken();
        }

        if (auth.getTime().plusMinutes(29).isAfter(LocalDateTime.now())) {
            return refreshAuth(sessionId).getToken();
        }

        logout(request);
        delAuth(sessionId);

        return null;

    }

    public Auth castAuth(SignInAuthResponse signInAuthResponse) {
        return Auth.builder()
                .id(signInAuthResponse.getId())
                .token(signInAuthResponse.getToken())
                .refreshToken(signInAuthResponse.getRefreshToken())
                .userName(signInAuthResponse.getUsername())
                .roles(signInAuthResponse.getRoles())
                .time(LocalDateTime.now())
                .build();
    }

    public void logout(HttpServletRequest request) {

        String sessionId = request.getSession().getId();

        String token = authRepository.getAuth(sessionId).getToken();

        ResponseEntity<?> response = feign.logout("Bearer " + token);
        if (response.getStatusCode().is2xxSuccessful()) {
            delAuth(sessionId);
        }

    }

    public boolean isAdmin(HttpServletRequest request) {

        Auth auth = getAuth(request);
        if (auth.getRoles().contains("ROLE_ADMIN")) {
            return true;
        }
        return false;

    }
}
