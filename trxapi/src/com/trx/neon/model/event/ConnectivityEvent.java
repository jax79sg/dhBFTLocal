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

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents the last time a resource was connected/disconnected.  
 * This can be used to determine server connectivity or tracking unit 
 * connectivity in the case of external tracking units.
 * @author Dan
 *
 */
public final class ConnectivityEvent implements Parcelable, INeonEvent
{
	/** Represents a type of resource that NeonLocationService must maintain a connection with */
	public enum ConnectivityEventType
	{
		/**
		 * The location assist data is all bundled together under this type.  Being disconnected
		 * with this type usually indicates the phone does not have internet connectivity, but could also
		 * imply router/firewall problems at the server, or an incorrect ip address or port configuration.
		 */
		SERVER,
		/**
		 * This type represents the polling of hardware: accelerometer, gyros and so forth
		 * Being disconnected here means the user has chosen to use a device other than the Android phone for tracking
		 * and no connection with it can be established.  (Have user check bluetooth etc)
		 */
		SENSORS
	}
	
	private final long unixTimeMs;
	private final String type;
	public final boolean connected;
	
	public ConnectivityEventType getType()
	{
		try
		{
			return Enum.valueOf(ConnectivityEventType.class, type);
		}
		catch (IllegalArgumentException e)
		{
			//Version mismatch with Neon Location Services? 
			return null;
		}
	}

	public String getKey()
	{
		return type;
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.CONNECTIVITY;
	}
	
	public ConnectivityEvent(long unixTimeMs, ConnectivityEventType type, boolean connected)
	{
		this.unixTimeMs = unixTimeMs;
		this.type = type.name();
		this.connected = connected;
	}
	
	private ConnectivityEvent(Parcel in)
	{
		this.unixTimeMs = in.readLong();
		this.type = in.readString();
		this.connected = in.readByte() > 0;
	}
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<ConnectivityEvent> CREATOR = new Creator<ConnectivityEvent>() {
		@Override
		public ConnectivityEvent createFromParcel(Parcel in) {
			return new ConnectivityEvent(in);
		}

		@Override
		public ConnectivityEvent[] newArray(int size) {
			return new ConnectivityEvent[size];
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
		dest.writeString(type);
		dest.writeByte(connected ? (byte)1 : (byte)0);
	}
	
	public String toString()
	{
		return ("Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", ") + "Type: " + type + ", " + "Connected: " + connected;
	}
}