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
 * A Neon Building Floor is identified by a zero-based floorNumber, and contains
 * a building outline and potentially a floorplan ID and georeferencing information
 * for that image
 * @author Travis
 *
 */
public class NeonBuildingFloor implements Parcelable
{
	/**
	 * The floor number in the containing building
	 *
	 * The ground floor is index 0, basement floors have negative indices,
	 * and above-ground floors have positive indices
	 */
	private final int FloorNumber;

	/**
	 * A friendly label for the floor,
	 * such as "B1" or "C"
	 */
	private final String Label;

	/**
	 * An outline for this floor (can be different from other floors)
	 */
	private final NeonFloorOutline Outline;

	/**
	 * Set of LatLongs that georeference floor plan image
	 * Use Neon.getFloorPlan to retrieve the floor plan image
	 */
	private final ArrayList<LatLong> FloorPlanCorners;

	/**
	 * Unique ID for the floor plan image
	 * Use Neon.getFloorPlan to retrieve the floor plan image
	 */
	private final String FloorPlanImageID;

	public NeonBuildingFloor(
			int floorNumber,
			String label,
			NeonFloorOutline outline,
			ArrayList<LatLong> floorPlanCorners,
			String floorPlanImageID)
	{
		this.FloorNumber = floorNumber;
		this.Label = label;
		this.Outline = new NeonFloorOutline(outline);
		this.FloorPlanCorners = floorPlanCorners;
		this.FloorPlanImageID = floorPlanImageID;
	}

	public int getFloorNumber() {
		return FloorNumber;
	}

	public String getLabel() {
		return Label;
	}

	public NeonFloorOutline getOutline() {
		return Outline;
	}

	public String getFloorPlanImageID() {return FloorPlanImageID; }

	public ArrayList<LatLong> getFloorPlanCorners() {return FloorPlanCorners;}

	/**
	 * @return Whether the building floor has an associated floor plan image
	 */
	public boolean hasFloorPlan() {
		return (FloorPlanImageID != null);
	}

	/**
	 * Used for sending data across binders
	 */
	private NeonBuildingFloor(Parcel in)
	{
		FloorNumber = in.readInt();
		Label = in.readString();
		Outline = new NeonFloorOutline(in);
		FloorPlanCorners = new ArrayList<>();
		in.readTypedList(FloorPlanCorners, LatLong.CREATOR);
		FloorPlanImageID = in.readString();
	}

	public static final Creator<NeonBuildingFloor> CREATOR = new Creator<NeonBuildingFloor>() {
		@Override
		public NeonBuildingFloor createFromParcel(Parcel in) {
			return new NeonBuildingFloor(in);
		}

		@Override
		public NeonBuildingFloor[] newArray(int size) {
			return new NeonBuildingFloor[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(FloorNumber);
		dest.writeString(Label);
		Outline.writeToParcel(dest, flags);
		dest.writeTypedList(FloorPlanCorners);
		dest.writeString(FloorPlanImageID);
	}
}
