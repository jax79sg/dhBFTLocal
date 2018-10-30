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

import com.trx.neon.model.event.INeonEvent;
import com.trx.neon.model.event.NeonEventType;

/**
 * The interface application code must implement to listen to INeonEvents.  
 * @author Dan
 */
public interface INeonEventListener 
{
	/**
	 * This function will be called on any event listeners registered with Neon whenever an event occurs.
	 * @param type
	 * @param event
	 */
	void onEvent(NeonEventType type, INeonEvent event);
}
