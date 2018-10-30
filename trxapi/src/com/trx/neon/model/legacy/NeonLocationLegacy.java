package com.trx.neon.model.legacy;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * DON'T EVER USE THIS, LEGACY PURPOSES ONLY
 */
@Deprecated
public class NeonLocationLegacy implements Parcelable
{
	@Deprecated
	public final int serialNumber;
	@Deprecated
	public final int sessionID;
	@Deprecated
	public final long unixTimeMs;
	@Deprecated
	public final int iteration;
	@Deprecated
	public final double longitude;
	@Deprecated
	public final double latitude;
	@Deprecated
	public final float heading;
	@Deprecated
	public final float headingError;
	@Deprecated
	public final double errorLongitude;
	@Deprecated
	public final double errorLatitude;
	@Deprecated
	public final float errorRadius;
	@Deprecated
	public final Float floor;
	@Deprecated
	public final Float floorError;
	@Deprecated
	public final float altitude;
	@Deprecated
	public final float altitudeError;
	@Deprecated
	public NeonLocationLegacy(int serial, int session, long unixTimeMs, int navIter,
							  double longitude, double latitude, float heading, float headingError,
							  double errorLongitude, double errorLatitude, float errorRadius,
							  Float floor, Float floorError, float altitude, float altitudeError)
	{
		this.serialNumber = serial;
		this.sessionID = session;
		this.unixTimeMs = unixTimeMs;
		this.iteration = navIter;
		this.longitude = longitude;
		this.latitude = latitude;
		this.heading = heading;
		this.headingError = headingError;

		this.errorLongitude = errorLongitude;
		this.errorLatitude = errorLatitude;
		this.errorRadius = errorRadius;

		this.floor = floor;
		this.floorError = floorError;
		this.altitude = altitude;
		this.altitudeError = altitudeError;
	}
	@Deprecated
	public NeonLocationLegacy(NeonLocationLegacy loc)
	{
		this.serialNumber = loc.serialNumber;
		this.sessionID = loc.sessionID;
		this.unixTimeMs = loc.unixTimeMs;

		this.iteration = loc.iteration;
		this.longitude = loc.longitude;
		this.latitude = loc.latitude;
		this.heading = loc.heading;
		this.headingError = loc.headingError;

		this.errorLongitude = loc.errorLongitude;
		this.errorLatitude = loc.errorLatitude;
		this.errorRadius = loc.errorRadius;

		this.floor = loc.floor;
		this.floorError = loc.floorError;
		this.altitude = loc.altitude;
		this.altitudeError = loc.altitudeError;
	}

	@Deprecated
	public static final Creator<NeonLocationLegacy> CREATOR = new Creator<NeonLocationLegacy>() {
		@Override
		public NeonLocationLegacy createFromParcel(Parcel in) {
			return null;
		}

		@Override
		public NeonLocationLegacy[] newArray(int size) {
			return new NeonLocationLegacy[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(serialNumber);
		dest.writeInt(sessionID);
		dest.writeLong(unixTimeMs);
		dest.writeInt(iteration);
		dest.writeDouble(longitude);
		dest.writeDouble(latitude);
		dest.writeFloat(heading);
		dest.writeFloat(headingError);
		
		dest.writeDouble(errorLongitude);
		dest.writeDouble(errorLatitude);
		dest.writeFloat(errorRadius);
		
		dest.writeByte((byte) (floor != null ? 1 : 0));
		if (floor != null)
			dest.writeFloat(floor);
		dest.writeByte((byte) (floorError != null ? 1 : 0));
		if (floorError != null)
			dest.writeFloat(floorError);
		dest.writeFloat(altitude);
		dest.writeFloat(altitudeError);
	}
}
