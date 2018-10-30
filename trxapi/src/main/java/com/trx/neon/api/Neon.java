/******************************************************************************
 * 		
 * 	 Copyright 2015, TRX Systems, Inc.  All Rights Reserved.
 * 
 *   TRX Systems, Inc.
 *   7500 Greenway Center Drive, Suite 420
 *   Greenbelt, Maryland  20770
 *   
 *   Tel:    (301) 313-0053
 *   email:  info@trxsystems.com
 * 
 *****************************************************************************/
package com.trx.neon.api;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.trx.neon.model.DownloadOptions;
import com.trx.neon.model.NeonInertialDelta;
import com.trx.neon.model.NeonLocationType;
import com.trx.neon.model.building.LatLongRect;
import com.trx.neon.model.building.NeonBuilding;
import com.trx.neon.model.building.NeonBuildingFloor;
import com.trx.neon.model.constraint.NeonConstraint;
import com.trx.neon.model.event.NeonEventType;
import com.trx.neon.model.extendedConstraint.GPSConstraint;
import com.trx.neon.model.extendedConstraint.LineSegmentConstraint;
import com.trx.neon.model.extendedConstraint.ManualConstraint;
import com.trx.neon.model.extendedConstraint.RangingConstraint;

import java.util.EnumSet;
import java.util.UUID;

/**
 * The main entry point for integrating Neon Location Services into your application.  
 * @author Dan
 */
public class Neon {
	/**
	 * Register an intent filter for this action to have your UI be started when the user touch pairs a tracking unit
	 */
	public static final String ACTIVITY_START_ON_TOUCH_PAIR = "com.trx.neon.framework.START_ON_TOUCH_PAIR";

	/**
	 * Neon location service is paired and ready to run
	 */
	public static final String NEON_READY_ON_TOUCH_PAIR = "com.trx.neon.framework.NEON_READY_ON_TOUCH_PAIR";

	/**
	 * Start this activity to let user manage Neon's preferences in a standard way
	 */
	public static final String ACTIVITY_SETTINGS = "com.trx.neon.framework.settings.SETTINGS";

	/**
	 * Register a listener for CURRENT location updates.
	 */
	public static void registerLocationUpdates(INeonLocationListener listener) {
		registerLocationUpdates(listener, NeonLocationType.CURRENT, "no tag");
	}

	/**
	 * Register a listener for location updates of the specified type
	 */
	public static void registerLocationUpdates(INeonLocationListener listener, NeonLocationType type) {
		neon.registerLocationUpdates(listener, type, "no tag");
	}

	/**
	 * Register a named listener for CURRENT location updates.
	 */
	public static void registerLocationUpdates(INeonLocationListener listener, String tag) {
		registerLocationUpdates(listener, NeonLocationType.CURRENT, tag);
	}

	/**
	 * Register a named listener for location updates of the specified type
	 */
	public static void registerLocationUpdates(INeonLocationListener listener, NeonLocationType type, String tag) {
		neon.registerLocationUpdates(listener, type, tag);
	}

	/**
	 * Register a listener for events of all types
	 */
	public static void registerEvents(INeonEventListener listener) {
		registerEvents(listener, EnumSet.allOf(NeonEventType.class));
	}

	/**
	 * Register a listener for the specified event types
	 */
	private static void registerEvents(INeonEventListener listener, EnumSet<NeonEventType> events) {
		neon.registerEvents(listener, events);
	}

	/**
	 * Checks if the Neon Service is already installed
	 * Return true if the Neon Service package was found
	 */
	public static boolean isNeonInstalled(Context c) {
		return NeonImpl.isNeonServiceInstalled(c);
	}

	/**
	 * Binds the Neon Location Services, ensuring that tracking is running and connecting all registered listeners
	 * All events will be posted to the main application thread.
	 * Returns true if Neon was successfully bound, false otherwise
	 */
	public static boolean bind(Context c) {
		return bind(c, new Handler(Looper.getMainLooper()));
	}

	/**
	 * Binds the Neon Location Services, ensuring that tracking is running and connecting all registered listeners
	 * All registered events will be posted to the specified handler.
	 * Returns true if Neon was successfully bound, false otherwise
	 */
	public static boolean bind(Context c, Handler eventHandler) {
		return neon.bind(c, eventHandler);
	}

	/**
	 * Constrain the user's location.
	 *
	 * @deprecated This method is deprecated in favor of addManualConstraint, addGPSConstraint, addRangingConstraint, and addLineSegmentConstraint.
	 */
	@Deprecated
	public static void addConstraint(NeonConstraint constraint) {
		if (constraint.getPosition() != null)
			checkLocation(constraint.getPosition().getLatitude(), constraint.getPosition().getLongitude());
		neon.addConstraint(constraint);
	}

