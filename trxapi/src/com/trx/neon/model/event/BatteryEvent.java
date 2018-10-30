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
 * Represents a change of at least 1% in the battery level of a device
 * @author Dan
 */
public final class BatteryEvent implements Parcelable, INeonEvent
{
    public final long unixTimeMs;
    public final String sourceID;
    public final String sourceType;
    public final byte percent;

    public String getKey()
    {
        return NeonEventType.BATTERY.name();
    }

    public NeonEventType getEventType()
    {
        return NeonEventType.BATTERY;
    }

    public BatteryEvent(long unixTimeMs, String sourceID, String sourceType, byte percent)
    {
        this.unixTimeMs = unixTimeMs;
        this.sourceID = sourceID;
        this.sourceType = sourceType;
        this.percent = percent;
    }

    private BatteryEvent(Parcel in)
    {
        this.unixTimeMs = in.readLong();
        this.sourceID = in.readString();
        this.sourceType = in.readString();
        this.percent = in.readByte();
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<BatteryEvent> CREATOR = new Creator<BatteryEvent>() {
        @Override
        public BatteryEvent createFromParcel(Parcel in) {
            return new BatteryEvent(in);
        }

        @Override
        public BatteryEvent[] newArray(int size) {
            return new BatteryEvent[size];
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
        dest.writeString(this.sourceID);
        dest.writeString(this.sourceType);
        dest.writeByte(this.percent);
    }

    public String toString()
    {
        return ("Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", ")
                + "SourceID: " + sourceID + ", "
                + "SourceType: " + sourceType + ", "
                + "Percent: " + percent;
    }
}
