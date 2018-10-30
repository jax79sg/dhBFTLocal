package com.trx.neon.model.config;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This policy defines a time slicing mechanism.
 * Specifying an interval of 0 completely disables functionality.
 * Specifying a delay of 0 enables continuous functionality
 * Both must be non negative, and at least one must be positive.
 */
public class TimeSlicePolicy implements Parcelable
{
    public final long interval; //Active window length, milliseconds
    public final long delay;    //Gap between active windows, milliseconds

    public TimeSlicePolicy(long interval, long delay)
    {
        if (interval < 0)
            throw new IllegalArgumentException("Interval must be >= 0");
        if (delay < 0)
            throw new IllegalArgumentException("Delay must be >= 0");
        this.interval = interval;
        this.delay = delay;

        if (!this.canPoll() && !this.isTimeSliced())
        {
            throw new IllegalArgumentException("Either interval or delay must be > 0");
        }
    }

    public boolean canPoll()
    {
        return this.interval > 0;
    }

    public boolean isTimeSliced()
    {
        return this.delay > 0;
    }

    public String toString()
    {
        return "Interval: " + interval + " Delay: " + delay;
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<TimeSlicePolicy> CREATOR = new Creator<TimeSlicePolicy>() {
        @Override
        public TimeSlicePolicy createFromParcel(Parcel in) {
            return new TimeSlicePolicy(in);
        }

        @Override
        public TimeSlicePolicy[] newArray(int size) {
            return new TimeSlicePolicy[size];
        }
    };

    /**
     * Constructor for data serialized over the binder
     * @param in
     */
    private TimeSlicePolicy(Parcel in)
    {
        this.interval = in.readLong();
        this.delay = in.readLong();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(this.interval);
        dest.writeLong(this.delay);
    }
}