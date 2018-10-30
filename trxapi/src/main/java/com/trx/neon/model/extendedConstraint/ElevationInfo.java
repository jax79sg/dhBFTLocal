package com.trx.neon.model.extendedConstraint;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John on 7/1/2016.
 */
public class ElevationInfo implements Parcelable {

    public enum ElevationType {
        None,
        Floor,
        OnTerrain,
        Altitude,
        Underground,
        UndergroundFloor
    }

    public final ElevationType Type;
    public final double Value;

    private ElevationInfo(ElevationType type, double value) {
        Type = type;
        Value = value;
    }

    /**
     * Sets altitude to a particular floor
     * @param floor
     * @return
     */
    public static ElevationInfo OnFloor(int floor) {
        return new ElevationInfo(ElevationType.Floor, floor);
    }

    /**
     * Sets altitude to the terrain
     * @return
     */
    public static ElevationInfo OnTerrain() {
        return new ElevationInfo(ElevationType.OnTerrain, 0);
    }

    /**
     * Sets altitude at a particular altitude with error bounds equal to the 2D error radius
     * @param altitude
     * @return
     */
    public static ElevationInfo AtAltitude(float altitude) {
        return new ElevationInfo(ElevationType.Altitude, altitude);
    }

    /**
     * Sets the altitude as underground, no other information is given
     * @return
     */
    public static ElevationInfo Undergound() {
        return new ElevationInfo(ElevationType.Underground, 0);
    }

    /**
     * Sets the altitude as underground and on a particular floor
     * @param floor
     * @return
     */
    public static ElevationInfo UndergroundOnFloor(int floor) {
        return new ElevationInfo(ElevationType.UndergroundFloor, floor);
    }

    /**
     * No elevation information is known
     * @return
     */
    public static ElevationInfo None() {
        return new ElevationInfo(ElevationType.None, 0);
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<ElevationInfo> CREATOR = new Creator<ElevationInfo>() {
        @Override
        public ElevationInfo createFromParcel(Parcel in) {
            return new ElevationInfo(in);
        }

        @Override
        public ElevationInfo[] newArray(int size) {
            return new ElevationInfo[size];
        }
    };

    ElevationInfo(Parcel in)
    {
        Type = ElevationType.values()[in.readInt()];
        Value = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Type.ordinal());
        dest.writeDouble(Value);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(Type.toString());
        if (Type == ElevationType.Floor || Type == ElevationType.UndergroundFloor) {
            sb.append(": ");
            sb.append(Value);
        } else if (Type == ElevationType.Altitude) {
            sb.append(": ");
            sb.append(Value);
            sb.append(" m");
        }
        return sb.toString();
    }
}
