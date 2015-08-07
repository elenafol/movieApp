USE ${dbname.mysql};
SHOW WARNINGS;

/*   
DROP TABLE IF EXISTS hibernate_sequence;
CREATE TABLE hibernate_sequence(
	next_val BIGINT NOT NULL PRIMARY KEY
*/

DROP TABLE IF EXISTS movie;
CREATE TABLE movie(
	mId BIGINT NOT NULL AUTO_INCREMENT,
	description VARCHAR(32),
	lastTnc BIGINT NOT NULL REFERENCES transaktion(tId),
	timestamp TIMESTAMP NOT NULL,
	title VARCHAR(32),
	urlBild VARCHAR(32),
	PRIMARY KEY (mId)
);


DROP TABLE IF EXISTS seat;
CREATE TABLE seat(
	sId BIGINT NOT NULL AUTO_INCREMENT,
	/*reserved BOOLEAN NOT NULL,*/
	PRIMARY KEY (sId),
	movie_fk BIGINT NOT NULL REFERENCES movie(mId),
	seatRes_fk BIGINT NOT NULL REFERENCES seatReservation(srId),
	lastTnc BIGINT NOT NULL REFERENCES transaktion(tId)

);



DROP TABLE IF EXISTS snack;
CREATE TABLE snack(
	snId BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	lastTnc BIGINT NOT NULL REFERENCES transaktion(tId),
	snackRes_fk BIGINT REFERENCES snackReservation(snrId),
	PRIMARY KEY(snId)

);


DROP TABLE IF EXISTS seatReservation;
CREATE TABLE seatReservation(
	srId BIGINT NOT NULL UNIQUE,
	lastTnc BIGINT NOT NULL REFERENCES transaktion(tId),
	PRIMARY KEY(srId)
);

DROP TABLE IF EXISTS snackReservation;
CREATE TABLE snackReservation(
	snrId BIGINT NOT NULL UNIQUE,
	/*snack VARCHAR(32) NOT NULL,*/
	seatRes_fk BIGINT NOT NULL REFERENCES seatReservation(srId),
	lastTnc BIGINT NOT NULL REFERENCES transaktion(tId),
	PRIMARY KEY(snrId)
);



DROP TABLE IF EXISTS client;
CREATE TABLE client(
	cId BIGINT NOT NULL,
	udI BIGINT NOT NULL UNIQUE,
	PRIMARY KEY (cId)
);


DROP TABLE IF EXISTS transaction;
CREATE TABLE transaction(
	tId BIGINT NOT NULL AUTO_INCREMENT,
	client_fk BIGINT NOT NULL REFERENCES client(cId),
	PRIMARY KEY (tId)
	
	
);
