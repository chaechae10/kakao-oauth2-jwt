package com.kakaoLogin.oauth_jwt_auth.home.controller;

import com.kakaoLogin.oauth_jwt_auth.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/api/home")
    public ResponseEntity<?> home(
            @RequestParam(required = false) Double x,
            @RequestParam(required = false) Double y) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) authentication.getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        return switch (role) {
            case "ROLE_OWNER" -> {
                if (x == null || y == null) {
                    yield ResponseEntity.badRequest().body("OWNER는 x(경도), y(위도) 좌표가 필요합니다.");
                }
                yield ResponseEntity.ok(homeService.getOwnerHome(userId, x, y));
            }
            case "ROLE_DIRECTOR" -> ResponseEntity.ok(homeService.getDirectorHome(userId));
            default -> ResponseEntity.status(403).build();
        };
    }
}
