CREATE SCHEMA `sa` ;

DROP TABLE IF EXISTS `information`;
CREATE TABLE information
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    soil_moisture    DOUBLE                NULL,
    soil_temperature DOUBLE                NULL,
    ph               DOUBLE                NULL,
    air_temperature  DOUBLE                NULL,
    air_humidity     DOUBLE                NULL,
    light_intensity  DOUBLE                NULL,
    create_time             datetime              NULL,
    CONSTRAINT pk_information PRIMARY KEY (id),
    INDEX timeidx (create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `goods_activity_onsale` WRITE;

UNLOCK TABLES;
