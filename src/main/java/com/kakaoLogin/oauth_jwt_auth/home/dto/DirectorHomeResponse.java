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
public class DirectorHomeResponse {

    private String role;
    private String kindergartenName;
    private List<TodayDogInfo> todayDogs;
    private int todayAttendance;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TodayDogInfo {
        private Long id;
        private String name;
        private String breed;
        private String ownerName;
    }
}
