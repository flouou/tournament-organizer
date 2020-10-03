CREATE TABLE rating
(
    id             BIGINT NOT NULL,
    value          DOUBLE NOT NULL,
    show_rating_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB
