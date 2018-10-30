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
 * Represents a range measurement with a remote device using either UWB or BLE technology
 * @author Dan
 *
 */
public final class RemoteRangeEvent implements Parcelable, INeonEvent
{
    /**
     * Supported Ranging Technologies
     */
    public enum RemoteRangeEventType
    {
        BLE,
        UWB
    }

    public final long unixTimeMs;
    private final String type;
    public final String remoteDeviceID;
    public final float range;

    public RemoteRangeEventType getType()
    {
        try
        {
            return Enum.valueOf(RemoteRangeEventType.class, type);
        }
        catch (IllegalArgumentException e)
        {
            //Version mismatch with Neon Location Services?
            return null;
        }
    }

    public String getKey()
    {
        return NeonEventType.REMOTE_RANGE.name();
    }

    public NeonEventType getEventType()
    {
        return NeonEventType.REMOTE_RANGE;
    }

    public RemoteRangeEvent(long unixTimeMs, RemoteRangeEventType type, String remoteDeviceID, float range)
    {
        this.unixTimeMs = unixTimeMs;
        this.type = type.name();
        this.remoteDeviceID = remoteDeviceID;
        this.range = range;
    }

    private RemoteRangeEvent(Parcel in)
    {
        this.unixTimeMs = in.readLong();
        this.type = in.readString();
        this.remoteDeviceID = in.readString();
        this.range = in.readFloat();
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<RemoteRangeEvent> CREATOR = new Creator<RemoteRangeEvent>() {
        @Override
        public RemoteRangeEvent createFromParcel(Parcel in) {
            return new RemoteRangeEvent(in);
        }

        @Override
        public RemoteRangeEvent[] newArray(int size) {
            return new RemoteRangeEvent[size];
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
        dest.writeString(remoteDeviceID);
        dest.writeFloat(range);
    }

    public String toString()
    {
        return ("Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", ")
                + "Type: " + type + ", "
                + "ID: " + remoteDeviceID + ", "
                + "Range: " + range;
    }
}
