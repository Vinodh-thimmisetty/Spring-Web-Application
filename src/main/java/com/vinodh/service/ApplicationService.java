package com.vinodh.service;

/**
 * 
 * This is Marker Interface which will just used as Super Object for all
 * available service interfaces in the Application. All Service Interfaces must
 * implement this Interface.
 * 
 * Note: Trying to use the advantage of Runtime Polymorphism.
 * 
 * This is mainly created for Unit testing of Controller in standalone setup.
 * TestCustomValidationFactory is custom test class created to validate the
 * CUSTOM VALIDATOR CONSTRAINTS and this Service is passed as as one of the
 * constructor arguments which will have mocked service.
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
public interface ApplicationService {

}
