CREATE TABLE club_group
(
    id                BIGINT       NOT NULL,
    name              VARCHAR(255) NOT NULL,
    time              DATETIME,
    club_id           BIGINT,
    classification_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB
