CREATE DATABASE hotel;
CREATE USER hotel WITH PASSWORD 'hotel';
GRANT ALL PRIVILEGES ON DATABASE hotel TO hotel;

\connect hotel

CREATE TABLE public.hotel (
	hotel_id int PRIMARY KEY,
	address VARCHAR ( 50 )  NOT NULL,
	id VARCHAR ( 50 ) NOT NULL
);

insert into public.hotel values(10001,'my address','1');

commit;

ALTER TABLE public.hotel
    OWNER to hotel;
