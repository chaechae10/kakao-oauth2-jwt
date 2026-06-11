package com.kakaoLogin.oauth_jwt_auth.oauth;

import com.kakaoLogin.oauth_jwt_auth.oauth.dto.KakaoUserInfo;
import com.kakaoLogin.oauth_jwt_auth.user.entity.Role;
import com.kakaoLogin.oauth_jwt_auth.user.entity.User;
import com.kakaoLogin.oauth_jwt_auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(oAuth2User.getAttributes());

        User user = userRepository.findByKakaoId(kakaoUserInfo.getId())
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .kakaoId(kakaoUserInfo.getId())
                                .nickname(kakaoUserInfo.getNickname())
                                .email(kakaoUserInfo.getEmail())
                                .role(Role.ROLE_OWNER)
                                .build()
                ));

        return CustomUserDetails.builder()
                .userId(user.getId())
                .kakaoId(user.getKakaoId())
                .role(user.getRole())
                .attributes(oAuth2User.getAttributes())
                .build();
    }
}
