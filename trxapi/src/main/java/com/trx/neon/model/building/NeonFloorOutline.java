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
import java.util.List;

/**
 * A Neon floor outline is composed of a list of LatLongOutlines.  LatLongOutlines
 * are formed from an outer hull and a list of interior holes in that outline.
 * A building floor can therefore contain multiple, distinct outlines, each with a hull and a set of
 * holes
 */
public class NeonFloorOutline implements Parcelable
{
    /**
     * A list of LatLongOutlines
     */
    public final List<LatLongOutline> Outlines;

    public NeonFloorOutline(List<LatLongOutline> outlines)
    {
        Outlines = new ArrayList<>(outlines);
    }

    NeonFloorOutline(NeonFloorOutline other)
    {
        Outlines = other.Outlines;
    }

    /**
     * Used for sending data across binders
     */
    NeonFloorOutline(Parcel in)
    {
        Outlines = new ArrayList<>();
        in.readTypedList(Outlines, LatLongOutline.CREATOR);
    }

    public static final Creator<NeonFloorOutline> CREATOR = new Creator<NeonFloorOutline>() {
        @Override
        public NeonFloorOutline createFromParcel(Parcel in) {
            return new NeonFloorOutline(in);
        }

        @Override
        public NeonFloorOutline[] newArray(int size) {
            return new NeonFloorOutline[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(Outlines);
    }
}
