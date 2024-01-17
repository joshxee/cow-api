DROP TABLE IF EXISTS cow;

CREATE TABLE cow
(
    id        VARCHAR DEFAULT(gen_random_uuid()) PRIMARY KEY NOT NULL,
    number    INTEGER                                                           NOT NULL,
    name      VARCHAR(100),
    collar_id INTEGER                                                           NOT NULL
);