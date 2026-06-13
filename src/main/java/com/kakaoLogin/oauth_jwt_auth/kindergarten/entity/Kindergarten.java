package com.kakaoLogin.oauth_jwt_auth.kindergarten.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kindergartens")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Kindergarten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "director_id", nullable = false)
    private Long directorId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String address;

    @Column
    private Double latitude;

    @Column
    private Double longitude;
}
