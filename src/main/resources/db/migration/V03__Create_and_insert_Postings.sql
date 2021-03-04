CREATE TABLE posting (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payment_date DATE,
	amount DECIMAL(10,2) NOT NULL,
	note VARCHAR(100),
	type VARCHAR(20) NOT NULL,
	id_category BIGINT(20) NOT NULL,
	id_person BIGINT(20) NOT NULL,
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Top Club', '2017-06-10', null, 120, null, 'RECEITA', 3, 1);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 1);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('DMAE', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 2);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA', 4, 2);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Bahamas', '2017-06-10', null, 500, null, 'RECEITA', 1, 1);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 1);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 2);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA', 5, 1);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Café', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 2);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 2);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 1);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 1);
INSERT INTO posting (description, due_date, payment_date, amount, note, type, id_category, id_person) values ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);