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
public class RangingConstraint implements Parcelable{

    public enum RangeType {
        RSSI,
        UWB,
        TOF
    }

    private final long timestamp;
    public final double Latitude;
    public final double Longitude;
    public final float LocationError;
    public final ElevationInfo Elevation;
    public final float Range;
    public final RangeType Type;
    public final boolean Broadcast;

    /**
     * Creates a ranging constraint at current time
     *
     * @param latitude WGS-84 Latitude
     * @param longitude WGS-84 Longitude
     * @param locationError Location Certainty / Error in meters
     * @param elevation Elevation information
     * @param range Range in meters
     * @param type Range type - UWB / ToF / RSSI
     */
    public RangingConstraint(double latitude, double longitude, float locationError, ElevationInfo elevation, float range, RangeType type)
    {
        this(System.currentTimeMillis(), latitude, longitude, locationError, elevation, range, type, false);
    }

    /**
     * Creates a ranging constraint
     *
     * @param unixTimeMs Unix time in ms
     * @param latitude WGS-84 Latitude
     * @param longitude WGS-84 Longitude
     * @param locationError Location Certainty / Error in meters
     * @param elevation Elevation information
     * @param range Range in meters
     * @param type Range type - UWB / ToF / RSSI
     */
    public RangingConstraint(long unixTimeMs, double latitude, double longitude, float locationError, ElevationInfo elevation, float range, RangeType type)
    {
        this(unixTimeMs, latitude, longitude, locationError, elevation, range, type, false);
    }

    /**
     * Creates a ranging constraint
     *
     * @param unixTimeMs Unix time in ms
     * @param latitude WGS-84 Latitude
     * @param longitude WGS-84 Longitude
     * @param locationError Location Certainty / Error in meters
     * @param elevation Elevation information
     * @param range Range in meters
     * @param type Range type - UWB / ToF / RSSI
     * @param broadcast Broadcast / Share data over UWB network
     */
    public RangingConstraint(long unixTimeMs, double latitude, double longitude, float locationError, ElevationInfo elevation, float range, RangeType type, boolean broadcast)
    {
        timestamp = SystemClock.elapsedRealtime() - System.currentTimeMillis() + unixTimeMs;
        Latitude = latitude;
        Longitude = longitude;
        LocationError = locationError;
        Elevation = elevation;
        Range = range;
        Type = type;
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
    public static final Creator<RangingConstraint> CREATOR = new Creator<RangingConstraint>() {
        @Override
        public RangingConstraint createFromParcel(Parcel in) {
            return new RangingConstraint(in);
        }

        @Override
        public RangingConstraint[] newArray(int size) {
            return new RangingConstraint[size];
        }
    };

    private RangingConstraint(Parcel in)
    {
        timestamp = in.readLong();
        Latitude = in.readDouble();
        Longitude = in.readDouble();
        LocationError = in.readFloat();
        Elevation = new ElevationInfo(in);
        Range = in.readFloat();
        Type = RangeType.values()[in.readInt()];
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
        dest.writeFloat(Range);
        dest.writeInt(Type.ordinal());
        dest.writeByte(Broadcast ? (byte)1 : (byte)0);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Ranging Constraint: ");
        sb.append("Time: ");
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date(getTimeUTCMillis())));

        sb.append(" Location: (" + Latitude + ", " + Longitude +")");
        sb.append(" Location Error: " + LocationError + " m");
        sb.append(" " + Elevation);
        sb.append(" Range: " + Range + " m");
        sb.append(" Type: " + Type);
        sb.append(" Broadcast: " + Broadcast);

        return sb.toString();
    }
}
