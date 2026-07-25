CREATE TABLE company (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    industry VARCHAR(255) NOT NULL,
    updated TIMESTAMP NOT NULL,
    created TIMESTAMP NOT NULL
);

CREATE TABLE storage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    companyId INT NOT NULL,
    storageType VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    constructionYear INT NOT NULL,
    capacity DOUBLE NOT NULL,
    capacityUnit VARCHAR(255),
    lastCheck TIMESTAMP NOT NULL,
    checkIntervall TIMESTAMP NOT NULL,
    updated TIMESTAMP NOT NULL,
    created TIMESTAMP NOT NULL,

    CONSTRAINT fk_storage_company FOREIGN KEY (companyId) REFERENCES company(id)
);

CREATE TABLE content (
    id INT AUTO_INCREMENT PRIMARY KEY,
    storageId INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    hazard VARCHAR(255),
    amount DOUBLE NOT NULL,
    amountUnit VARCHAR(255) NOT NULL,
    expirationDate TIMESTAMP NOT NULL,

    CONSTRAINT fk_content_storage FOREIGN KEY (storageId) REFERENCES storage(id)

)