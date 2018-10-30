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
package com.trx.neon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Status result on download operations to the Neon Location Service
 * @author Travis
 *
 */
public enum DownloadResult implements Parcelable
{
	/** There are connectivity issues preventing download at this time,
	 *  or log in has not been successful */
	CONNECTION_ERROR,
	/** We have successfully downloaded buildings to the Neon Location Service */
	SUCCESS;

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(ordinal());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<DownloadResult> CREATOR = new Creator<DownloadResult>() {
		@Override
		public DownloadResult createFromParcel(Parcel in) {
			int value = in.readInt();

			if(value >= DownloadOptions.values().length)
				return DownloadResult.values()[0];
			return DownloadResult.values()[value];
		}

		@Override
		public DownloadResult[] newArray(int size) {
			return new DownloadResult[size];
		}
	};
}
