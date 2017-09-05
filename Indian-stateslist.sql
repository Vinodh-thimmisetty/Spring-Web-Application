# Create Table Script
SET DEFINE OFF;
CREATE TABLE VINODH.INDIAN_STATES (
  STATE_ID NUMBER,
  STATE_NAME VARCHAR2(50) NOT NULL,
  CONSTRAINT PK_INDIAN_STATES_LIST PRIMARY KEY (STATE_ID)
);

#Sample Data

Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (1, 'ANDAMAN AND NICOBAR ISLANDS');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (2, 'ANDHRA PRADESH');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (3, 'ARUNACHAL PRADESH');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (4, 'ASSAM');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (5, 'BIHAR');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (6, 'CHATTISGARH');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (7, 'CHANDIGARH');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (8, 'DAMAN AND DIU');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (9, 'DELHI');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (10, 'DADRA AND NAGAR HAVELI');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (11, 'GOA');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (12, 'GUJARAT');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (13, 'HIMACHAL PRADESH');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (14, 'HARYANA');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (15, 'JAMMU AND KASHMIR');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (16, 'JHARKHAND');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (17, 'KERALA');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (18, 'KARNATAKA');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (19, 'LAKSHADWEEP');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (20, 'MEGHALAYA');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (21, 'MAHARASHTRA');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (22, 'MANIPUR');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (23, 'MADHYA PRADESH');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (24, 'MIZORAM');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (25, 'NAGALAND');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (26, 'ORISSA');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (27, 'PUNJAB');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (28, 'PONDICHERRY');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (29, 'RAJASTHAN');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (30, 'SIKKIM');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (31, 'TAMIL NADU');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (32, 'TRIPURA');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (33, 'UTTARAKHAND');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (34, 'UTTAR PRADESH');
Insert into VINODH.INDIAN_STATES
   (STATE_ID, STATE_NAME)
 Values
   (35, 'WEST BENGAL');
COMMIT;
