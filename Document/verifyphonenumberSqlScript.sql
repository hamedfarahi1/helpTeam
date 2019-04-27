CREATE TABLE verifyphonenumber
(
	ID int NOT NULL
		PRIMARY KEY ,
	phoneNumber varchar(11) NOT NULL,
	verifyCode int NOT NULL,
	status int NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;