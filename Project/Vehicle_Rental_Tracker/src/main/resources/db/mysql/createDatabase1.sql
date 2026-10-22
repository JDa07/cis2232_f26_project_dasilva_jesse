#For localhost
DROP DATABASE IF EXISTS vehicle_rental_tracker;
CREATE DATABASE vehicle_rental_tracker;
use vehicle_rental_tracker;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with your project.  Expect one
-- table with 7-9 fields.
-- ------------------------------------------------------------------------------

CREATE TABLE VehicleRentalTrackerData
(
    saleID         int(5),
    rentDate       varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    custFName      varchar(50) NOT NULL COMMENT 'Customer first name',
    custLName      varchar(50) NOT NULL COMMENT 'Customer last name',
    custAddress    varchar(50) NOT NULL COMMENT 'Customer address',
    vehicleColour  varchar(50) NOT NULL COMMENT 'Vehicle colour',
    vehicleType    varchar(50) NOT NULL COMMENT 'Vehicle type',
    rentalTimeDays int COMMENT 'Vehicle rental length in days',
    saleTotal      double COMMENT 'Sale total'

) COMMENT 'This table holds vehicle rental details';

ALTER TABLE VehicleRentalTrackerData
    ADD PRIMARY KEY (saleID);
ALTER TABLE VehicleRentalTrackerData
    MODIFY saleID int NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;

CREATE TABLE CodeType
(
    codeTypeId         int(3) COMMENT 'This is the primary key for code types',
    englishDescription varchar(100) NOT NULL COMMENT 'English description',
    frenchDescription  varchar(100) DEFAULT NULL COMMENT 'French description',
    createdDateTime    datetime     DEFAULT NULL,
    createdUserId      varchar(20)  DEFAULT NULL,
    updatedDateTime    datetime     DEFAULT NULL,
    updatedUserId      varchar(20)  DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

ALTER TABLE CodeType
    ADD PRIMARY KEY (CodeTypeId);

INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (2, 'Vehicle Rental Types', 'Vehicle Rental Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');



CREATE TABLE CodeValue
(
    codeTypeId              int(3)       NOT NULL COMMENT 'see code_type table',
    codeValueSequence       int(3)       NOT NULL,
    englishDescription      varchar(100) NOT NULL COMMENT 'English description',
    englishDescriptionShort varchar(20)  NOT NULL COMMENT 'English abbreviation for description',
    frenchDescription       varchar(100) DEFAULT NULL COMMENT 'French description',
    frenchDescriptionShort  varchar(20)  DEFAULT NULL COMMENT 'French abbreviation for description',
    sortOrder               int(3)       DEFAULT NULL COMMENT 'Sort order if applicable',
    createdDateTime         datetime     DEFAULT NULL,
    createdUserId           varchar(20)  DEFAULT NULL,
    updatedDateTime         datetime     DEFAULT NULL,
    updatedUserId           varchar(20)  DEFAULT NULL
) COMMENT ='This will hold code values for the application.';

ALTER TABLE CodeValue
    ADD PRIMARY KEY (CodeTypeId, codeValueSequence);
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 1, 'Car', 'Car', 'Voiture', 'Voiture', NOW(), 'admin', NOW(), 'admin'),
       (2, 2, 'Truck', 'Truck', 'Camion', 'Camion', NOW(), 'admin', NOW(), 'admin'),
       (2, 3, 'Electric', 'Electric', 'Électrique', 'Électrique', NOW(), 'admin', NOW(), 'admin'),
       (2, 4, 'Van', 'Van', 'Fourgonnette', 'Fourgonnette', NOW(), 'admin', NOW(), 'admin');


