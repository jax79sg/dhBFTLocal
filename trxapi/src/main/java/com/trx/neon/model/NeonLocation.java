package com.trx.neon.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


public class NeonLocation implements Parcelable
{
	/**
	 * The serial number represents the tracking device.
	 */
	public final int serialNumber;

	/**
	 * The session ID represents turn on to turn off of the tracking device.  A new session ID will be generated
	 * each time the tracking unit is restarted, or in the case of tracking on the phone itself, every time the
	 * NeonLocationService is bound and then fully unbound by all client applications.
	 */
	public final int sessionID;

	/**
	 * This timestamp represents the time of validity of the location.  It is a UTC time in milliseconds.
	 * (This is generally the same frame of reference as System.currentTimeMillis())
	 */
	public final long unixTimeMs;
	/**
	 * This number represents an internal iteration count within the NeonLocationServices.
	 * In the case of receiving two device locations with the same time stamp, this value should be used as a tie
	 * breaker.
	 *
	 * Of two NeonLocations with the same time stamp, the one with the higher iteration is a correction to
	 * the previously calculated position, based on the newly received constraint input data.
	 *
	 * Only systems that display paths/trails are likely to care about this number.
	 */
	public final int iteration;

	/**
	 * This represents the source of the location,
	 * example:
	 * NEON = fused with Tracker,
	 * GPS = Android GPD only no tracker used
	 */
	public final String type;

	/**
	 * The longitude of the user (degrees)
	 */
	public final double longitude;

	/**
	 * The latitude of the user (degrees)
	 */
	public final double latitude;

	/**
	 * The heading of the user (degrees)
	 *
	 * The heading provided is in the range (-inf, inf) - not limited to the range [0, 360).
	 * To obtain compass heading the user can perform a mod 360 operation.
	 *
	 * clockwise.
	 * 0 represents north.
	 * 90 represents east.
	 */
	public final float heading;

	/**
	 * The heading variance of the user (degrees)
	 *
	 * 0 represents low variance.
	 * 180 represents high variance.
	 */
	public final float headingError;

	/**
	 * The longitude of the center point of the computed error bounds on this position
	 * Note that this is not always the same as the estimate of the position itself.
	 */
	public final double errorLongitude;

	/**
	 * The latitude of the center point of the computed error bounds on this position
	 * Note that this is not always the same as the estimate of the position itself.
	 */
	public final double errorLatitude;

	/**
	 * The radius of the computed error bounds on this position (meters)
	 * Note that this is around the point specified by ErrorLongitude and ErrorLatitude
	 */
	public final float errorRadius;

	/**
	 * A nullable floor number estimate.  Null if the user is outside of any known buildings.
	 *
	 * 0 is the ground floor.
	 * -1 is the first basement level.
	 * 1 is the first floor above the ground floor.
	 * .5 is taking the stairs/elevator from the ground floor to the floor above.
	 */
	public final Float floor;

	/**
	 * A nullable floor number error estimate.  Null or extremely large values indicate that
	 * the user's floor number is unknown.
	 *
	 * The error should be read as a CEP bounds in either direction about the Floor.
	 * ie: With 50% probability, [Floor-FloorError, Floor+FloorError] contains the user's current floor number.
	 */
	public final Float floorError;

	/**
	 * An altitude estimate in meters above the WGS84 ellipsoid.
	 * Altitude will always be set, but in the absence of constraint data, will be accompanied by a very large AltitudeError.
	 * Client applications should check that the AltitudeError is reasonable for their purposes before displaying the Altitude to the user.
	 */
	public final float altitude;

	/**
	 *
	 */
	public final boolean underground;

	/**
	 * An altitude error estimate in meters.
	 *
	 * The error should be read as a CEP bounds in either direction about the Altitude
	 * ie: With 50% probability, [Altitude-AltitudeError, Altitude+AltitudeError] contains the user's current altitude.
	 *
	 * Client applications should check that this number looks reasonable for their purposes before displaying Altitude
	 * to end users.  As a rough order of magnitude, a standard building's floor height
	 * is around 3.2 meters.
	 */
	public final float altitudeError;

	/**
	 * Percent battery life of the android device at the time of this location
	 * Valid range 0-100
	 * Outside of range means battery life unknown
	 */
	public final byte batteryHH;
	/**
	 * Percent battery life of the tracking unit at the time of this location
	 * Valid range 0-100
	 * Outside of range means battery life unknown
	 */
	public final byte batteryTU;

