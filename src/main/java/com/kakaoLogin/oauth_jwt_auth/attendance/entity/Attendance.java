package com.kakaoLogin.oauth_jwt_auth.attendance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dog_id", nullable = false)
    private Long dogId;

    @Column(name = "kindergarten_id", nullable = false)
    private Long kindergartenId;

    @Column(name = "attended_date", nullable = false)
    private LocalDate attendedDate;
}
