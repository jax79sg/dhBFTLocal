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

/**
 * Uniquely identifies a tracking session, can be stored in HashMaps.
 * @author Dan
 *
 */
public class SessionEvent implements Parcelable, INeonEvent
{
	public enum DeviceType
	{
		INU8_BLE, // INU8 with BLE Ranging
		INU8_UWB, // INU8 with UWB Ranging
		PHONE	  // Phone Sensors
	}

	public final int serialNumber;
	public final int session;
	public final String deviceType;

	/**
	 * Retrieve the device type connected to NEON
	 */
	public DeviceType getDeviceType()
	{
		try
		{
			return Enum.valueOf(DeviceType.class, deviceType);
		}
		catch (IllegalArgumentException e)
		{
			//Version mismatch with Neon Location Services?
			return null;
		}
	}

	/**
	 * ID Constructor, takes a unique identifier as input
	 */
	public SessionEvent(int id, int session, DeviceType deviceType) {
		this.serialNumber = id;
		this.session = session;
		this.deviceType = deviceType.name();
	}

	/**
	 * Used for sending data across binders
	 */
	private SessionEvent(Parcel in) {
		this.serialNumber = in.readInt();
		this.session = in.readInt();
		this.deviceType = in.readString();
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
		if (o instanceof SessionEvent)
			return ((SessionEvent) o).serialNumber == this.serialNumber && ((SessionEvent) o).session == this.session;
		return false;
	}

	/**
	 * Gets a hashcode for this instance
	 */
	public int hashCode() {
		return serialNumber + session * 3;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return "(PID: " + serialNumber + ":"+session+"[" + deviceType + "])";
	}

	/**
	 * @see Parcelable#writeToParcel(Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(serialNumber);
		out.writeInt(session);
		out.writeString(deviceType);
	}

	/**
	 * Used for sending data across binders
	 */
	public static final Creator<SessionEvent> CREATOR = new Creator<SessionEvent>() {
		/**
		 * @see Creator#createFromParcel(Parcel)
		 */
		@Override
		public SessionEvent createFromParcel(Parcel in) {
			return new SessionEvent(in);
		}

		/**
		 * @see Creator#newArray(int)
		 */
		@Override
		public SessionEvent[] newArray(int size) {
			return new SessionEvent[size];
		}
	};
}
