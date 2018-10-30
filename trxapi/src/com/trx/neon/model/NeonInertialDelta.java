package com.trx.neon.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NeonInertialDelta implements Parcelable
{
	/**
	 * This timestamp represents the time of the final inertial measurement.  It is in milliseconds since boot.
	 * (This is generally the same frame of reference as SystemClock.elapsedRealtime())
	 */
	public final long time;

	/**
	 * The change in heading of the user (radians) counter-clockwise
	 */
	public final float deltaHeading;

	/**
	 * The change in position of the user (meters) relative to their heading
	 * X-axis represents left-right (right is positive)
	 * Y-axis represents forward-back (forward is positive)
	 * Z-axis represents up-down (up is positive)
	 */
	public final float deltaX;
	public final float deltaY;
	public final float deltaZ;

	/**
	 * The magnitude of error
	 */
	public final float errorRadius;

	public NeonInertialDelta(long unixTimeMs, float deltaHeading, float deltaX, float deltaY, float deltaZ, float errorRadius)
	{
		this.time = SystemClock.elapsedRealtime() - System.currentTimeMillis() + unixTimeMs;
		this.deltaHeading = deltaHeading;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
		this.errorRadius = errorRadius;
	}

	/** Copy constructor*/
	public NeonInertialDelta(NeonInertialDelta loc)
	{
		this(
				loc.time,
				loc.deltaHeading,
				loc.deltaX,
				loc.deltaY,
				loc.deltaZ,
				loc.errorRadius
			);
	}

	//
	// Function to serialize and deserialize the class
	//

	/**
	 * Used for sending data across binders
	 */
	public static final Creator<NeonInertialDelta> CREATOR = new Creator<NeonInertialDelta>() {
		@Override
		public NeonInertialDelta createFromParcel(Parcel in) {
			return new NeonInertialDelta(in);
		}

		@Override
		public NeonInertialDelta[] newArray(int size) {
			return new NeonInertialDelta[size];
		}
	};

	/**
	 * Constructor for data serialized over the binder
	 * @param in
	 */
	private NeonInertialDelta(Parcel in)
	{
		this.time = in.readLong();
		this.deltaHeading = in.readFloat();
		this.deltaX = in.readFloat();
		this.deltaY = in.readFloat();
		this.deltaZ = in.readFloat();
		this.errorRadius = in.readFloat();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(time);
		dest.writeFloat(deltaHeading);
		dest.writeFloat(deltaX);
		dest.writeFloat(deltaY);
		dest.writeFloat(deltaZ);
		dest.writeFloat(errorRadius);
	}
	
	//
	// Overridden to improve toString() functionality
	//
	
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		final long unixTimeMs = System.currentTimeMillis() - SystemClock.elapsedRealtime() + time;
		sb.append("Time: " + NeonInertialDelta.DEFAULT_DATE_FORMAT.format(new Date(unixTimeMs)) + ", ");
		sb.append("DeltaHeading: " + deltaHeading + ", ");
		sb.append("DeltaX: " + deltaX + ", ");
		sb.append("DeltaY: " + deltaY + ", ");
		sb.append("DeltaZ: " + deltaZ + ", ");
		sb.append("Error Radius: " + errorRadius);

		return sb.toString();
	}
}
