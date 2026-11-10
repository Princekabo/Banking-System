INSERT INTO customers VALUES ('C001','John','Doe','Gaborone','pass1');
INSERT INTO customers VALUES ('C002','Jane','Smith','Francistown','pass2');
INSERT INTO customers VALUES ('C003','Kago','Molefe','Maun','pass3');

INSERT INTO accounts VALUES ('ACC001','C001','Savings',1500,'Gaborone',NULL,NULL);
INSERT INTO accounts VALUES ('ACC002','C001','Investment',2000,'Gaborone',NULL,NULL);
INSERT INTO accounts VALUES ('ACC003','C002','Savings',800,'Francistown',NULL,NULL);
INSERT INTO accounts VALUES ('ACC004','C002','Cheque',4500,'Francistown','Acme Ltd','Plot 5');
INSERT INTO accounts VALUES ('ACC005','C003','Investment',750,'Maun',NULL,NULL);
INSERT INTO accounts VALUES ('ACC006','C003','Savings',300,'Maun',NULL,NULL);
INSERT INTO accounts VALUES ('ACC007','C001','Cheque',6000,'Gaborone','TechCorp','Plot 10');