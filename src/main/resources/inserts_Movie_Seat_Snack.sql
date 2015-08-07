
//Movie
INSERT INTO movie (mId, title, description, timestamp, lastTnc, urlBild) VALUES (1,'Titanic','','01.07.2015 13:00:00',1,'');
INSERT INTO movie (mId, title, description, timestamp, lastTnc, urlBild) VALUES (2,'Interstellar','','02.07.2015 12:00:00',2,'');
INSERT INTO movie (mId, title, description, timestamp, lastTnc, urlBild) VALUES (3,'Harry Potter','','02.07.2015 17:00:00',2,'');

//Seat
INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (1,1,5,1);
INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (2,1,5,1);
INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (3,1,5,1);

INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (4,2,6,2);
INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (5,2,6,2);
INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (6,2,6,2);

INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (7,3,7,3);
INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (8,3,7,3);
INSERT INTO seat (sId, movie_fk, lastTnc, seatRes_fk) VALUES (9,3,7,3);

//Snack
INSERT INTO seat (snId, reservation_fk, name, lastTnc, snackRes_fk) VALUES (1,,'Drink','Sprite',5,1);
INSERT INTO seat (snId, reservation_fk, name, lastTnc,snackRes_fk) VALUES (2,,'Ice','Chocolate',5,1);
INSERT INTO seat (snId, reservation_fk, name, lastTnc, snackRes_fk) VALUES (3,,'Popcorn','Sweet',6,1);
snrId BIGINT NOT NULL UNIQUE,

//SnackReservation
INSERT INTO snackreservation (snrId, seatRes_fk, lastTnc) VALUES (1,1,5);
INSERT INTO snackreservation (snrId, seatRes_fk, lastTnc) VALUES (1,1,4);
INSERT INTO snackreservation (snrId, seatRes_fk, lastTnc) VALUES (1,2,6);

//SeatReservation
INSERT INTO seatreservation (srId, lastTnc) VALUES (1,5);
INSERT INTO seatreservation (srId, lastTnc) VALUES (2,6);
INSERT INTO seatreservation (srId, lastTnc) VALUES (3,7);