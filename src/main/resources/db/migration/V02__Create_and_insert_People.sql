CREATE TABLE person (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	active TINYINT(1) NOT NULL DEFAULT 1,
	street VARCHAR(50) NOT NULL,
	number VARCHAR(50) NOT NULL,
	complement VARCHAR(10) NOT NULL,
	zip_code VARCHAR(10) NOT NULL,
	city VARCHAR(20) NOT NULL,
	state VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (name, active, street, number, complement, zip_code, city, state) VALUES ("Dilthey Aislan", 1, "Rua 10", "S/N", "Centro", "90000-123", "Brasília", "DF");