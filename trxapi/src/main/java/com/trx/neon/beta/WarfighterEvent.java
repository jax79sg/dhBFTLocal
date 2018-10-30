package com.trx.neon.beta;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jeffrey on 7/20/2016.
 */
public class WarfighterEvent implements Parcelable, INeonEventBeta {
    public final int x, y, heading;
    public final long time;
    public final int id;

    public WarfighterEvent(int x, int y, int heading, long time, int id) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.time = time;
        this.id = id;
    }

    private WarfighterEvent(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        heading = in.readInt();
        time = in.readLong();
        id = in.readInt();
    }

    public static final Creator<WarfighterEvent> CREATOR = new Creator<WarfighterEvent>() {
        @Override
        public WarfighterEvent createFromParcel(Parcel in) {
            return new WarfighterEvent(in);
        }

        @Override
        public WarfighterEvent[] newArray(int size) {
            return new WarfighterEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(heading);
        dest.writeLong(time);
        dest.writeInt(id);
    }
}
