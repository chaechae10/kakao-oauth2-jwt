package com.kakaoLogin.oauth_jwt_auth.oauth;

import com.kakaoLogin.oauth_jwt_auth.user.entity.Role;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
@Builder
public class CustomUserDetails implements OAuth2User {

    private final Long userId;
    private final String kakaoId;
    private final Role role;
    private final Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getName() {
        return kakaoId;
    }
}
