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
 * Represents a change in the user's posture.  
 * @author Dan
 *
 */
public final class PostureEvent implements Parcelable, INeonEvent
{
	/** Postures that the user can take on. */
	public enum PostureEventType
	{
		UNKNOWN,
		UPRIGHT,
		ON_BACK,
		ON_FRONT,
		ON_SIDE_LEFT,
		ON_SIDE_RIGHT,
		UPSIDE_DOWN
	}
	
	public final long unixTimeMs;
	private final String type;
	
	public PostureEventType getType()
	{
		try
		{
			return Enum.valueOf(PostureEventType.class, type);
		}
		catch (IllegalArgumentException e)
		{
			//Version mismatch with Neon Location Services? 
			return null;
		}
	}

	public String getKey()
	{
		return NeonEventType.POSTURE.name();
	}

	public NeonEventType getEventType()
	{
		return NeonEventType.POSTURE;
	}
	
	public PostureEvent(long unixTimeMs, PostureEventType type)
	{
		this.unixTimeMs = unixTimeMs;
		this.type = type.name();
	}
	
	private PostureEvent(Parcel in)
	{
		this.unixTimeMs = in.readLong();
		this.type = in.readString();
	}
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<PostureEvent> CREATOR = new Creator<PostureEvent>() {
		@Override
		public PostureEvent createFromParcel(Parcel in) {
			return new PostureEvent(in);
		}

		@Override
		public PostureEvent[] newArray(int size) {
			return new PostureEvent[size];
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
	}
	
	public String toString()
	{
		return ("Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", ") + "Type: " + type;
	}
}