drop table Booking cascade constraint;
drop table User cascade constraint;
drop table Fare cascade constraint;

drop sequence hibernate_sequence;
create sequence hibernate_sequence START with 10 INCREMENT BY 1;




create table Booking(
	booking_id number(2) primary key,
	user_mobile number(10),
	source varchar2(15),
	destination varchar2(10),
	fare number(9,2),
	travel_date DATE,
	status CHAR NOT NULL
);


create table Fare(
	fare_id number(5) primary key,
	source varchar2(15),
	destination varchar2(15),
	fare number(5)
);

insert into Fare values(1,'San Jose','Los Angles',340);
insert into Fare values(2,'San Francisco','San Jose',48);
insert into Fare values(3,'Los Angles','San Diego',120);
insert into Fare values(4,'Pheonix','Tucson',114);

insert into Users values(9998766756,'Andy','and@123','andy@hotmail.com',null,'C');
insert into Users values(9898766756,'Ron','ron@123','ron@hotmail.com',null,'C');
insert into Users values(9898770751,'Chan','chan@123','chan@hotmail.com',null,'C');
insert into Users values(9877766756,'Winny','win@123','winny@hotmail.com',null,'C');
insert into Users values(9898006896,'Edd','ed@123','eddy@hotmail.com',null,'C');
insert into Users values(8898766766,'Sam','sam@123','sam@hotmail.com',null,'C');


insert into Booking values(1,9877766756,'San Jose','Los Angles',340,sysdate+2,'B');
insert into Booking values(2,8898766766,'San Francisco','San Jose',48,sysdate+4,'B');


commit;


select * from Fare;
select * from Booking;

