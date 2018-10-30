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
public class ManualConstraint implements Parcelable {

    private final long timestamp;
    public final double Latitude;
    public final double Longitude;
    public final float LocationError;
    public final ElevationInfo Elevation;
    public final boolean Broadcast;

    /**
     * Creates a manual user correction at current time.
     *
     * @param latitude WGS-84 Latitude
     * @param longitude WGS-84 Longitude
     * @param locationError Location Certainty / Error in meters
     * @param elevation Elevation information
     */
    public ManualConstraint(double latitude, double longitude, float locationError, ElevationInfo elevation)
    {
        this(System.currentTimeMillis(), latitude, longitude, locationError, elevation, false);
    }

    /**
     * Creates a manual user correction.
     *
     * @param unixTimeMs Unix time in ms
     * @param latitude WGS-84 Latitude
     * @param longitude WGS-84 Longitude
     * @param locationError Location Certainty / Error in meters
     * @param elevation Elevation information
     */
    public ManualConstraint(long unixTimeMs, double latitude, double longitude, float locationError, ElevationInfo elevation)
    {
        this(unixTimeMs, latitude, longitude, locationError, elevation, false);
    }

    /**
     * Creates a manual user correction.
     *
     * @param unixTimeMs Unix time in ms
     * @param latitude WGS-84 Latitude
     * @param longitude WGS-84 Longitude
     * @param locationError Location Certainty / Error in meters
     * @param elevation Elevation information
     * @param broadcast Broadcast / Share data over UWB network
     */
    public ManualConstraint(long unixTimeMs, double latitude, double longitude, float locationError, ElevationInfo elevation, boolean broadcast)
    {
        timestamp = SystemClock.elapsedRealtime() - System.currentTimeMillis() + unixTimeMs;
        Latitude = latitude;
        Longitude = longitude;
        LocationError = locationError;
        Elevation = elevation;
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
    public static final Creator<ManualConstraint> CREATOR = new Creator<ManualConstraint>() {
        @Override
        public ManualConstraint createFromParcel(Parcel in) {
            return new ManualConstraint(in);
        }

        @Override
        public ManualConstraint[] newArray(int size) {
            return new ManualConstraint[size];
        }
    };

    private ManualConstraint(Parcel in)
    {
        timestamp = in.readLong();
        Latitude = in.readDouble();
        Longitude = in.readDouble();
        LocationError = in.readFloat();
        Elevation = new ElevationInfo(in);
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
        Elevation.writeToParcel(dest, flags);
        dest.writeByte(Broadcast ? (byte)1 : (byte)0);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Manual Constraint: ");
        sb.append("Time: ");
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date(getTimeUTCMillis())));

        sb.append(" Location: (" + Latitude + ", " + Longitude +")");
        sb.append(" Location Error: " + LocationError + " m");
        sb.append(" " + Elevation);
        sb.append(" Broadcast: " + Broadcast);

        return sb.toString();
    }
}
