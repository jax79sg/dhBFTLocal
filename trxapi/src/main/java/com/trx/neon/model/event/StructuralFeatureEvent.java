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
 * Represents detections of structural features.
 * The time these events are reported will often trail the end of the feature's traversal by several seconds,
 * however the Start and Stop times are meaningful and valid only for the time the user was traversing the
 * feature.  
 * 
 * Several partial events are available to avoid the delay, but come with a higher chance of false positives. 
 * @author Dan
 *
 */
public final class StructuralFeatureEvent implements Parcelable, INeonEvent
{
	/**
	 * Types of structural feature events.  
	 * Elevators, Stairwells, and Floors are full features.
	 * ElevatorTrips and StairwellFlights are partial features.  
	 * 
	 * Groups of partial features that are not eventually followed by full features of the corresponding type
	 * can be considered to be false positives.  Partial features will be generated in real time, full features 
	 * will have a longer delay before the event is fired.  
	 */
	public enum StructuralFeatureEventType
	{
		ELEVATOR,
		ELEVATOR_TRIP,
		FLOOR,
		STAIRWELL,
		STAIRWELL_FLIGHT,
		ENTRANCE
	}
	
	private final long startTime; //in ms since boot
	private final long stopTime; //in ms since boot
	
	private final String type;
	
	public StructuralFeatureEventType getType()
	{
		try
		{
			return Enum.valueOf(StructuralFeatureEventType.class, type);
		}
		catch (IllegalArgumentException e)
		{
			//Version mismatch with Neon Location Services? 
			return null;
		}
	}

	public String getKey()
	{
		return NeonEventType.STRUCTURAL_FEATURE.name();
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.STRUCTURAL_FEATURE;
	}

    public long getStopTime()
    {
        return stopTime;
    }

    public StructuralFeatureEvent(long startTime, long stopTime, StructuralFeatureEventType type)
	{
		this.startTime = startTime;
		this.stopTime = stopTime;
		
		this.type = type.name();
	}
	
	private StructuralFeatureEvent(Parcel in)
	{
		this.startTime = in.readLong();
		this.stopTime = in.readLong();
		
		this.type = in.readString();
	}
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<StructuralFeatureEvent> CREATOR = new Creator<StructuralFeatureEvent>() {
		@Override
		public StructuralFeatureEvent createFromParcel(Parcel in) {
			return new StructuralFeatureEvent(in);
		}

		@Override
		public StructuralFeatureEvent[] newArray(int size) {
			return new StructuralFeatureEvent[size];
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
		dest.writeLong(startTime);
		dest.writeLong(stopTime);

		dest.writeString(type);
	}
	
	public String toString()
	{
		String sb = ("Start Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(startTime)) + ", ") +
				"Stop Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(stopTime)) + ", " +
				"Type: " + type;

		return sb;
	}
}
