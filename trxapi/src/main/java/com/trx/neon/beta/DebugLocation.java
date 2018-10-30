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
package com.trx.neon.beta;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Beta functionality, not yet ready for public consumption.  
 * This class will eventually be used to help debug the Neon Location Services in the presence of 
 * various kinds of constraints.   
 * @author Dan
 */
public final class DebugLocation implements Parcelable 
{
	public final long ElapsedRealTimeMs;
	public final double Latitude;
	public final double Longitude;
	public final Float Floor;
	public final Float Altitude;

	/**
	 * Get the time in Unix time milliseconds
	 * @return
	 */
	public long GetUnixTimeMs()
	{
		return this.ElapsedRealTimeMs + System.currentTimeMillis() - SystemClock.elapsedRealtime();
	}

	public DebugLocation(long unixTimeMs, double latitude, double longitude, Float floor, Float altitude)
	{
		ElapsedRealTimeMs = SystemClock.elapsedRealtime() - System.currentTimeMillis() + unixTimeMs;
		Latitude = latitude;
		Longitude = longitude;
		Floor = floor;
		Altitude = altitude;
		
		if (!this.isValid())
			throw new IllegalArgumentException();
	}	
	
	//
	// Function to serialize and deserialize the class
	//
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<DebugLocation> CREATOR = new Creator<DebugLocation>() {
		@Override
		public DebugLocation createFromParcel(Parcel in) {
			return new DebugLocation(in);
		}

		@Override
		public DebugLocation[] newArray(int size) {
			return new DebugLocation[size];
		}
	};
	
	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	private DebugLocation(Parcel in)
	{
		ElapsedRealTimeMs = in.readLong();
		Longitude = in.readDouble();
		Latitude = in.readDouble();
		Floor = in.readByte() > 0 ? in.readFloat() : null;
		Altitude = in.readByte() > 0 ? in.readFloat() : null;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(ElapsedRealTimeMs);

		dest.writeDouble(Longitude);
		dest.writeDouble(Latitude);
		dest.writeByte((byte) (Floor != null ? 1 : 0));
		if (Floor != null)
			dest.writeFloat(Floor);
		dest.writeByte((byte) (Altitude != null ? 1 : 0));
		if (Altitude != null)
			dest.writeFloat(Altitude);	
	}
	
	//
	// Overridden to improve toString() functionality
	//
	
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

	public boolean isValid()
	{
		if (!(Longitude >= -180 && Longitude <= 180))
            return false;

        return Latitude >= -90 && Latitude <= 90;
    }
	
	@Override
	public String toString()
	{
		String sb = ("Time: " + DebugLocation.DEFAULT_DATE_FORMAT.format(new Date(ElapsedRealTimeMs)) + ", ") +
				"Longitude: " + Longitude + ", " +
				"Latitude: " + Latitude + ", " +
				"Floor: " + Floor + ", " +
				"Altitude: " + Altitude;

		return sb;
	}
}
