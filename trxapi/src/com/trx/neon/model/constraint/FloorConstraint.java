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
 * Represents a constraint specifying that the user is on a particular floor 
 * (or a range of floors each with some probability, depending on the included ErrorModel)
 * 
 * @author John
 */
public final class FloorConstraint implements Parcelable 
{	
	private final float floor;
	private final ErrorModel error;
	
	/**
	 * Floors in European format
	 * @return
	 */
	public float getFloor() { return floor; }
	
	/**
	 * Probability distribution of the constraint about the specified floor.  
	 * Also in meters.
	 * @return
	 */
	public ErrorModel getError() { return error; }
	
	/**
	 * A floor constraint, restricting location to a particular floor number (European style: 0 = lobby, 3 = 4th floor in US)
	 * @param floor Floor number in European style numbering
	 * @param error Error model, cannot be null
	 */
	public FloorConstraint(float floor, ErrorModel error)
	{
		this.floor = floor;
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
	public static final Creator<FloorConstraint> CREATOR = new Creator<FloorConstraint>() {
		@Override
		public FloorConstraint createFromParcel(Parcel in) {
			return new FloorConstraint(in);
		}

		@Override
		public FloorConstraint[] newArray(int size) {
			return new FloorConstraint[size];
		}
	};
	
	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	public FloorConstraint(Parcel in)
	{
		floor = in.readFloat();
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
		dest.writeFloat(floor);
		error.writeToParcel(dest, flags);
	}

	//
	// Overridden to improve toString() functionality
	//
	
	@Override
	public String toString()
	{
		return "Center: " + floor + " floor - Error: {" + error.toString() + "}";	
	}
}
