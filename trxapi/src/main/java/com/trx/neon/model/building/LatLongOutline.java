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

import java.util.ArrayList;

/**
 * An outline defined as a list of Latitude-Longitude pairs that make up an outer hull and a set of inner holes.
 * Comparable to PolygonWithHoles
 */
public class LatLongOutline implements Parcelable
{
    /**
     * A set of Latitude-Longitude points that form an outer outline
     */
    public final ArrayList<LatLong> Hull;

    /**
     * A set of Latitude-Longitude points inside the hull that form interior holes in the outline
     */
    public final ArrayList<ArrayList<LatLong>> Holes;

    public LatLongOutline(ArrayList<LatLong> hull, ArrayList<ArrayList<LatLong>> holes)
    {
        Hull = new ArrayList<>(hull);
        Holes = new ArrayList<>(holes);
    }

    /**
     * Used for sending data across binders
     */
    private LatLongOutline(Parcel in)
    {
        Hull = new ArrayList<>();
        in.readTypedList(Hull, LatLong.CREATOR);
        int size = in.readInt();
        Holes = new ArrayList<>();
        for(int i = 0; i<size; i++)
        {
            ArrayList<LatLong> temp = new ArrayList<>();
            in.readTypedList(temp, LatLong.CREATOR);
            Holes.add(temp);
        }
    }

    public static final Creator<LatLongOutline> CREATOR = new Creator<LatLongOutline>() {
        @Override
        public LatLongOutline createFromParcel(Parcel in) {
            return new LatLongOutline(in);
        }

        @Override
        public LatLongOutline[] newArray(int size) {
            return new LatLongOutline[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeTypedList(Hull);
        dest.writeInt(Holes.size());
        for(ArrayList<LatLong> hole : Holes)
            dest.writeTypedList(hole);
    }
}
