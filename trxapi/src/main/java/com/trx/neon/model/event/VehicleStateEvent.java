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

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a change in the user's vehicle state.  
 * @author Travis
 *
 */
public final class VehicleStateEvent implements Parcelable, INeonEvent
{
	/** Motion Levels that the user can take on. */
	public enum VehicleStateEventType
	{
		UNKNOWN,
		START,
		END
	}
	
	private final long unixTimeMs;
	private final String type;
	
	public VehicleStateEventType getType()
	{
		try
		{
			return Enum.valueOf(VehicleStateEventType.class, type);
		}
		catch (IllegalArgumentException e)
		{
			//Version mismatch with Neon Location Services? 
			return null;
		}
	}

	public String getKey()
	{
		return NeonEventType.VEHICLE.name();
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.VEHICLE;
	}
	
	public VehicleStateEvent(long unixTimeMs, VehicleStateEventType type)
	{
		this.unixTimeMs = unixTimeMs;
		this.type = type.name();
	}
	
	private VehicleStateEvent(Parcel in)
	{
		unixTimeMs = in.readLong();
		type = in.readString();
	}
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<VehicleStateEvent> CREATOR = new Creator<VehicleStateEvent>() {
		@Override
		public VehicleStateEvent createFromParcel(Parcel in) {
			return new VehicleStateEvent(in);
		}

		@Override
		public VehicleStateEvent[] newArray(int size) {
			return new VehicleStateEvent[size];
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