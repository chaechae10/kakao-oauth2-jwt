package com.kakaoLogin.oauth_jwt_auth.oauth.dto;

import java.util.Map;

public class KakaoUserInfo {

    private final Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    public String getNickname() {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            if (kakaoAccount == null) return "Unknown";

            @SuppressWarnings("unchecked")
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            if (profile == null) return "Unknown";

            Object nickname = profile.get("nickname");
            return nickname != null ? (String) nickname : "Unknown";
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public String getEmail() {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            if (kakaoAccount == null) return null;

            Object email = kakaoAccount.get("email");
            return email != null ? (String) email : null;
        } catch (Exception e) {
            return null;
        }
    }
}
