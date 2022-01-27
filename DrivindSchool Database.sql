drop database drivingschool1;

create database drivingschool1;

#Create Database
use drivingschool1;

#Create Employee table
create table employee(
	emp_id varchar(9),
	first_name varchar(10),
	last_name varchar(10),
	mobile_num varchar(10),
	address varchar(10),
	primary key (emp_id)
);

create table trainer(
	trainer_id varchar(9) primary key,
    foreign key (trainer_id) references employee(emp_id)
    ON DELETE CASCADE
);

create table secretary(
	secretary_id varchar(9) primary key,
    foreign key (secretary_id) references employee(emp_id)
    ON DELETE CASCADE
);

-- this table was made for license type to be 2NF
CREATE TABLE prices(
	lesson_price real,
    test_price real,
    primary key (lesson_price)
);

#Create license_type table
create table license_type(
	type_name varchar(20),
	lesson_price real,
	primary key (type_name),
    foreign key (lesson_price) references prices(lesson_price)
);


#Create student table
create table Student(
	student_id varchar(9),
    first_name varchar(10),
    last_name varchar(10),
    mobile_num varchar(10),
    eye_test_date varchar(11),
    address varchar(10),
    process_status varchar(20),
    license varchar(20),
    test_taken int default 0,
    emp_id varchar(9),
    primary key(student_id),
    foreign key (emp_id) references trainer (trainer_id),
    foreign key (license) references license_type (type_name)
);

#Create Payments table
create table payments(
	payment_id int NOT NULL AUTO_INCREMENT,
	student_id varchar(9),
	payment_date varchar(11),
	amount real,
	primary key (payment_id),
	foreign key (student_id) references student (student_id) 
);

#Create Vehicle table
create table vehicle(
	vehicle_num varchar(10),
	insurance_end_date varchar(11),
	primary key (vehicle_num)
);

#Create the table that is connected between the student and the vehicle
create table vehicle_student(
	lesson_id int NOT NULL AUTO_INCREMENT,
	student_id varchar(9),
	lesson_date varchar(11),
	vehicle_num varchar(10),
	primary key (lesson_id, student_id),
	foreign key (student_id) references student (student_id),
	foreign key (vehicle_num) references vehicle (vehicle_num)
);

#Insert statements into the table Employee that is used to show the model of the java project
INSERT INTO employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`address`)
VALUES
('400034500','Ahmad','Mustafa','0599111222','Ramallah'),
('412312312','Sameer','Qadee','0599876876','Tulkarem'),
('476879809','Othman','Saleh','0596785462','Jenin');

INSERT INTO prices (`lesson_price`,`test_price`)
VALUES
(90,290),
(110,350),
(160,500);

INSERT INTO license_type (`type_name`,lesson_price)
VALUES
('private',90),
('taxi',90),
('light truck',110),
('bus',160),
('heavy truck',160),
('trella',160);

INSERT INTO trainer(`trainer_id`)
VALUES
('400034500'),('412312312');

INSERT INTO student(`student_id`,`first_name`,`last_name`,`mobile_num`,`eye_test_date`,`address`,`process_status`,`license`,test_taken,`emp_id`)
VALUES
("405867284","Oday","Bilal","0599874859","12-05-2019","Ramallah", "New", "private",1, "412312312"),
("912387644","Fadi","Hamad","0568449332","12-05-2021","Tulkarem","Graduated", "light truck" ,2, "400034500"),
("978563345","Salma","Emad","0568666545","08-07-2019","Ramallah","New","taxi",0,"412312312");

INSERT INTO payments (`payment_id`,`student_id`,`payment_date`,`amount`)
VALUES
(1,'405867284','01-01-2021',200 ),
(2,'912387644','03-01-2018',400 ),
(3,'405867284','11-11-2013',50 ),
(4,'912387644','15-11-2014',20 );


INSERT INTO vehicle(`vehicle_num`,`insurance_end_date`)
VALUES
('2435','10-02-2025'),
('2241','28-05-2026'),
('11132','16-11-2023');

INSERT INTO vehicle_student(`lesson_id`,`student_id`,`lesson_date`,`vehicle_num`)
VALUES
(1,'405867284','01-01-2021','2435'),
(2,'405867284','04-01-2021','2435'),
(3,'912387644','03-03-2021','11132'),
(4,'405867284','05-03-2021','2435'),
(5,'405867284','04-04-2014','2435'),
(6,'405867284','03-03-2019','2435'),
(7,'912387644','01-01-2013','11132');

INSERT INTO secretary(`secretary_id`)
VALUES
('476879809');

