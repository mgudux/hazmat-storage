CREATE TABLE company (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    industry VARCHAR(255) NOT NULL,
    updated DATETIME NOT NULL,
    created DATETIME NOT NULL);

CREATE TABLE storage (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    companyId BIGINT NOT NULL,
    storageType VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    constructionYear INT NOT NULL,
    capacity DOUBLE NOT NULL,
    capacityUnit VARCHAR(255) NOT NULL,
    lastCheck TIMESTAMP NOT NULL,
    monthsCheckIntervall INT NOT NULL,
    updated DATETIME NOT NULL,
    created DATETIME NOT NULL,

    CONSTRAINT fk_storage_company FOREIGN KEY (companyId) REFERENCES company(id)
);

CREATE TABLE content (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    storageId BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    hazard VARCHAR(255),
    amount DOUBLE NOT NULL,
    amountUnit VARCHAR(255) NOT NULL,
    expirationDate DATETIME,
    updated DATETIME NOT NULL,
    created DATETIME NOT NULL,

    CONSTRAINT fk_content_storage FOREIGN KEY (storageId) REFERENCES storage(id)

)