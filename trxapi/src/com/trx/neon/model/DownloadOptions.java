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
 * Options for downloading buildings to the Neon Location Service
 * @author Travis
 *
 */
public enum DownloadOptions implements Parcelable
{
	/**
	 * Once buildings have been downloaded from the server, future requests
	 * for those buildings will return from the cache and will not re-download
	 * from the server until the cache expires
	 */
	CACHED,
	/**
	 * This will force the Neon Location Service to re-download from the server.
	 * This should be used SPARINGLY, generally only after edits have been made to the
	 * buildings on the server.  Call this too many times and the API will throw an
	 * exception.
	 */
	FORCE_REFRESH;

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(ordinal());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<DownloadOptions> CREATOR = new Creator<DownloadOptions>() {
		@Override
		public DownloadOptions createFromParcel(Parcel in) {
			int value = in.readInt();

			if(value >= DownloadOptions.values().length)
				return DownloadOptions.values()[0];
			return DownloadOptions.values()[value];
		}

		@Override
		public DownloadOptions[] newArray(int size) {
			return new DownloadOptions[size];
		}
	};
}
