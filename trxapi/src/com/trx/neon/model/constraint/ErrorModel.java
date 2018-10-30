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
 * Represents a uniform or gaussian error model.  More types may be added as new constraint sources are modeled.  
 * Units of the radius are in meters.  
 *
 * PositionConstraint radii should be in meters, 
 * FloorConstraint radii should be in meters,
 * AltitudeConstraint radii should be in meters.
 * 
 * @author John
 *
 */
public final class ErrorModel implements Parcelable 
{
	public static final int UNIFORM_ID = 0;
	/**
	 * Error model with Uniform probability distribution
	 * Error is centered at 0
	 * @param radius units are in meters
	 * @return
	 */
	public static ErrorModel uniform(float radius)
	{
		return new ErrorModel(UNIFORM_ID, radius);
	}
	
	private static final int GAUSSIAN_ID = 1;
	/**
	 * Error model with Gaussian probability distribution
	 * Error is centered at 0
	 * @param stdDev units are in meters
	 * @return
	 */
	public static ErrorModel gaussian(float stdDev)
	{
		return new ErrorModel(GAUSSIAN_ID, stdDev);
	}
	
	private int errorType;
	private final Float parameter1;
	
	/**
	 * Accessor to get error type
	 * @return
	 */
	public int getErrorType() { return errorType; }
	
	/**
	 * Accessor to get parameter 1 of the error model
	 * @return
	 */
	public Float getParameter1() { return parameter1; }
	
	private ErrorModel(int errorType, Float parameter1)
	{
		this.errorType = errorType;
		this.parameter1 = parameter1;
	}
	
	//
	// Function to serialize and deserialize the class
	//
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<ErrorModel> CREATOR = new Creator<ErrorModel>() {
		@Override
		public ErrorModel createFromParcel(Parcel in) {
			return new ErrorModel(in);
		}

		@Override
		public ErrorModel[] newArray(int size) {
			return new ErrorModel[size];
		}
	};
	
	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	public ErrorModel (Parcel in)
	{
		in.readInt();
		if (in.readByte() > 0)
			parameter1 = in.readFloat();
		else
			parameter1 = null;		
	}	
	
	@Override
	public int describeContents() 
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeInt(errorType);
		dest.writeByte(parameter1 == null ? (byte)0 : (byte)1);
		if (parameter1 != null)
			dest.writeFloat(parameter1);
	}

	//
	// Overridden to improve toString() functionality
	//
	
	@Override
	public String toString()
	{
		switch(errorType)
		{
			case UNIFORM_ID:
				return "Uniform Error (" + parameter1 + "m)";
			case GAUSSIAN_ID:
				return "Gaussian Error (" + parameter1 + "m stdev)";
		}
		return "UNKNOWN[" + errorType + "]";
	}
}
