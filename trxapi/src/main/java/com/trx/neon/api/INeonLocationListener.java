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
package com.trx.neon.api;

import com.trx.neon.model.NeonLocation;

/**
 * The interface application code must implement to listen for location data.  
 * @author Dan
 */
public interface INeonLocationListener 
{
	/**
	 * This function will be called with a user's location estimate whenever Neon has calculated a new location,
	 * or computed a more accurate path history.  
	 * @param location
	 */
	void onLocationChanged(NeonLocation location);
}
