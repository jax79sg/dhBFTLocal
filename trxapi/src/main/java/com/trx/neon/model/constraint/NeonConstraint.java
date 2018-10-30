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
package com.trx.neon.model.constraint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

/**
 * A NeonConstraint constrains user location at a specified time in 2D, 
 * and optionally in absolute altitude and/or building floor.
 * @author Dan
 */
public class NeonConstraint implements Parcelable
{	
	/** Default format for displaying date/time strings */
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

	/**
	 * Creates a constraint at the current time with a default circular uniform error model at the specified latitude and longitude
	 */
	public NeonConstraint(double latitude, double longitude)
	{
		this(latitude, longitude, System.currentTimeMillis(), ErrorModel.uniform(1));
	}
	
	/**
	 * Creates a new constraint
	 * @param latitude Latitude at the center of the constraint
	 * @param longitude Longitude at the center of the constraint
	 * @param unixTimeMs Time the constraint should affect the user.  (Use System.currentTimeMillis() to specify the current time)
	 * @param errorModel Error radius and distribution
	 * @return A new constraint
	 */
	public NeonConstraint(double latitude, double longitude, long unixTimeMs, ErrorModel errorModel)
	{
		this(SystemClock.elapsedRealtime() - System.currentTimeMillis() + unixTimeMs, new PositionConstraint(latitude, longitude, errorModel));
	}

	/**
	 * Specifies which floor the user is on (of whichever building he is in)
	 * Constraints use a European style numbering system.  
	 * 0 specifies the ground floor.
	 * 1 specifies the first floor above ground 
	 * -1 specifies the first floor below ground
	 * @return The modified constraint (for convenience)
	 */
	public NeonConstraint withFloor(Integer floorNumber)
	{
		Float f = null;
		if (floorNumber != null)
			f = (float)(int)floorNumber;
		
		if (f == null)
			this.floor = null;
		else
			this.floor = new FloorConstraint(floorNumber, ErrorModel.uniform(0.1f));
		
		return this;
	}
		
	/**
	 * Specifies the absolute elevation of the user
	 * @param absoluteAltitude The altitude as WGS-84 meters above the ellipsoid
	 * @param model A radius in meters and how the probability of being at a particular altitude should be calculated
	 * @return The modified constraint (for convenience)
	 */
	public NeonConstraint withAltitude(Float absoluteAltitude, ErrorModel model)
	{
		if (absoluteAltitude == null)
			this.altitude = null;
		else
			this.altitude = new AltitudeConstraint(absoluteAltitude, model);

		return this;
	}
	
	/**Retrieves the timestamp of this constraint.  This value is directly comparable with System.currentTimeMillis()*/
	public long getTimeUTCMillis()
	{
		return System.currentTimeMillis() - SystemClock.elapsedRealtime() + timestamp;
	}
	/**Retrieves the timestamp of this constraint.  This value is directly comparable with SystemClock.elapsedRealTime()*/
	public long getElapsedRealtimeMillis()
	{
		return timestamp;
	}
	
	/** Retrieves the 2D component of this constraint */
	public PositionConstraint getPosition()
	{
		return pos;
	}
	
	/** Retrieves the floor component of this constraint, returns null if it was never set */
	public FloorConstraint getFloor()
	{
		return floor;
	}
	
	/** Retrieves the altitude component of this constraint, returns null if it was never set */
	public AltitudeConstraint getAltitude()
	{
		return altitude;
	}
	
	private final long timestamp;
	private final PositionConstraint pos;
	private FloorConstraint floor;
	private AltitudeConstraint altitude;
	private NeonConstraint(long elapsedRealtimeMs, PositionConstraint pc)
	{
		this.timestamp = elapsedRealtimeMs;
		this.pos = pc;
		
		if (!isValid())
			throw new IllegalArgumentException("Latitude must be in the range -90 to 90, and Longitude must be in the range -180 to 180");
	}

	/**
	 * Used for sending data across binders
	 */
	public static final Creator<NeonConstraint> CREATOR = new Creator<NeonConstraint>() {
		@Override
		public NeonConstraint createFromParcel(Parcel in) {
			return new NeonConstraint(in);
		}

		@Override
		public NeonConstraint[] newArray(int size) {
			return new NeonConstraint[size];
		}
	};
	
	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	private NeonConstraint(Parcel in)
	{
		timestamp = in.readLong();
		pos = new PositionConstraint(in);
		if (in.readByte() > 0)
			floor = new FloorConstraint(in);
		else
			floor = null;
		if (in.readByte() > 0)
			altitude = new AltitudeConstraint(in);
		else 
			altitude = null;
	}
	
	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeLong(timestamp);
		pos.writeToParcel(dest, flags);
		dest.writeByte(floor == null ? (byte)0 : (byte)1);
		if (floor != null)
			floor.writeToParcel(dest, flags);
		dest.writeByte(altitude == null ? (byte)0 : (byte)1);
		if (altitude != null)
			altitude.writeToParcel(dest, flags);		
	}
	
	public boolean isValid()
	{
		if(pos==null)
			return false;
		
		if (!(pos.getLongitude() >= -180 && pos.getLongitude() <= 180))
            return false;

        return pos.getLatitude() >= -90 && pos.getLatitude() <= 90;
    }
	
	//
	// Overridden to improve toString() functionality
	//
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("User Correction: ");
		sb.append("Time: ");
		sb.append(NeonConstraint.DEFAULT_DATE_FORMAT.format(new Date(getTimeUTCMillis())));
		sb.append(", Position: [");
		sb.append(pos);
		
		sb.append("], Floor: [");
		if (floor == null)
			sb.append("NULL");
		else
			sb.append(floor);
		
		sb.append("], Altitude: [");
		if (altitude == null)
			sb.append("NULL");
		else
			sb.append(altitude);
		
		sb.append("]");		
		return sb.toString();
	}
}
