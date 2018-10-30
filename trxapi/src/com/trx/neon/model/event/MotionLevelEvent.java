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

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Represents a change in the user's motion level.  
 * @author Dan
 *
 */
public final class MotionLevelEvent implements Parcelable, INeonEvent
{
	/** Motion Levels that the user can take on. */
	public enum MotionLevelEventType
	{
		UNKNOWN,
		NONE,
		LOW,
		HIGH
	}
	
	public final long unixTimeMs;
	private final String type;
	
	public MotionLevelEventType getType()
	{
		try
		{
			return Enum.valueOf(MotionLevelEventType.class, type);
		}
		catch (IllegalArgumentException e)
		{
			//Version mismatch with Neon Location Services? 
			return null;
		}
	}

	public String getKey()
	{
		return NeonEventType.MOTION_LEVEL.name();
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.MOTION_LEVEL;
	}
	
	public MotionLevelEvent(long unixTimeMs, MotionLevelEventType type)
{
	this.unixTimeMs = unixTimeMs;
	this.type = type.name();
}

	private MotionLevelEvent(Parcel in)
	{
		unixTimeMs = in.readLong();
		type = in.readString();
	}
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<MotionLevelEvent> CREATOR = new Creator<MotionLevelEvent>() {
		@Override
		public MotionLevelEvent createFromParcel(Parcel in) {
			return new MotionLevelEvent(in);
		}

		@Override
		public MotionLevelEvent[] newArray(int size) {
			return new MotionLevelEvent[size];
		}
	};	
	
	@Override
	public int describeContents() 
	{
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeLong(unixTimeMs);
		dest.writeString(type);
	}
	
	public String toString()
	{
		return ("Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", ") + "Type: " + type;
	}
}