package com.kakaoLogin.oauth_jwt_auth.attendance.repository;

import com.kakaoLogin.oauth_jwt_auth.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByKindergartenIdAndAttendedDate(Long kindergartenId, LocalDate attendedDate);
}
