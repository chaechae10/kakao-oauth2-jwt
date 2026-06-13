package com.kakaoLogin.oauth_jwt_auth.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerHomeResponse {

    private String role;
    private List<DogInfo> myDogs;
    private List<KindergartenInfo> nearbyKindergartens;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DogInfo {
        private Long id;
        private String name;
        private String breed;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KindergartenInfo {
        private Long id;
        private String name;
        private String distance;
    }
}
