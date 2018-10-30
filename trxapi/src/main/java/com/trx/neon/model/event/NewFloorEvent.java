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
 * Created by Travis on 7/5/2016.
 */
public class NewFloorEvent implements Parcelable, INeonEvent{

	public final long unixTimeMs;

	public NewFloorEvent(long unixTimeMs)
	{
		this.unixTimeMs = unixTimeMs;
	}

	private NewFloorEvent(Parcel in)
	{
		this.unixTimeMs = in.readLong();
	}

	/**
	 * Used for sending data across binders
	 */
	public static final Creator<NewFloorEvent> CREATOR = new Creator<NewFloorEvent>() {
		@Override
		public NewFloorEvent createFromParcel(Parcel in) {
			return new NewFloorEvent(in);
		}

		@Override
		public NewFloorEvent[] newArray(int size) {
			return new NewFloorEvent[size];
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
		dest.writeLong(unixTimeMs);
	}

	public String toString()
	{
		return ("Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", ");
	}

	@Override
	public String getKey() {
		return NeonEventType.NEW_FLOOR.name();
	}

	@Override
	public NeonEventType getEventType() {
		return NeonEventType.NEW_FLOOR;
	}
}
