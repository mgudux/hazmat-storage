CREATE TABLE company (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    industry VARCHAR(255) NOT NULL,
    updated TIMESTAMP NOT NULL,
    created TIMESTAMP NOT NULL);

CREATE TABLE storage (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_id BIGINT NOT NULL,
    storage_type VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    construction_year INT NOT NULL,
    capacity DOUBLE NOT NULL,
    capacity_unit VARCHAR(255) NOT NULL,
    lastCheck TIMESTAMP NOT NULL,
    months_check_intervall INT NOT NULL,
    updated TIMESTAMP NOT NULL,
    created TIMESTAMP NOT NULL,

    CONSTRAINT fk_storage_company FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE content (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    storage_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    hazard VARCHAR(255),
    amount DOUBLE NOT NULL,
    amount_unit VARCHAR(255) NOT NULL,
    expiration_date TIMESTAMP,
    updated TIMESTAMP NOT NULL,
    created TIMESTAMP NOT NULL,

    CONSTRAINT fk_content_storage FOREIGN KEY (storage_id) REFERENCES storage(id)

)