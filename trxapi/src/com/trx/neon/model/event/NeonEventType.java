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
 * All of the possible types of events.  In the future, more events may be added.  
 * @author Dan
 *
 */
public enum NeonEventType 
{
	BATTERY,
	BINDING,
	CALIBRATION,
	CONNECTIVITY,
	MOTION_LEVEL,
	POSTURE,
	REMOTE_RANGE,
	SAFETY_ALERT,
	SESSION,
	STRUCTURAL_FEATURE,
	VEHICLE,
	UPDATE_AVAILABLE,
	MAPPER_STRUCTURAL_FEATURE,
	RAW_FEATURE,
	FILTERED_RAW_FEATURE,
	NAV_RESET,
	NAV_LOCK,
	NEW_FLOOR,
    INDOOR_OUTDOOR_TRANSITION
}
