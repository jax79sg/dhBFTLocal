package com.trx.neon.beta;

import android.os.Parcel;
import android.os.Parcelable;

import com.trx.neon.model.event.NeonEventConstants;

import java.util.Date;

/**
 * Created by Dan on 1/26/2016.
 */
public class UserActivityEvent  implements Parcelable, INeonEventBeta
{
    public final long startTimeMs;
    public final long endTimeMs;
    public final String type;

    public UserActivityEvent(long startTimeMs, long endTimeMs, String type)
    {
        this.startTimeMs = startTimeMs;
        this.endTimeMs = endTimeMs;
        this.type = type;
    }

    private UserActivityEvent(Parcel in)
    {
        this.startTimeMs = in.readLong();
        this.endTimeMs = in.readLong();
        this.type = in.readString();
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<UserActivityEvent> CREATOR = new Creator<UserActivityEvent>() {
        @Override
        public UserActivityEvent createFromParcel(Parcel in) {
            return new UserActivityEvent(in);
        }

        @Override
        public UserActivityEvent[] newArray(int size) {
            return new UserActivityEvent[size];
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
        return ("Start time: " + NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(startTimeMs))
                + ", End time: " + ((endTimeMs > 0)? NeonEventConstants.DEFAULT_DATE_FORMAT.format(new Date(endTimeMs)): "none")
                + ", " + "Type: " + type);
    }

}
