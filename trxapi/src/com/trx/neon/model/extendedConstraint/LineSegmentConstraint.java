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
public class LineSegmentConstraint implements Parcelable {

    public enum SegmentStatus {
        StartSegment,
        EndSegment
    }

    private final long timestamp;
    public final double StartLatitude;
    public final double StartLongitude;
    public final double EndLatitude;
    public final double EndLongitude;
    public final float LocationError;
    public final ElevationInfo Elevation;
    public final SegmentStatus Status;

    /**
     * Creates a Line Segment constraint, used for heading and position.
     * Must create a secondary constraint ending the segment
     *
     * @param unixTimeMs Unix time in ms
     * @param startLatitude WGS-84 Latitude for start location
     * @param startLongitude WGS-84 Longitude for start location
     * @param endLatitude WGS-84 Latitude for end location
     * @param endLongitude WGS-84 Longitude for end location
     * @param locationError Location Certainty / Error in meters
     * @param elevation Elevation information
     * @param status Start / Stop segment flag
     */
    public LineSegmentConstraint(long unixTimeMs, double startLatitude, double startLongitude,
                                 double endLatitude, double endLongitude, float locationError,
                                 ElevationInfo elevation, SegmentStatus status)
    {
        timestamp = SystemClock.elapsedRealtime() - System.currentTimeMillis() + unixTimeMs;
        StartLatitude = startLatitude;
        StartLongitude = startLongitude;
        EndLatitude = endLatitude;
        EndLongitude = endLongitude;
        LocationError = locationError;
        Elevation = elevation;
        Status = status;
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
    public static final Creator<LineSegmentConstraint> CREATOR = new Creator<LineSegmentConstraint>() {
        @Override
        public LineSegmentConstraint createFromParcel(Parcel in) {
            return new LineSegmentConstraint(in);
        }

        @Override
        public LineSegmentConstraint[] newArray(int size) {
            return new LineSegmentConstraint[size];
        }
    };

    private LineSegmentConstraint(Parcel in)
    {
        timestamp = in.readLong();
        StartLatitude = in.readDouble();
        StartLongitude = in.readDouble();
        EndLatitude = in.readDouble();
        EndLongitude = in.readDouble();
        LocationError = in.readFloat();
        Elevation = new ElevationInfo(in);
        Status = SegmentStatus.values()[in.readInt()];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timestamp);
        dest.writeDouble(StartLatitude);
        dest.writeDouble(StartLongitude);
        dest.writeDouble(EndLatitude);
        dest.writeDouble(EndLongitude);
        dest.writeFloat(LocationError);
        Elevation.writeToParcel(dest, flags);
        dest.writeInt(Status.ordinal());
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Manual Constraint: ");
        sb.append("Time: ");
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date(getTimeUTCMillis())));

        sb.append(" Start Location: (" + StartLatitude + ", " + StartLongitude +")");
        sb.append(" End Location: (" + EndLatitude + ", " + EndLongitude +")");
        sb.append(" Location Error: " + LocationError + " m");
        sb.append(" " + Elevation);
        sb.append(" Segment Status: " + Status);

        return sb.toString();
    }
}