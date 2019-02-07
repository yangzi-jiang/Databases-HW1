#CREATE SCHEMA SenatorVotes;

USE SenatorVotes;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Senator;
DROP TABLE IF EXISTS Vote;
DROP TABLE IF EXISTS VoteCast;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE Senator
	(member_id			VARCHAR(4),
	 first_name			VARCHAR(15),
	 last_name			VARCHAR(15),
	 party				VARCHAR(1), #R-51, D-47, I-2
	 state				VARCHAR(2),
	 PRIMARY KEY (member_id)
	);
	
CREATE TABLE Vote
	(vote_number		NUMERIC(3,0),
	 congress_number	NUMERIC(3,0),
	 session			NUMERIC(1,0),
	 congress_year		NUMERIC(4,0),
	 vote_date			DATE, #DATE_FORMAT(date, "%M %e, %Y  %r")?
	 PRIMARY KEY (vote_number)
	);
	
CREATE TABLE VoteCast
	(senator_ID			VARCHAR(4),
	 vote_number		NUMERIC(3,0),
	 vote_option		VARCHAR(1),
	 PRIMARY KEY (senator_ID),
	 FOREIGN KEY (senator_ID) REFERENCES Senator (member_id),
	 FOREIGN KEY (vote_number) REFERENCES Vote (vote_number)
	);