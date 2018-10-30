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
import java.util.HashMap;
import java.util.UUID;

/**
 * A Neon Building is identified with a UUID and contains a collection of building floors,
 * each with an outline and potentially a floor plan image
 * @author Travis
 *
 */
public class NeonBuilding implements Parcelable {

    /**
     * UUID that uniquely identifies this building
     */
    private final UUID ID;

    /**
     * Friendly name for the building
     */
    private final String Name;

    /**
     * List of building floors contained in the building
     * The array index DOES NOT correspond to the zero-based index
     * floor number.  That mapping is achieved from the floorMap
     */
    private final ArrayList<NeonBuildingFloor> Floors;

    /**
     * Mapping from zero-based floor number to array index
     * Floors are retrieved using the floor number with
     * getFloor(int floorNumber)
     *
     * The ground floor is index 0, basement floors have negative indices,
     * and above-ground floors have positive indices
     */
    private final HashMap<Integer, Integer> floorMap;

    public NeonBuilding(UUID id, String name, ArrayList<NeonBuildingFloor> floors)
    {
        this.ID = id;
        this.Name = name;
        this.Floors = new ArrayList<>(floors);

        /**
         * Construct the floor mapping
         */
        int i = 0;
        floorMap = new HashMap<>();
        for (NeonBuildingFloor bf : this.Floors) {
            floorMap.put(bf.getFloorNumber(), i++);
        }
    }

    /**
     * Get unique identifier for this building
     */
    public UUID getID() {
        return ID;
    }

    /**
     * Get friendly name for this building.
     * Does not have to be unique or non-empty
     * If null, returns part of the UUID instead
     */
    public String getName() {
        if(Name == null)
            return ID.toString().substring(0,8);
        else
            return Name;
    }

    /**
     * Get a collection of all floors in the building
     * Use this to iterate through all floors in the building
     */
    public ArrayList<NeonBuildingFloor> getFloors() {
        return new ArrayList<>(Floors);
    }

    /**
     * Retrieve a specific floor by floor number
     *
     * @param floorNumber The floor number to retrieve.  The ground floor is index 0,
     *                    below-ground floors have negative indices,
     *                    and above-ground floors have positive indices
     * @return A NeonBuildingFloor, or null if that floorNumber doesn't exist
     */
    public NeonBuildingFloor getFloor(int floorNumber) {
        Integer mapKey = floorMap.get(floorNumber);
        if (mapKey == null)
            return null;

        return Floors.get(mapKey);
    }

    /**
     * Used for sending data across binders
     */
    private NeonBuilding(Parcel in)
    {
        ID = UUID.fromString(in.readString());
        Name = in.readString();
        Floors = in.createTypedArrayList(NeonBuildingFloor.CREATOR);

        int i = 0;
        floorMap = new HashMap<>();
        for (NeonBuildingFloor bf : this.Floors) {
            floorMap.put(bf.getFloorNumber(), i++);
        }
    }

    public static final Creator<NeonBuilding> CREATOR = new Creator<NeonBuilding>() {
        @Override
        public NeonBuilding createFromParcel(Parcel in) {
            return new NeonBuilding(in);
        }

        @Override
        public NeonBuilding[] newArray(int size) {
            return new NeonBuilding[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID.toString());
        dest.writeString(Name);
        dest.writeTypedList(Floors);
    }
}
