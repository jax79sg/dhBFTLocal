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

import java.util.Date;

/**
 * The binding event indicates when Neon is successfully bound and running
 * It can be used provide status to the user.  
 * @author Dan
 */
public class BindingEvent implements INeonEvent
{
	public enum BindingEventType
	{
		CONNECT, /*The user enabled tracking and the client app is bound*/
		DISCONNECT, /*The user disabled tracking*/
		FATAL_DISCONNECT /*The process running the Neon Location Service was killed*/
	}

	private final long unixTimeMs;
	public final BindingEventType isBound;
	
	public BindingEvent(long unixTimeMs, BindingEventType isBound)
	{
		this.unixTimeMs = unixTimeMs;
		this.isBound = isBound;
	}
	
	public String toString()
	{
		return "Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + " Bound: " + isBound;
	}

	public String getKey()
	{
		return NeonEventType.BINDING.name();
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.BINDING;
	}
}
