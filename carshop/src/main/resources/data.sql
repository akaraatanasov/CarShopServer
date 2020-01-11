-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO car_make (title)
VALUES
('BMW'),
('Tesla'),
('Mercedes'),
('Audi'),
('VW'),
('Renault'),
('Opel'),
('Skoda'),
('Peugeot'),
('Fiat');

INSERT INTO program (name)
VALUES
('Informatics'),
('Information Technologies'),
('Game Development'),
('Network Technologies');

INSERT INTO course (name, credits, grades, program)
VALUES
('CITB415',3,5,1),
('CITB305',3,4,1),
('CSCB869',3,5,2),
('CSCB509',6,5,1),
('CSCB209',3,6,2),
('CSCB211',3,3,1),
('CSCB035',6,3,3),
('CSCB025',15,2,3),
('CSCB024',15,4,4);
