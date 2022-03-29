INSERT INTO exchange_rates (type, price) VALUES ('NUEVO SOL', '1');
INSERT INTO exchange_rates (type, price) VALUES ('DOLAR', '3.73');
INSERT INTO exchange_rates (type, price) VALUES ('EURO', '4.11');
INSERT INTO exchange_rates (type, price) VALUES ('LIBRA', '4.91');
INSERT INTO exchange_rates (type, price) VALUES ('PESO MEXICANO', '2.3');

INSERT INTO users (username, password, rol) VALUES ('admin', 'admin', 'ADMIN');
INSERT INTO users (username, password, rol) VALUES ('user', 'user', 'USER');

--DROP TABLE IF EXISTS usuarios;
--
--CREATE TABLE IF NOT EXISTS usuarios (
--    id bigint auto_increment,
--    username varchar(50) NOT NULL,
--    password varchar(50) NOT NULL,
--    rol varchar(50) NOT NULL,
--
--    CHECK (rol in ('ADMIN', 'USER')),
--    PRIMARY KEY (id)
--);