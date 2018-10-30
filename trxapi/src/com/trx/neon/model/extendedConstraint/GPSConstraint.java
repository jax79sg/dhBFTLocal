package com.trx.neon.model.extendedConstraint;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by John on 7/1/2016.
 */
public class GPSConstraint implements Parcelable {

    private final long timestamp;
    public final double Latitude;
    public final double Longitude;
    public final float LocationError;
    public final boolean Broadcast;

    /**
     * Creates a GPS constraint
     * @param unixTimeMs Unix time in ms
     * @param latitude WGS-84 Latitude
     * @param longitude WGS-84 Longitude
     * @param locationError Location Certainty / Error in meters
     */
    public GPSConstraint(long unixTimeMs, double latitude, double longitude, float locationError)
    {
        this(unixTimeMs, latitude, longitude, locationError, false);
    }

    /**
     * Creates a GPS constraint
     * @param unixTimeMs Unix time in ms
     * @param latitude WGS-84 Latitude
     * @param longitude WGS-84 Longitude
     * @param locationError Location Certainty / Error in meters
     * @param broadcast Broadcast / Share data over UWB network
     */
    public GPSConstraint(long unixTimeMs, double latitude, double longitude, float locationError, boolean broadcast)
    {
        timestamp = SystemClock.elapsedRealtime() - System.currentTimeMillis() + unixTimeMs;
        Latitude = latitude;
        Longitude = longitude;
        LocationError = locationError;
        Broadcast = broadcast;
    }

    /**
     * Retrieves the timestamp of this constraint.  This value is directly comparable with System.currentTimeMillis()
     * @return
     */
    public long getTimeUTCMillis()
    {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime() + timestamp;
    }

    /**
     * Retrieves the timestamp of this constraint.  This value is directly comparable with SystemClock.elapsedRealTime()
     * @return
     */
    public long getElapsedRealtimeMillis()
    {
        return timestamp;
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<GPSConstraint> CREATOR = new Creator<GPSConstraint>() {
        @Override
        public GPSConstraint createFromParcel(Parcel in) {
            return new GPSConstraint(in);
        }

        @Override
        public GPSConstraint[] newArray(int size) {
            return new GPSConstraint[size];
        }
    };

    private GPSConstraint(Parcel in)
    {
        timestamp = in.readLong();
        Latitude = in.readDouble();
        Longitude = in.readDouble();
        LocationError = in.readFloat();
        Broadcast = in.readByte() > 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timestamp);
        dest.writeDouble(Latitude);
        dest.writeDouble(Longitude);
        dest.writeFloat(LocationError);
        dest.writeByte(Broadcast ? (byte)1 : (byte)0);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("GPS Constraint: ");
        sb.append("Time: ");
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date(getTimeUTCMillis())));

        sb.append(" Location: (" + Latitude + ", " + Longitude +")");
        sb.append(" Location Error: " + LocationError + " m");
        sb.append(" Broadcast: " + Broadcast);

        return sb.toString();
    }
}