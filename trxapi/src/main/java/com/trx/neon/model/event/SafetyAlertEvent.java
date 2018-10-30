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
 * Represents an event that might show a user is in danger
 * @author Dan
 */
public final class SafetyAlertEvent implements Parcelable, INeonEvent
{
    public enum SafetyAlertEventType
    {
        NO_MOTION,
        HIGH_IMPACT,
        FREE_FALL
    }

    public final long startTimeMs;
    public final long endTimeMs;
    private final String type;

    public SafetyAlertEventType getType()
    {
        try
        {
            return Enum.valueOf(SafetyAlertEventType.class, type);
        }
        catch (IllegalArgumentException e)
        {
            //Version mismatch with Neon Location Services?
            return null;
        }
    }

    public String getKey()
    {
        return NeonEventType.SAFETY_ALERT.name();
    }

    public NeonEventType getEventType()
    {
        return NeonEventType.SAFETY_ALERT;
    }

    public SafetyAlertEvent(long startTimeMs, long endTimeMs, SafetyAlertEventType eventType)
    {
        this.startTimeMs = startTimeMs;
        this.endTimeMs = endTimeMs;
        this.type = eventType.name();
    }

    private SafetyAlertEvent(Parcel in)
    {
        this.startTimeMs = in.readLong();
        this.endTimeMs = in.readLong();
        this.type = in.readString();
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<SafetyAlertEvent> CREATOR = new Creator<SafetyAlertEvent>() {
        @Override
        public SafetyAlertEvent createFromParcel(Parcel in) {
            return new SafetyAlertEvent(in);
        }

        @Override
        public SafetyAlertEvent[] newArray(int size) {
            return new SafetyAlertEvent[size];
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
        dest.writeLong(startTimeMs);
        dest.writeLong(endTimeMs);
        dest.writeString(type);
    }

    public String toString()
    {
        return "Start: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(startTimeMs)) + ", "
                + "End: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(endTimeMs)) + ", "
                + "Type: " + type;
    }
}