	/**
	 * Add a ranging constraint to help solve the user's location
	 **/
	public static void addRangingConstraint(RangingConstraint constraint) {
		checkLocation(constraint.Latitude, constraint.Longitude);
		neon.addRangingConstraint(constraint);
	}

	/**
	 * Add a manual user correction to help solve the user's location
	 **/
	public static void addManualConstraint(ManualConstraint constraint) {
		checkLocation(constraint.Latitude, constraint.Longitude);
		neon.addManualConstraint(constraint);
	}

	/**
	 * Add a GPS constraint to help solve the user's location
	 **/
	public static void addGPSConstraint(GPSConstraint constraint) {
		checkLocation(constraint.Latitude, constraint.Longitude);
		neon.addGPSConstraint(constraint);
	}

	/**
	 * Add a line segment or path constraint to help solve the user's location
	 **/
	public static void addLineSegmentConstraint(LineSegmentConstraint constraint) {
		checkLocation(constraint.StartLatitude, constraint.StartLongitude);
		checkLocation(constraint.EndLatitude, constraint.EndLongitude);
		neon.addLineSegmentConstraint(constraint);
	}

	/**
	 * Process a InertialDelta
	 */
	public static void processInertialDelta(NeonInertialDelta delta) {
		neon.processInertialDelta(delta);
	}

	/**
	 * Unregister a previously registered listener
	 *
	 * @return true if listener was removed, false if it could not be found
	 */
	public static boolean unregisterLocationUpdates(INeonLocationListener listener) {
		return neon.unregisterLocationUpdates(listener);
	}

	/**
	 * Unregister a previously registered listener
	 *
	 * @return true if listener was removed, false if it could not be found
	 */
	public static boolean unregisterEvents(INeonEventListener listener) {
		return neon.unregisterEvents(listener);
	}

	/**
	 * Unbinds the Neon Location Services.  If this context is the last to unbind, then tracking will cease.
	 * All registered listeners will be disconnected until the next bind call,
	 * Events that have already been queued will continue to be processed, even after this function returns.
	 **/
	public static void unbind() {
		neon.unbind();
	}

	/**
	 * Update the Neon Configuration.  This should only be done on headless systems.
	 * On standard phones, startActivity(Neon.ACTIVITY_SETTINGS) instead.
	 */
	public static void setConfiguration(NeonConfig config) {
		neon.setConfiguration(config);
	}

	/**
	 * Get the version of the installed neon location service
	 **/
	public static String getLocationServiceVersion(Context c) {
		return neon.getLocationServiceVersion(c);
	}

	/**
	 * Interact with the user to download and install a new version of the Neon Location Services.
	 *
	 * @param visibleActivity A resumed Activity context used to start the interaction
	 */
	public static void upgradeNeonLocationServices(Activity visibleActivity) {
		neon.upgradeNeonLocationServices(visibleActivity);
	}

	/**
	 * Interact with the user to download and install a new version of the Neon Location Services.
	 *
	 * @param visibleActivity A resumed Activity context used to start the interaction
	 * @param mandatoryUpdate update will be mandatory
	 */
	public static void upgradeNeonLocationServices(Activity visibleActivity, boolean mandatoryUpdate) {
		neon.upgradeNeonLocationServices(visibleActivity, mandatoryUpdate);
	}

	/**
	 * Interact with the user to download and install a new version of the currently paired device firmware
	 *
	 * @param visibleActivity A resumed Activity context used to start the interaction
	 */
	public static void upgradeDeviceFirmware(Activity visibleActivity) {
		neon.upgradeDeviceFirmware(visibleActivity);
	}

	/**
	 * Interact with the user to download and install a new version of the currently paired device firmware
	 *
	 * @param visibleActivity A resumed Activity context used to start the interaction
	 * @param requestCode     A request code used for a call to startActivityForResult.  Results of the
	 *                        firmware upgrade process will be returned to the activity in an onActivityResult callback.
	 */
	public static void upgradeDeviceFirmware(Activity visibleActivity, int requestCode) {
		neon.upgradeDeviceFirmware(visibleActivity, requestCode);
	}

