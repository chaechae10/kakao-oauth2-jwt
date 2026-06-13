package com.kakaoLogin.oauth_jwt_auth.home.service;

import com.kakaoLogin.oauth_jwt_auth.attendance.entity.Attendance;
import com.kakaoLogin.oauth_jwt_auth.attendance.repository.AttendanceRepository;
import com.kakaoLogin.oauth_jwt_auth.dog.entity.Dog;
import com.kakaoLogin.oauth_jwt_auth.dog.repository.DogRepository;
import com.kakaoLogin.oauth_jwt_auth.home.dto.DirectorHomeResponse;
import com.kakaoLogin.oauth_jwt_auth.home.dto.DirectorHomeResponse.TodayDogInfo;
import com.kakaoLogin.oauth_jwt_auth.home.dto.OwnerHomeResponse;
import com.kakaoLogin.oauth_jwt_auth.home.dto.OwnerHomeResponse.DogInfo;
import com.kakaoLogin.oauth_jwt_auth.home.dto.OwnerHomeResponse.KindergartenInfo;
import com.kakaoLogin.oauth_jwt_auth.home.util.LocationBoundingBox;
import com.kakaoLogin.oauth_jwt_auth.kindergarten.entity.Kindergarten;
import com.kakaoLogin.oauth_jwt_auth.kindergarten.repository.KindergartenRepository;
import com.kakaoLogin.oauth_jwt_auth.user.entity.User;
import com.kakaoLogin.oauth_jwt_auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private static final double SEARCH_RADIUS_KM = 2.0;

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final KindergartenRepository kindergartenRepository;
    private final AttendanceRepository attendanceRepository;

    public OwnerHomeResponse getOwnerHome(Long userId, double x, double y) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        List<Dog> dogs = dogRepository.findAllByOwnerId(userId);

        List<DogInfo> dogInfos = dogs.stream()
                .map(d -> DogInfo.builder()
                        .id(d.getId())
                        .name(d.getName())
                        .breed(d.getBreed())
                        .build())
                .toList();

        // x = 경도(longitude), y = 위도(latitude)
        LocationBoundingBox boundingBox = new LocationBoundingBox(y, x, SEARCH_RADIUS_KM);
        List<Kindergarten> kindergartens = kindergartenRepository.findNearby(boundingBox.toPointFormat());

        List<KindergartenInfo> kindergartenInfos = kindergartens.stream()
                .map(k -> {
                    double distanceKm = LocationBoundingBox.distanceKm(y, x, k.getLatitude(), k.getLongitude());
                    return KindergartenInfo.builder()
                            .id(k.getId())
                            .name(k.getName())
                            .distance(String.format("%.1fkm", distanceKm))
                            .build();
                })
                .toList();

        return OwnerHomeResponse.builder()
                .role("OWNER")
                .myDogs(dogInfos)
                .nearbyKindergartens(kindergartenInfos)
                .build();
    }

    public DirectorHomeResponse getDirectorHome(Long userId) {
        Kindergarten kindergarten = kindergartenRepository.findByDirectorId(userId)
                .orElseThrow(() -> new RuntimeException("유치원 없음"));

        List<Attendance> todayAttendances = attendanceRepository
                .findByKindergartenIdAndAttendedDate(kindergarten.getId(), LocalDate.now());

        List<TodayDogInfo> todayDogInfos = todayAttendances.stream()
                .map(a -> {
                    Dog dog = dogRepository.findById(a.getDogId()).orElseThrow();
                    User owner = userRepository.findById(dog.getOwnerId()).orElseThrow();
                    return TodayDogInfo.builder()
                            .id(dog.getId())
                            .name(dog.getName())
                            .breed(dog.getBreed())
                            .ownerName(owner.getNickname())
                            .build();
                })
                .toList();

        return DirectorHomeResponse.builder()
                .role("DIRECTOR")
                .kindergartenName(kindergarten.getName())
                .todayDogs(todayDogInfos)
                .todayAttendance(todayDogInfos.size())
                .build();
    }
}
