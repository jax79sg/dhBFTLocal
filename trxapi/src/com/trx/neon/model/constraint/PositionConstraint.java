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
 * This constraint can specify a 2D user position, as a center latitude longitude and error distribution around it.
 * 
 * @author John
 */
public final class PositionConstraint implements Parcelable 
{	
	private final double latitude;
	private final double longitude;
	private final ErrorModel error;
	
	/**
	 * Gets the latitude of the constraint in WGS-84
	 * @return
	 */
	public double getLatitude() { return latitude; }
	
	/**
	 * Gets the longitude of the constraint in WGS-84
	 * @return
	 */
	public double getLongitude() { return longitude; }
	
	/**
	 * Probability distribution of the constraint about the specified location.  
	 * In meters.
	 * @return
	 */
	public ErrorModel getError() { return error; }
		
	/**
	 * Position constraint
	 * 
	 * @param latitude Latitude in WGS-84 coordinates
	 * @param longitude Longitude in WGS-84 coordinates
	 * @param error Error model, cannot be null
	 */
	public PositionConstraint(double latitude, double longitude, ErrorModel error)
	{
		this.latitude = latitude;
		this.longitude = longitude;
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
	public static final Creator<PositionConstraint> CREATOR = new Creator<PositionConstraint>() {
		@Override
		public PositionConstraint createFromParcel(Parcel in) {
			return new PositionConstraint(in);
		}

		@Override
		public PositionConstraint[] newArray(int size) {
			return new PositionConstraint[size];
		}
	};
	
	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	public PositionConstraint(Parcel in)
	{
		latitude = in.readDouble();
		longitude = in.readDouble();
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
		dest.writeDouble(latitude);
		dest.writeDouble(longitude);
		error.writeToParcel(dest, flags);
	}

	//
	// Overridden to improve toString() functionality
	//
	
	@Override
	public String toString()
	{
		return "Center: " + latitude + ", " + longitude + " - Error: {" + error.toString() + "}";	
	}
}
