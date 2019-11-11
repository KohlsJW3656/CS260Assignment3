-- ResourcesScript - script for starter database for humanitarian resources

-- drop tables
DROP TABLE MedicalCenter;
DROP TABLE Water;
DROP TABLE Food;
DROP TABLE HumResource;

-- create tables
-- HumResource
CREATE TABLE HumResource
(
   HRID                 INTEGER,
   HRName               VARCHAR(50),
   HRAddressString      VARCHAR(100),
   HRPhoneNumber        CHAR(12),
   HRLatitude           NUMBER(*,7),
   HRLongitude          NUMBER(*,7),
   HRType               VARCHAR(20),
   HRDesc               VARCHAR(200),
   HROpenHoursString    VARCHAR(200),
   PRIMARY KEY (HRID)
);

-- MedicalCenter
CREATE TABLE MedicalCenter
(
   HRID                     INTEGER,
   NumBeds                  INTEGER,
   EmergencyRoomCapacity    INTEGER,
   NumDoctors               INTEGER,
   NumNurses                INTEGER,
   PRIMARY KEY (HRID),
   FOREIGN KEY (HRID) REFERENCES HumResource(HRID)
);

-- Water
CREATE TABLE Water
(
   HRID                            INTEGER,
   Num10OzBottlesAvailable         INTEGER,
   NumHalfLiterBottlesAvailable    INTEGER,
   Num5GallonJugsAvailable         INTEGER,
   PRIMARY KEY (HRID),
   FOREIGN KEY (HRID) REFERENCES HumResource(HRID)
);

-- Food
CREATE TABLE Food
(
   HRID                     INTEGER,
   FType                    VARCHAR(20),
   FMealsAvailable          INTEGER,
   FSpecificDesc            VARCHAR(200),
   PRIMARY KEY (HRID),
   FOREIGN KEY (HRID) REFERENCES HumResource(HRID)
);


-- Insert Rows
--  (some data estimated for all tables)

-- HumResource (HRID, HRName, HRAddressString, HRPhoneNumber, HRLatitude, 
--              HRLongitude, HRType, HRDesc, HROpenHoursString)
INSERT INTO HumResource VALUES (1000, 'Mayo Clinic Health System - Luther Campus', 
            '1221 Whipple St., Eau Claire, WI 54703', '715.838.3311', 
            44.812994, -91.512309, 'MedicalCenter', 'Mayo Hospital and Clinic', 
            'Open all days/hours');

INSERT INTO HumResource VALUES (1001, 'HSHS Sacred Heart Hospital', 
            '900 W. Clairemont Ave., Eau Claire, WI 54701', '715.717.4121', 
            44.794208, -91.511707, 'MedicalCenter', 'Sacred Heart Hospital', 
            'Open all days/hours');            

INSERT INTO HumResource VALUES (1002, 'Sterling Water Culligan', 
            '1928 Truax Blvd., Eau Claire, WI 54703', '715.834.9431', 
            44.836355, -91.535865, 'Water', 'Bottled Water Supply', 
            'M-F 7:30AM-5PM');  
            
INSERT INTO HumResource VALUES (1003, 'Indianhead Foodservice Distributor', 
            '303 N. Hastings Pl., Eau Claire, WI 54703', '715.834.2727', 
            44.818080, -91.472281, 'Food', 'Wholesale Food Distributor', 
            'M-F 8AM-6PM, Sa 8AM-2PM');  
            
INSERT INTO HumResource VALUES (1004, 'Marshfield Clinic and Hospital', 
            '2116 Craig Rd., Eau Claire, WI 54701', '715.858.8100', 
            44.7956525,-91.5221142, 'MedicalCenter', 'Marshfield Clinic - Main Clinic/Hospital', 
            'Open all days/hours');
            
INSERT INTO HumResource VALUES (1005, 'Marshfield Clinic - Riverview Center', 
            '1000 Starr Ave., Eau Claire, WI 54703', '715.858.4850', 
            44.826130, -91.478861, 'MedicalCenter', 'Marshfield Clinic for Mental Health Services', 
            'M,W,Th,F 8AM-5PM, Th 10AM-7PM');            

INSERT INTO HumResource VALUES (1006, 'Kinetco Water Systems', 
            '223 N. Michigan St., Eau Claire, WI 54703', '715.598.4631', 
            44.800851, -91.532163, 'Water', 'Water Supplies and Equipment', 
            'M-F 8AM-5PM');  

INSERT INTO HumResource VALUES (1007, 'Acoustic Cafe', 
            '505 S. Barstow St., Eau Claire, WI 54701', '715.598.4631', 
            44.809271, -91.497825, 'Food', 'Water Supplies and Equipment', 
            'M-Th 7:30AM-10PM, F 7:30AM-11PM, Sa 9:30AM-11PM, Su 9:30AM-10PM');  
            
INSERT INTO HumResource VALUES (1008, 'Just Local Food',
            '1117 S. Farwell St., Eau Claire, WI 54701', '715.552.3366', 
            44.804753, -91.492014, 'Food', 'Cooperative Grocery and Deli', 
            'M-F 7:30AM-9PM, Sa/Su 8AM-9PM');   

-- MedicalCenter (HRID, NumBeds, EmergencyRoomCapacity, NumDoctors, NumNurses)
INSERT INTO MedicalCenter VALUES (1000, 394, 20, 325, 900); 
INSERT INTO MedicalCenter VALUES (1001, 390, 20, 275, 840); 
INSERT INTO MedicalCenter VALUES (1004,  75, 15,  50, 175);
INSERT INTO MedicalCenter VALUES (1005,   0,  0,  12,  50);

-- Water
INSERT INTO Water VALUES (1002, 24500, 6000, 2500);
INSERT INTO Water VALUES (1006, 18000, 5000, 1900);

-- Food
INSERT INTO Food VALUES (1003, 'Distributor', 42500, 'frozen, packaged and fresh food');
INSERT INTO Food VALUES (1007, 'Restaurant', 750, 'fresh coffee, sandwiches, bakery, etc.');
INSERT INTO Food VALUES (1008, 'Grocery', 2500, 'local and organic packaged, fresh, frozen, produce');