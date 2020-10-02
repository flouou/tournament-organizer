CREATE TABLE club_group
(
    id         BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    age  VARCHAR(255) NOT NULL,
    club_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB
