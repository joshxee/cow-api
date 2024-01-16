ALTER TABLE cow
    DROP CONSTRAINT IF EXISTS cow_number_unique,
    DROP CONSTRAINT IF EXISTS cow_name_unique;

ALTER TABLE cow
    ADD CONSTRAINT cow_number_unique UNIQUE (number),
    ADD CONSTRAINT cow_collar_id_unique UNIQUE (collar_id);