	/**
	 * The ID of the building assigned in the nav engine. If there is no building assigned, this
	 * will be reference-equal to NeonLocation.NoBuilding.
	 */
	public final UUID buildingID;

	/**
	 * The revision of the building assigned in the nav engine. Used for database lookups.
	 */
	public final int buildingRevision;

	/**
	 * Average Scale
	 */
	public final float scale;

	/**
	 * Drift Error
	 */
	public final float scaleError;

	/**
	 * Average Drift
	 */
	public final float drift;

	/**
	 * Drift Error
	 */
	public final float driftError;

	public static final UUID NoBuilding = new UUID(0L, 0L);

	public NeonLocation(int serial, int session, long unixTimeMs, int navIter, String type,
			double longitude, double latitude, float heading, float headingError,
			double errorLongitude, double errorLatitude, float errorRadius,
			Float floor, Float floorError, float altitude, float altitudeError, boolean underground,
			byte batteryAndroid, byte batteryTrackingUnit,
			UUID buildingID, int buildingRevision, float scale, float scaleError, float drift, float driftError)
	{
		this.serialNumber = serial;
		this.sessionID = session;
		this.unixTimeMs = unixTimeMs;
		this.iteration = navIter;
		this.type = type;
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

		this.buildingID = buildingID;
		this.buildingRevision = buildingRevision;
		this.scale = scale;
		this.scaleError = scaleError;
		this.drift = drift;
		this.driftError = driftError;
	}

	/** Copy constructor*/
	public NeonLocation(NeonLocation loc)
	{
		this.serialNumber = loc.serialNumber;
		this.sessionID = loc.sessionID;
		this.unixTimeMs = loc.unixTimeMs;
		this.iteration = loc.iteration;
		this.type = loc.type;

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

		this.buildingID = loc.buildingID;
		this.buildingRevision = loc.buildingRevision;
		this.scale = loc.scale;
		this.scaleError = loc.scaleError;
		this.drift = loc.drift;
		this.driftError = loc.driftError;
	}

	/** A convenience method to quickly integrate NeonLocations into applications that use the android.location.Location type. */
	public Location toLocation()
	{
		return toLocation("Neon");
	}

	/** A convenience method to quickly integrate NeonLocations into applications that use the android.location.Location type. */
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
	public static final Creator<NeonLocation> CREATOR = new Creator<NeonLocation>() {
		@Override
		public NeonLocation createFromParcel(Parcel in) {
			return new NeonLocation(in);
		}

		@Override
		public NeonLocation[] newArray(int size) {
			return new NeonLocation[size];
		}
	};

	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	private NeonLocation(Parcel in)
	{
		serialNumber = in.readInt();
		sessionID = in.readInt();
		unixTimeMs = in.readLong();
		iteration = in.readInt();
		type = in.readString(); //This works somehow?

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

		buildingID = new UUID(in.readLong(), in.readLong());
		buildingRevision = in.readInt();
		scale = in.readFloat();
		scaleError = in.readFloat();
		drift = in.readFloat();
		driftError = in.readFloat();
	}

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
		dest.writeString(type);

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

		dest.writeLong(buildingID.getMostSignificantBits());
		dest.writeLong(buildingID.getLeastSignificantBits());
		dest.writeInt(buildingRevision);
		dest.writeFloat(scale);
		dest.writeFloat(scaleError);
		dest.writeFloat(drift);
		dest.writeFloat(driftError);
	}

	//
	// Overridden to improve toString() functionality
	//

	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

	@Override
	public String toString()
	{
		String sb = ("SerialNumber: " + serialNumber + ", ") +
				"Session: " + sessionID + ", " +
				"Time: " + NeonLocation.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", " +
				"Iteration: " + iteration + ", " +
				"Type: " + type + ", " +
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
				"Underground: " + underground + ", " +
				"BatteryHH: " + batteryHH + ", " +
				"BatteryTU: " + batteryTU + ", " +
				"Building: " + buildingID + " revision " + buildingRevision + ", " +
				"Drift: " + drift + ", " +
				"DriftError: " + driftError + ", " +
				"Scale: " + scale + ", " +
				"ScaleError: " + scaleError;

		return sb;
	}
}
