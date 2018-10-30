/******************************************************************************
 *
 * 	 Copyright 2017, TRX Systems, Inc.  All Rights Reserved.
 *
 *   TRX Systems, Inc.
 *   7500 Greenway Center Drive, Suite 420
 *   Greenbelt, Maryland  20770
 *
 *   Tel:    (301) 313-0053
 *   email:  info@trxsystems.com
 *
 *****************************************************************************/
package com.trx.neon.model.building;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Global location in degrees (WGS-84)
 */
public class LatLong implements Parcelable{
    /**
     * North-South Measurement in degrees
     */
    public final double Latitude;

    /**
     * East-West Measurement in degrees
     */
    public final double Longitude;

    public LatLong(double latitude, double longitude)
    {
        if (!(latitude >= -90 && latitude <= 90))
            throw new IllegalArgumentException("Latitude is not valid: " + latitude);

        if (!(longitude >= -180 && longitude <= 180))
            throw new IllegalArgumentException("Longitude is not valid: " + longitude);

        this.Latitude = latitude;
        this.Longitude = longitude;
    }

    @Override
    public String toString()
    {
        return this.Latitude + ", " + this.Longitude;
    }

    /**
     * Used for sending data across binders
     */
    public static final Creator<LatLong> CREATOR = new Creator<LatLong>() {
        @Override
        public LatLong createFromParcel(Parcel in) {
            return new LatLong(in);
        }

        @Override
        public LatLong[] newArray(int size) {
            return new LatLong[size];
        }
    };

    LatLong(Parcel in)
    {
        Latitude = in.readDouble();
        Longitude = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(Latitude);
        dest.writeDouble(Longitude);
    }
}


