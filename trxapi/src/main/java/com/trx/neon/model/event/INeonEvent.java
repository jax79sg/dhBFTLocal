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
package com.trx.neon.model.event;

/**
 * An interface inherited by all events.  Based on the event type, you can cast to the type you're interested in.  
 * @author Dan
 *
 */
public interface INeonEvent 
{
	String getKey();
	NeonEventType getEventType();
}
