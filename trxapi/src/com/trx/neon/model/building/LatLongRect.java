/******************************************************************************
 *
 * 	 Copyright 2017, TRX Systems, Inc.  All Rights Reserved.
 *
 *   TRX Systems, Inc.
 *   7500 Greenway Center Drive, Suite 420
 *   Greenbelt, Maryland  20770
 *
 *   Tel:    (301) 313-0053
 *   email:  info@trxsystems.com
 *
 *****************************************************************************/
package com.trx.neon.model.building;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Not technically a rectangle, but the closest you can get in spherical coordinates, this specifies an area of the world.
 * @author Travis
 *
 */
public class LatLongRect implements Parcelable
{
	/**
	 * LatLong that specifies the southwest corner of the rectangle
	 */
	public final LatLong Southwest;
	/**
	 * LatLong that specifies the northeast corner of the rectangle
	 */
	public final LatLong Northeast;

	public LatLongRect(LatLong southWest,
			LatLong northEast)
	{
		this.Southwest = southWest;
		this.Northeast = northEast;
	}

	@Override
	public String toString()
	{
		return "Southwest: "+Southwest.toString()+" Northeast: "+Northeast.toString();
	}

	/**
	 * Used for sending data across binders
	 */
	private LatLongRect(Parcel in)
	{
		Southwest = new LatLong(in);
		Northeast = new LatLong(in);
	}

	public static final Creator<LatLongRect> CREATOR = new Creator<LatLongRect>() {
		@Override
		public LatLongRect createFromParcel(Parcel in) {
			return new LatLongRect(in);
		}

		@Override
		public LatLongRect[] newArray(int size) {
			return new LatLongRect[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		Southwest.writeToParcel(dest, flags);
		Northeast.writeToParcel(dest, flags);
	}
}
