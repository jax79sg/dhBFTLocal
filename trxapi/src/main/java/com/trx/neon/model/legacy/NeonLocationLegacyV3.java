package com.trx.neon.model.legacy;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Deprecated
public class NeonLocationLegacyV3 implements Parcelable
{
	@Deprecated	public final int serialNumber;
	@Deprecated	public final int sessionID;
	@Deprecated	public final long unixTimeMs;
	@Deprecated	public final int iteration;
	@Deprecated	public final double longitude;
	@Deprecated	public final double latitude;
	@Deprecated public final float heading;
	@Deprecated	public final float headingError;
	@Deprecated	public final double errorLongitude;
	@Deprecated	public final double errorLatitude;
	@Deprecated	public final float errorRadius;
	@Deprecated	public final Float floor;
	@Deprecated	public final Float floorError;
	@Deprecated	public final float altitude;
	@Deprecated	public final boolean underground;
	@Deprecated	public final float altitudeError;
	@Deprecated public final byte batteryHH;
	@Deprecated public final byte batteryTU;

	@Deprecated public NeonLocationLegacyV3(int serial, int session, long unixTimeMs, int navIter,
                                            double longitude, double latitude, float heading, float headingError,
                                            double errorLongitude, double errorLatitude, float errorRadius,
                                            Float floor, Float floorError, float altitude, float altitudeError, boolean underground,
											byte batteryAndroid, byte batteryTrackingUnit)
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

		this.underground = underground;
		this.batteryHH = batteryAndroid;
		this.batteryTU = batteryTrackingUnit;
	}

	@Deprecated	public NeonLocationLegacyV3(NeonLocationLegacyV3 loc)
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

		this.underground = loc.underground;
		this.batteryHH = loc.batteryHH;
		this.batteryTU = loc.batteryTU;
	}

	@Deprecated	/** A convenience method to quickly integrate NeonLocations into applications that use the android.location.Location type. */
	public Location toLocation()
	{
		return toLocation("Neon");
	}

	@Deprecated	/** A convenience method to quickly integrate NeonLocations into applications that use the android.location.Location type. */
	private Location toLocation(String providerName)
	{
		Location loc = new Location(providerName);
		//68% confidence circle in Android.  Our definition of error radius is very similar
		//Do note however, that we do not necessarily center our error radius at the user.
		//Android's Location type does not have support for this.
		loc.setAccuracy(errorRadius);
		//Meters above sea level.  Our definition is meters above WGS84 ellipsoid, which is very similar
		loc.setAltitude(altitude);
		//Degrees east (clockwise) of true north.  Our definition is the same
		loc.setBearing(heading);
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		loc.setTime(unixTimeMs);
		return loc;
	}

	//
	// Function to serialize and deserialize the class
	//

	/**
	 * Used for sending data across binders
	 */
	@Deprecated	public static final Creator<NeonLocationLegacyV3> CREATOR = new Creator<NeonLocationLegacyV3>() {
		@Override
		public NeonLocationLegacyV3 createFromParcel(Parcel in) {
			return new NeonLocationLegacyV3(in);
		}

		@Override
		public NeonLocationLegacyV3[] newArray(int size) {
			return new NeonLocationLegacyV3[size];
		}
	};

	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	@Deprecated	private NeonLocationLegacyV3(Parcel in)
	{
		serialNumber = in.readInt();
		sessionID = in.readInt();
		unixTimeMs = in.readLong();

		iteration = in.readInt();

		longitude = in.readDouble();
		latitude = in.readDouble();
		heading = in.readFloat();
		headingError = in.readFloat();

		errorLongitude = in.readDouble();
		errorLatitude = in.readDouble();
		errorRadius = in.readFloat();

		floor = in.readByte() > 0 ? in.readFloat() : null;
		floorError = in.readByte() > 0 ? in.readFloat() : null;

		altitude = in.readFloat();
		altitudeError = in.readFloat();

		underground = in.readByte() > 0;
		batteryHH = in.readByte();
		batteryTU = in.readByte();
	}

	@Override
	@Deprecated	public int describeContents() {
		return 0;
	}

	@Override
	@Deprecated	public void writeToParcel(Parcel dest, int flags) {
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

		dest.writeByte(underground ? (byte)1 : (byte)0);
		dest.writeByte(batteryHH);
		dest.writeByte(batteryTU);
	}

	//
	// Overridden to improve toString() functionality
	//

	@Deprecated	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

	@Override
	@Deprecated	public String toString()
	{
		String sb = ("SerialNumber: " + serialNumber + ", ") +
				"Session: " + sessionID + ", " +
				"Time: " + NeonLocationLegacyV3.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", " +
				"Iteration: " + iteration + ", " +
				"Longitude: " + longitude + ", " +
				"Latitude: " + latitude + ", " +
				"Heading: " + heading + ", " +
				"HeadingError: " + headingError + ", " +
				"ErrorLongitude: " + errorLongitude + ", " +
				"ErrorLatitude: " + errorLatitude + ", " +
				"ErrorRadius: " + errorRadius + ", " +
				"Floor: " + floor + ", " +
				"FloorError: " + floorError + ", " +
				"Altitude: " + altitude + ", " +
				"AltitudeError: " + altitudeError + ", " +
				"Underground: " + underground +
				"BatteryHH: " + batteryHH + ", " +
				"BatteryTU: " + batteryTU;

		return sb;
	}
}
