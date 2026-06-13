ALTER TABLE kindergartens
    ADD COLUMN latitude  DOUBLE,
    ADD COLUMN longitude DOUBLE,
    ADD COLUMN point     POINT SRID 0;

UPDATE kindergartens SET
    latitude  = 37.5068,
    longitude = 127.0536,
    point     = ST_PointFromText('POINT(127.0536 37.5068)', 0)
WHERE id = 1;

UPDATE kindergartens SET
    latitude  = 37.5038,
    longitude = 127.0000,
    point     = ST_PointFromText('POINT(127.0000 37.5038)', 0)
WHERE id = 2;

UPDATE kindergartens SET
    latitude  = 37.5145,
    longitude = 127.1059,
    point     = ST_PointFromText('POINT(127.1059 37.5145)', 0)
WHERE id = 3;

UPDATE kindergartens SET
    latitude  = 37.5570,
    longitude = 126.9240,
    point     = ST_PointFromText('POINT(126.9240 37.5570)', 0)
WHERE id = 4;

UPDATE kindergartens SET
    latitude  = 37.5349,
    longitude = 126.9938,
    point     = ST_PointFromText('POINT(126.9938 37.5349)', 0)
WHERE id = 5;
