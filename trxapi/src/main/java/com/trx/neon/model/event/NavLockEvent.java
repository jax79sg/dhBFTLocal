package com.trx.neon.model.event;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by John on 8/29/2016.
 */
public final class NavLockEvent implements Parcelable, INeonEvent
{
    public final long unixTimeMs;
    private final byte value;

    public boolean getLocationLock()
    {
        return (value & 0x01) > 0;
    }

    public boolean getHeadingLock()
    {
        return (value & 0x02) > 0;
    }

    public boolean getElevationLock()
    {
        return (value & 0x04) > 0;
    }

    public boolean getScaleLock()
    {
        return (value & 0x08) > 0;
    }

    public boolean getDriftLock()
    {
        return (value & 0x10) > 0;
    }

    public boolean getInsideBuilding()
    {
        return (value & 0x20) > 0;
    }

    public int getNavLockBars() {
        int navLockLevel = 0;
        if (getLocationLock())
        {
            navLockLevel=1;
            if (getHeadingLock())
            {
                navLockLevel=2;
                if (getScaleLock())
                {
                    navLockLevel=3;
                    if (getDriftLock())
                    {
                        navLockLevel=4;
                    }
                }
            }
        }

        return navLockLevel;
    }

    public NavLockEvent(long unixTimeMs, byte lockValue)
    {
        this.unixTimeMs = unixTimeMs;
        this.value = lockValue;
    }

    private NavLockEvent(Parcel in)
    {
        unixTimeMs = in.readLong();
        value = in.readByte();
    }

    @Override
    public String getKey() {
        return NeonEventType.NAV_LOCK.name();
    }

    @Override
    public NeonEventType getEventType() {
        return NeonEventType.NAV_LOCK;
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<NavLockEvent> CREATOR = new Creator<NavLockEvent>() {
        @Override
        public NavLockEvent createFromParcel(Parcel in) {
            return new NavLockEvent(in);
        }

        @Override
        public NavLockEvent[] newArray(int size) {
            return new NavLockEvent[size];
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
        dest.writeByte(value);
    }

    public String toString()
    {
        return ("Time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", ") +
                "Location Lock: " + getLocationLock() +
                "; Heading Lock: " + getHeadingLock() +
                "; Elevation Lock: " + getElevationLock() +
                "; Scale Lock: " + getScaleLock() +
                "; Drift Lock: " + getDriftLock() +
                "; InsideBuilding: " + getInsideBuilding();
    }
}
