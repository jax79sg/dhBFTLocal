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

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This constraint represents an absolute altitude in meters (above the WGS84 ellipsoid), 
 * with some error model fall off around that altitude.
 * 
 * @author John
 */
public final class AltitudeConstraint implements Parcelable 
{
	private final float altitude;
	private final ErrorModel error;
	
	/**
	 * Meters above the WGS84 ellipsoid
	 * @return
	 */
	public float getAltitude() { return altitude; }
	
	/**
	 * Probability distribution of the constraint about the specified altitude.  
	 * Also in meters.
	 * @return
	 */
	public ErrorModel getError() { return error; }
	
	/**
	 * Altitude constraint
	 * 
	 * @param altitude Center location in meters, WGS-84 meters above ellipsoid
	 * @param error Error model, cannot be null
	 */
	public AltitudeConstraint(float altitude, ErrorModel error)
	{
		this.altitude = altitude;
		this.error = error;
		if (error == null)
			throw new IllegalArgumentException("Error cannot be null");
	}
	
	//
	// Function to serialize and deserialize the class
	//
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<AltitudeConstraint> CREATOR = new Creator<AltitudeConstraint>() {
		@Override
		public AltitudeConstraint createFromParcel(Parcel in) {
			return new AltitudeConstraint(in);
		}

		@Override
		public AltitudeConstraint[] newArray(int size) {
			return new AltitudeConstraint[size];
		}
	};
	
	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	public AltitudeConstraint(Parcel in)
	{
		altitude = in.readFloat();
		error = new ErrorModel(in);
	}
	
	@Override
	public int describeContents() 
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeFloat(altitude);
		error.writeToParcel(dest, flags);
	}

	//
	// Overridden to improve toString() functionality
	//
	
	@Override
	public String toString()
	{
		return "Center: " + altitude + "m - Error: {" + error.toString() + "}";	
	}
}