	/**
	 * Starts the LoginActivity, where users can login using their email and password or a google account
	 *
	 * @param visibleActivity onActivityResult will be called on this Activity when the Login Activity finishes
	 * @param requestCode     onActivityResult will be called this requestCode
	 * @param forceLogOut     forces LoginActivity to logout first, otherwise the activity will quit immediately when an account is already saved
	 * @return false if the LoginActivity could not be started, true otherwise
	 * in onActivityResult for this requestCode, if the resultCode is Activity.RESULT_CANCELED then the login was not done (pressed back button),
	 * if the resultCode is Activity.RESULT_OK then the login was successful and the account information is available via the data
	 * passed in onActivityResult :	Bundle extras = data.getExtras(); Account account = extras.getParcelable("account");
	 */
	public static boolean startLoginActivityForResult(int requestCode, Activity visibleActivity, boolean forceLogOut) {
		return neon.startLoginActivityForResult(requestCode, visibleActivity, forceLogOut);
	}

	/**
	 * Issues a request to download buildings in the specified area.  If they have previously been downloaded,
	 * it will return the cached result without hitting the network.
	 *
	 * @param bounds   The area defined by four latitude-longitude pairs
	 * @param callback Called on completion with status information (on main looper)
	 */
	public static void downloadBuildingsInArea(LatLongRect bounds, INeonBuildingListener callback) {
		checkLocation(bounds.Northeast.Latitude, bounds.Northeast.Longitude);
		checkLocation(bounds.Southwest.Latitude, bounds.Southwest.Longitude);
		neon.downloadBuildingsInArea(bounds, new Handler(Looper.getMainLooper()), callback, DownloadOptions.CACHED);
	}

	/**
	 * Issues a request to download buildings in the specified area.  If they have previously been downloaded,
	 * it will return the cached result without hitting the network.
	 *
	 * @param bounds   The area defined by four latitude-longitude pairs
	 * @param h        The handler, used for the status notification callback
	 * @param callback Called on completion with status information
	 */
	public static void downloadBuildingsInArea(LatLongRect bounds, Handler h, INeonBuildingListener callback) {
		checkLocation(bounds.Northeast.Latitude, bounds.Northeast.Longitude);
		checkLocation(bounds.Southwest.Latitude, bounds.Southwest.Longitude);
		neon.downloadBuildingsInArea(bounds, h, callback, DownloadOptions.CACHED);
	}

	/**
	 * Issues a request to download buildings in the specified area.  If they have previously been downloaded and the
	 * download options are set to CACHED, it will return the cached result without hitting the network.  If the options
	 * are set to FORCE_REFRESH it will re-download all buildings in the area.
	 *
	 * @param bounds          The area defined by four latitude-longitude pairs
	 * @param h               The handler, used for status notifications
	 * @param listener        Called on completion with a list of NeonBuildings and status
	 * @param downloadOptions If FORCE_REFRESH, will force the Neon Location Service to requery from the cloud.  If CACHED,
	 *                        calls to getBuildingsInArea may return cached values
	 */
	public static void downloadBuildingsInArea(LatLongRect bounds, Handler h, INeonBuildingListener listener, DownloadOptions downloadOptions) {
		checkLocation(bounds.Northeast.Latitude, bounds.Northeast.Longitude);
		checkLocation(bounds.Southwest.Latitude, bounds.Southwest.Longitude);
		neon.downloadBuildingsInArea(bounds, h, listener, downloadOptions);
	}

	/**
	 * retrieves a specific building by id that has already been downloaded to the Neon Location Service.
	 *
	 * @param id is the UUID identifier for a building
	 * @return A NeonBuilding or null if not found
	 */
	public static NeonBuilding getBuilding(UUID id) {
		return neon.getBuilding(id);
	}

	/**
	 * Downloads a floor plan image that corresponds with a NeonBuildingFloor.  This operation will
	 * download the floor plan if it has not already been cached in the Neon Location Service.  If the image
	 * can be retrieved it will decode a bitmap and return the results via the INeonFloorPlanListener.
	 * This is potentially a slow operation and work is done on the passed in handler - don't pass a handler
	 * to the main looper if you don't want to block the main thread.
	 *
	 * @param context         The context of the calling application
	 * @param floor           The NeonBuildingFloor object used to retrieve the floor plan image
	 * @param h               The handler, used for bitmap decoding and status notification. Work is performed on this handler.
	 * @param listener        Called on completion with status information and, if successful, a NeonFloorPlan
	 */
	public static void downloadFloorPlan(Context context, NeonBuildingFloor floor, Handler h, INeonFloorPlanListener listener)
	{
		neon.getFloorPlan(context, floor, h, listener);
	}

    protected static final NeonImpl neon = new NeonImpl();

    private static void checkLocation(double latitude, double longitude)
    {
        if (!(longitude >= -180 && longitude <= 180 && latitude >= -90 && latitude <= 90))
            throw new IllegalArgumentException("Location is not valid: latitude " + latitude + ", longitude " + longitude);
    }
}
