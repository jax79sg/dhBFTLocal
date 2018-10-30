/******************************************************************************
 * 		
 * 	 Copyright 2015, TRX Systems, Inc.  All Rights Reserved.
 * 
 *   TRX Systems, Inc.
 *   7500 Greenway Center Drive, Suite 420
 *   Greenbelt, Maryland  20770
 *   
 *   Tel:    (301) 313-0053
 *   email:  info@trxsystems.com
 * 
 *****************************************************************************/
package com.trx.neon.model;

/**
 * Type describing a filter for location data passed back from Neon
 * @author Dan
 *
 */
public enum NeonLocationType 
{
	/**
	 * Specifies that the application is only interested in the most up to date location
	 * There will potentially (but rarely) be small gaps in the location data reported.  
	 */
	CURRENT,
	/**
	 * Includes CURRENT locations, also specifies that the application would 
	 * like to be updated with the user's location at every step.  This will 
	 * prevent any gaps in the reported location data.  
	 */
	PER_STEP,
	/**
	 * Includes PER_STEP locations, also specifies that the application would 
	 * like location history to be corrected over time.  In this case, locations
	 * reported are not reported in order.
	 */
	CORRECTED
}
