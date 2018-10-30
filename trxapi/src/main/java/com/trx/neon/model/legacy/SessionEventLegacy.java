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
package com.trx.neon.model.legacy;

import android.os.Parcel;
import android.os.Parcelable;

import com.trx.neon.model.event.INeonEvent;
import com.trx.neon.model.event.NeonEventType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Deprecated
public class SessionEventLegacy implements Parcelable, INeonEvent
{
	@Deprecated
	public final int serialNumber;
	@Deprecated
	public final int session;

	/**
	 * ID Constructor, takes a unique identifier as input
	 */
	public SessionEventLegacy(int id, int session) {
		this.serialNumber = id;
		this.session = session;
	}

	/**
	 * Used for sending data across binders
	 */
	private SessionEventLegacy(Parcel in) {
		this.serialNumber = in.readInt();
		this.session = in.readInt();
	}

	public String getKey()
	{
		return NeonEventType.SESSION.name();
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.SESSION;
	}

	/**
	 * @see Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	/**
	 * @see Object#equals(Object)
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o instanceof SessionEventLegacy)
			return ((SessionEventLegacy) o).serialNumber == this.serialNumber && ((SessionEventLegacy) o).session == this.session;
		return false;
	}

	/**
	 * Gets a hashcode for this instance
	 */
	public int hashCode() {
		return serialNumber + session * 3;
	}

	/**
	 * Used for sending data across binders
	 */
	public void toStream(DataOutputStream writer) throws IOException {
		writer.writeInt(serialNumber);
		writer.writeInt(session);
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return "(PID: " + serialNumber + ":"+session+")";
	}

	/**
	 * @see Parcelable#writeToParcel(Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(serialNumber);
		out.writeInt(session);
	}

	/**
	 * Used for sending data across binders
	 */
	public static final Creator<SessionEventLegacy> CREATOR = new Creator<SessionEventLegacy>() {
		/**
		 * @see Creator#createFromParcel(Parcel)
		 */
		@Override
		public SessionEventLegacy createFromParcel(Parcel in) {
			return new SessionEventLegacy(in);
		}

		/**
		 * @see Creator#newArray(int)
		 */
		@Override
		public SessionEventLegacy[] newArray(int size) {
			return new SessionEventLegacy[size];
		}
	};

	/**
	 * Used for sending data across streams
	 */
	public static SessionEventLegacy fromStream(DataInputStream reader)
			throws IOException
	{
		return new SessionEventLegacy(reader.readInt(), reader.readInt());
	}
}
