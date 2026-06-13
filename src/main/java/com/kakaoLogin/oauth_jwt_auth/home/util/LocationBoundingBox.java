package com.kakaoLogin.oauth_jwt_auth.home.util;

public class LocationBoundingBox {

    private static final double EARTH_RADIUS = 6371.0;

    private final double neLat;
    private final double neLng;
    private final double swLat;
    private final double swLng;

    public LocationBoundingBox(double lat, double lng, double radiusKm) {
        double dLat = Math.toDegrees(radiusKm / EARTH_RADIUS);
        double dLng = Math.toDegrees(radiusKm / (EARTH_RADIUS * Math.cos(Math.toRadians(lat))));

        this.neLat = lat + dLat;
        this.neLng = lng + dLng;
        this.swLat = lat - dLat;
        this.swLng = lng - dLng;
    }

    // MBRContains에 전달할 LINESTRING 문자열 반환 (SW → NE)
    public String toPointFormat() {
        return String.format("LINESTRING(%f %f, %f %f)", swLng, swLat, neLng, neLat);
    }

    // 두 좌표 간 실제 거리 계산 (Haversine)
    public static double distanceKm(double lat1, double lng1, double lat2, double lng2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        return EARTH_RADIUS * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}
