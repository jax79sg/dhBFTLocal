/******************************************************************************
 * 		
 * 	 Copyright 2016, TRX Systems, Inc.  All Rights Reserved.
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



/**
 * Represents detections of raw features.
 */
public final class RawFeatureEvent implements Parcelable, INeonEvent
{
	public String descriptor;
    public long featureElapsedRealtime;

	public RawFeatureEvent(String descriptor, long featureElapsedRealtime)
	{
		this.descriptor = descriptor;
        this.featureElapsedRealtime = featureElapsedRealtime;
	}

	@Override
	public NeonEventType getEventType()
	{
		return NeonEventType.RAW_FEATURE;
	}

	@Override
	public String getKey()
	{
		return NeonEventType.RAW_FEATURE.name();
	}
	//
	// Function to serialize and deserialize the class
	//

	/**
	 * Used for sending data across binders
	 */
	public static final Creator<RawFeatureEvent> CREATOR = new Creator<RawFeatureEvent>() {
		@Override
		public RawFeatureEvent createFromParcel(Parcel in) {
			return new RawFeatureEvent(in);
		}

		@Override
		public RawFeatureEvent[] newArray(int size) {
			return new RawFeatureEvent[size];
		}
	};

	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	private RawFeatureEvent(Parcel in)
	{
		descriptor = in.readString();
        featureElapsedRealtime = in.readLong();
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(descriptor);
        dest.writeLong(featureElapsedRealtime);
	}

	//
	// Overridden to improve toString() functionality
	//

	@Override
	public String toString()
	{
		return descriptor;
	}
}
