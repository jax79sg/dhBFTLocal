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
public final class FilteredRawFeatureEvent implements Parcelable, INeonEvent
{
	public String descriptor;

	public FilteredRawFeatureEvent(String descriptor)
	{
		this.descriptor = descriptor;
	}

	@Override
	public NeonEventType getEventType()
	{
		return NeonEventType.FILTERED_RAW_FEATURE;
	}

	@Override
	public String getKey()
	{
		return NeonEventType.FILTERED_RAW_FEATURE.name();
	}
	//
	// Function to serialize and deserialize the class
	//

	/**
	 * Used for sending data across binders
	 */
	public static final Creator<FilteredRawFeatureEvent> CREATOR = new Creator<FilteredRawFeatureEvent>() {
		@Override
		public FilteredRawFeatureEvent createFromParcel(Parcel in) {
			return new FilteredRawFeatureEvent(in);
		}

		@Override
		public FilteredRawFeatureEvent[] newArray(int size) {
			return new FilteredRawFeatureEvent[size];
		}
	};

	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	private FilteredRawFeatureEvent(Parcel in)
	{
		descriptor = in.readString();	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(descriptor);
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
