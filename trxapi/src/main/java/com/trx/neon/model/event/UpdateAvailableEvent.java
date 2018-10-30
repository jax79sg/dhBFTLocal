package com.trx.neon.model.event;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The UpdateAvailableEvent indicates that an update of the Neon Location Services is available
 * The Neon Location Services will not be able to update environmental data until the upgrade
 * is completed.  The client application can allow the user to trigger the upgrade with
 * Neon.upgradeNeonLocationServices
 * @author Dan
 */
public class UpdateAvailableEvent implements Parcelable, INeonEvent
{
    public enum UpdateAvailableEventType
    {
        APPLICATION,
        FIRMWARE
    }

    private final String type;

    public UpdateAvailableEventType getType()
    {
        try
        {
            return Enum.valueOf(UpdateAvailableEventType.class, type);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }

    public UpdateAvailableEvent(UpdateAvailableEventType type)
    {
        this.type = type.name();
    }

    private UpdateAvailableEvent(Parcel in)
    {
        type = in.readString();
    }

    public String getKey()
    {
        return NeonEventType.UPDATE_AVAILABLE.name();
    }

    public NeonEventType getEventType()
    {
        return NeonEventType.UPDATE_AVAILABLE;
    }

    /*
     * Used for sending data across binders
     */
    public static final Creator<UpdateAvailableEvent> CREATOR = new Creator<UpdateAvailableEvent>() {
        @Override
        public UpdateAvailableEvent createFromParcel(Parcel in) {
            return new UpdateAvailableEvent(in);
        }

        @Override
        public UpdateAvailableEvent[] newArray(int size) {
            return new UpdateAvailableEvent[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(type);
    }

    public String toString()
    {
        return "Update Available";
    }
}
