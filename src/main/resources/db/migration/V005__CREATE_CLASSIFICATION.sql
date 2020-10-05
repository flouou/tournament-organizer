CREATE TABLE classification
(
    id          BIGINT       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    PRIMARY KEY (id)
) ENGINE = InnoDB
