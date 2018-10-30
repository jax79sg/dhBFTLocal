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
 * Represents detections of structural features.
 * The time these events are reported will often trail the end of the feature's traversal by several seconds,
 * however the Start and Stop times are meaningful and valid only for the time the user was traversing the
 * feature.  
 * 
 * Several partial events are available to avoid the delay, but come with a higher chance of false positives. 
 * @author Dan
 *
 */
public final class MapperStructuralFeatureEvent implements Parcelable, INeonEvent
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
	public enum MapperStructuralFeatureEventType
	{
		ELEVATOR,
		ELEVATOR_TRIP,
		FLOOR,
		STAIRWELL,
		STAIRWELL_FLIGHT,
		ENTRANCE,
        WIFI_SCAN,
		WIFI_SIGNATURE,
        WIFI_SIGNATURES_FAILED,
        PHONE_INFO,
        DEBUG_MESSAGE
	}

	private final long startTime; //in ms since boot
	private final long stopTime; //in ms since boot

	private final String type;
	private final String userInfo;

	public MapperStructuralFeatureEventType getType()
	{
		try
		{
			return Enum.valueOf(MapperStructuralFeatureEventType.class, type);
		}
		catch (IllegalArgumentException e)
		{
			//Version mismatch with Neon Location Services?
			return null;
		}
	}

	public String getKey()
	{
		return NeonEventType.MAPPER_STRUCTURAL_FEATURE.name();
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.MAPPER_STRUCTURAL_FEATURE;
	}

    public long getStopTime()
    {
        return stopTime;
    }

	public String getUserInfo()
	{
		return userInfo;
	}

    public MapperStructuralFeatureEvent(long startTime, long stopTime, MapperStructuralFeatureEventType type, String userInfo)
	{
		this.startTime = startTime;
		this.stopTime = stopTime;

		this.type = type.name();
		this.userInfo = userInfo;
	}

	private MapperStructuralFeatureEvent(Parcel in)
	{
		this.startTime = in.readLong();
		this.stopTime = in.readLong();

		this.type = in.readString();
		this.userInfo = in.readString();
	}

	/**
	 * Used for sending data across binders
	 */
	public static final Creator<MapperStructuralFeatureEvent> CREATOR = new Creator<MapperStructuralFeatureEvent>() {
		@Override
		public MapperStructuralFeatureEvent createFromParcel(Parcel in) {
			return new MapperStructuralFeatureEvent(in);
		}

		@Override
		public MapperStructuralFeatureEvent[] newArray(int size) {
			return new MapperStructuralFeatureEvent[size];
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
		dest.writeString(userInfo);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Start Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(startTime)) + ", ");
		sb.append("Stop Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(stopTime)) + ", ");
		sb.append("Type: " + type);
		sb.append("UserInfo: " + userInfo);
		
		return sb.toString();
	}
}
