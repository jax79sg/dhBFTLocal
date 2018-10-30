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
 * Represents the last time a sensor was calibrated.
 * @author Dan
 *
 */
public final class CalibrationEvent implements Parcelable, INeonEvent
{
	/** Types of sensors that can generate calibration events */
	public enum CalibrationEventType
	{
		GYROSCOPE,
		MAGNETOMETER
	}

	private final long unixTimeMs;
	public final String type;
	
	public CalibrationEventType getType()
	{
		try
		{
			return Enum.valueOf(CalibrationEventType.class, type);
		}
		catch (IllegalArgumentException e)
		{
			//Version mismatch with Neon Location Services? 
			return null;
		}
	}

	public String getKey()
	{
		return type;
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.CALIBRATION;
	}
	
	public CalibrationEvent(long unixTimeMs, CalibrationEventType type)
	{
		this.unixTimeMs = unixTimeMs;
		this.type = type.name();
	}
	
	private CalibrationEvent(Parcel in)
	{
		this.unixTimeMs = in.readLong();
		this.type = in.readString();
	}
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<CalibrationEvent> CREATOR = new Creator<CalibrationEvent>() {
		@Override
		public CalibrationEvent createFromParcel(Parcel in) {
			return new CalibrationEvent(in);
		}

		@Override
		public CalibrationEvent[] newArray(int size) {
			return new CalibrationEvent[size];
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