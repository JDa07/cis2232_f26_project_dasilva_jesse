-- For localhost
DROP DATABASE IF EXISTS cis2232_vehicle_rental_tracker;
CREATE DATABASE cis2232_vehicle_rental_tracker;
use cis2232_vehicle_rental_tracker;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with your project.  Expect one
-- table with 7-9 fields.
-- ------------------------------------------------------------------------------

CREATE TABLE vehiclerentaltrackerdata
(
    saleID         int         NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    rentDate       varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    custFName      varchar(50) NOT NULL COMMENT 'Customer first name',
    custLName      varchar(50) NOT NULL COMMENT 'Customer last name',
    custAddress    varchar(50) NOT NULL COMMENT 'Customer address',
    vehicleColour  varchar(50) NOT NULL COMMENT 'Vehicle colour',
    vehicleType    varchar(50) NOT NULL COMMENT 'Vehicle type',
    rentalTimeDays int COMMENT 'Vehicle rental length in days',
    saleTotal double COMMENT 'Sale total',
    PRIMARY KEY (saleID),
    INDEX          idx_customer_name (custFName, custLName) -- speeds up the customer name report
) COMMENT 'This table holds vehicle rental details';

-- ------------------------------------------------------------------------------
-- Sample rentals for testing the reports (totals = daily price x days x 1.15)
-- ------------------------------------------------------------------------------

INSERT INTO vehiclerentaltrackerdata
(rentDate, custFName, custLName, custAddress, vehicleColour, vehicleType, rentalTimeDays, saleTotal)
VALUES ('2026-10-01', 'Jane', 'Smith', '12 Water St', 'Red', 'Car', 3, 120.75),
       ('2026-10-05', 'Jane', 'Smith', '12 Water St', 'Blue', 'Van', 1, 56.35),
       ('2026-10-10', 'John', 'Doe', '45 Queen St', 'Black', 'Truck', 2, 92.00),
       ('2026-10-15', 'Maria', 'Garcia', '78 King St', 'White', 'Electric', 5, 258.75);

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
    codeTypeId              int(3) NOT NULL COMMENT 'see code_type table',
    codeValueSequence       int(3) NOT NULL,
    englishDescription      varchar(100) NOT NULL COMMENT 'English description',
    englishDescriptionShort varchar(20)  NOT NULL COMMENT 'English abbreviation for description',
    frenchDescription       varchar(100) DEFAULT NULL COMMENT 'French description',
    frenchDescriptionShort  varchar(20)  DEFAULT NULL COMMENT 'French abbreviation for description',
    sortOrder               int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
    createdDateTime         datetime     DEFAULT NULL,
    createdUserId           varchar(20)  DEFAULT NULL,
    updatedDateTime         datetime     DEFAULT NULL,
    updatedUserId           varchar(20)  DEFAULT NULL
) COMMENT='This will hold code values for the application.';

ALTER TABLE CodeValue
    ADD PRIMARY KEY (CodeTypeId, codeValueSequence);

-- User types
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37',
        'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');

-- Vehicle rental types (match the types priced in VehicleRentalTrackerBO)
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 1, 'Car', 'Car', 'CarFR', 'CarFR', '2026-10-15 18:44:37', 'admin', '2026-10-15 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 2, 'Truck', 'Truck', 'TruckFR', 'TruckFR', '2026-10-15 18:44:37', 'admin', '2026-10-15 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 3, 'Electric', 'Electric', 'ElectricFR', 'ElectricFR', '2026-10-15 18:44:37', 'admin', '2026-10-15 18:44:37',
        'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 4, 'Van', 'Van', 'VanFR', 'VanFR', '2026-10-15 18:44:37', 'admin', '2026-10-15 18:44:37', 'admin');