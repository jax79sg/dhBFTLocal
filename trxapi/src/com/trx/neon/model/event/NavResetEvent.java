package com.trx.neon.model.event;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by John on 7/5/2016.
 */
public class NavResetEvent implements Parcelable, INeonEvent{

    public final long unixTimeMs;

    public NavResetEvent(long unixTimeMs)
    {
        this.unixTimeMs = unixTimeMs;
    }

    private NavResetEvent(Parcel in)
    {
        this.unixTimeMs = in.readLong();
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<NavResetEvent> CREATOR = new Creator<NavResetEvent>() {
        @Override
        public NavResetEvent createFromParcel(Parcel in) {
            return new NavResetEvent(in);
        }

        @Override
        public NavResetEvent[] newArray(int size) {
            return new NavResetEvent[size];
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
    }

    public String toString()
    {
        return ("Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", nav reset");
    }

    @Override
    public String getKey() {
        return NeonEventType.NAV_RESET.name();
    }

    @Override
    public NeonEventType getEventType() {
        return NeonEventType.NAV_RESET;
    }
}
