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
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import com.trx.neon.beta.DebugLocation;
import com.trx.neon.beta.INeonEventBeta;
import com.trx.neon.beta.INeonEventListenerBeta;
import com.trx.neon.beta.NeonEventTypeBeta;
import com.trx.neon.beta.UserActivityEvent;
import com.trx.neon.beta.WarfighterEvent;
import com.trx.neon.binder.MapperBinder;
import com.trx.neon.binder.MapperConnectionBinder;
import com.trx.neon.binder.BuildingBinder;
import com.trx.neon.binder.BuildingConnectionBinder;
import com.trx.neon.binder.NavigationBinder;
import com.trx.neon.binder.NavigationConnectionBinder;
import com.trx.neon.binder.NeonAuthBinder;
import com.trx.neon.binder.NeonBinder;
import com.trx.neon.binder.OnBuildingsCallback;
import com.trx.neon.binder.OnChange;
import com.trx.neon.binder.OnChangeBoolean;
import com.trx.neon.binder.OnChangeCalibrationEvent;
import com.trx.neon.binder.OnChangeConnectivityEvent;
import com.trx.neon.binder.OnChangeFilteredRawFeatureEvent;
import com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent;
import com.trx.neon.binder.OnChangeMotionLevelEvent;
import com.trx.neon.binder.OnChangeNavResetEvent;
import com.trx.neon.binder.OnChangeNavLockEvent;
import com.trx.neon.binder.OnChangeNeonLocationListV5;
import com.trx.neon.binder.OnChangePostureEvent;
import com.trx.neon.binder.OnChangeRawFeatureEvent;
import com.trx.neon.binder.OnChangeSessionEvent;
import com.trx.neon.binder.OnChangeSessionEventV2;
import com.trx.neon.binder.OnChangeString;
import com.trx.neon.binder.OnChangeStructuralFeatureEvent;
import com.trx.neon.binder.OnChangeUpdateAvailableEvent;
import com.trx.neon.binder.OnChangeVehicleStateEvent;
import com.trx.neon.binder.OnChangeNewFloorEvent;
import com.trx.neon.binder.OnNeonEvent;
import com.trx.neon.binder.OnNeonEventBeta;
import com.trx.neon.binder.PublicSettingsBinder;
import com.trx.neon.binder.PublicSettingsConnectionBinder;
import com.trx.neon.binder.ServiceConnectionBinder;
import com.trx.neon.binder.ServiceStatusBinder;
import com.trx.neon.model.DownloadOptions;
import com.trx.neon.model.DownloadResult;
import com.trx.neon.model.building.NeonBuilding;
import com.trx.neon.model.NeonInertialDelta;
import com.trx.neon.model.NeonLocation;
import com.trx.neon.model.NeonLocationType;
import com.trx.neon.model.building.NeonBuildingFloor;
import com.trx.neon.model.building.NeonFloorPlan;
import com.trx.neon.model.constraint.NeonConstraint;
import com.trx.neon.model.building.LatLongRect;
import com.trx.neon.model.event.BatteryEvent;
import com.trx.neon.model.event.BindingEvent;
import com.trx.neon.model.event.CalibrationEvent;
import com.trx.neon.model.event.ConnectivityEvent;
import com.trx.neon.model.event.FilteredRawFeatureEvent;
import com.trx.neon.model.event.INeonEvent;
import com.trx.neon.model.event.MapperStructuralFeatureEvent;
import com.trx.neon.model.event.MotionLevelEvent;
import com.trx.neon.model.event.NavLockEvent;
import com.trx.neon.model.event.NavResetEvent;
import com.trx.neon.model.event.NeonEventType;
import com.trx.neon.model.event.NewFloorEvent;
import com.trx.neon.model.event.PostureEvent;
import com.trx.neon.model.event.RawFeatureEvent;
import com.trx.neon.model.event.RemoteRangeEvent;
import com.trx.neon.model.event.SafetyAlertEvent;
import com.trx.neon.model.event.SessionEvent;
import com.trx.neon.model.event.StructuralFeatureEvent;
import com.trx.neon.model.event.UpdateAvailableEvent;
import com.trx.neon.model.event.VehicleStateEvent;
import com.trx.neon.model.extendedConstraint.GPSConstraint;
import com.trx.neon.model.extendedConstraint.LineSegmentConstraint;
import com.trx.neon.model.extendedConstraint.ManualConstraint;
import com.trx.neon.model.extendedConstraint.RangingConstraint;
import com.trx.neon.model.legacy.SessionEventLegacy;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * NOTE TO APPLICATION DEVELOPERS:  Expect this class to change with future releases.
 * Do not modify this class, do not develop your application against the specifics in this class.
 * Build against the Neon class itself.
 *
 * Implementation details of the Neon API.  Pay no attention to that man behind the curtain
 *
 * @author Dan
 *
 */
public class NeonImpl
{
	private static int idGen = 0;

	private static final String LOG_TAG = "NeonImpl";

	private static final String NEON_SERVICE_NAME = "com.trx.neon.framework.INTENT_SERVICE_NEON_SERVICE";
	private static final String NEON_SERVICE_PACKAGE = "com.trx.neon.locationservice";
	private static final String NEON_BUILDING_SERVICE_NAME = "com.trx.neon.framework.INTENT_SERVICE_NEON_BUILDING_SERVICE";
	private static final String NEON_BUILDING_SERVICE_PACKAGE = "com.trx.neon.locationservice";

	private Handler h;
	private final NeonLocationCache locationCache = new NeonLocationCache();
	private final NeonEventCache eventCache = new NeonEventCache();
	private final NeonEventCacheBeta eventCacheBeta = new NeonEventCacheBeta();

	//Copy on write assumes very few writes and a ton of reads, which seems reasonable for usage of these observers
	//Hardly matters if we synchronize every call into the implementation though.
	private final CopyOnWriteArrayList<FilteredLocationListener> locationListeners = new CopyOnWriteArrayList<>();
	private final CopyOnWriteArrayList<FilteredEventListener> eventListeners = new CopyOnWriteArrayList<>();
	private final CopyOnWriteArrayList<FilteredEventListenerBeta> eventListenersBeta = new CopyOnWriteArrayList<>();

	private boolean isBound;

	private NeonServiceConnection conn = null;
	private ServiceConnectionState boundState = null;
	private NeonConfig startupConfig = null;
	private final Queue<NeonConstraint> depositedConstraints = new LinkedList<>();
	private final Queue<GPSConstraint> depositedGPSConstraints = new LinkedList<>();
	private final Queue<LineSegmentConstraint> depositedLineSegmentConstraints = new LinkedList<>();
	private final Queue<ManualConstraint> depositedManualConstraints = new LinkedList<>();
	private final Queue<RangingConstraint> depositedRangingConstraints = new LinkedList<>();
	private final Queue<DebugLocation> depositedDebugLocations = new LinkedList<>();

    private long ElapsedRealtimeOffset = System.currentTimeMillis() - SystemClock.elapsedRealtime(); // will be overwritten with a value from the location service

    NeonImpl()
	{
		//This should keep our caches up to date, and by performing these operations synchronously,
		//should guarantee our caches get updated before new event listeners are registered.
		locationListeners.add(new FilteredLocationListener(locationCache, NeonLocationType.CURRENT, true, "NeonImpl Cache"));
		eventListeners.add(new FilteredEventListener(eventCache, EnumSet.allOf(NeonEventType.class), true));
		eventListenersBeta.add(new FilteredEventListenerBeta(eventCacheBeta, EnumSet.allOf(NeonEventTypeBeta.class), true));
	}

	public static boolean isNeonServiceInstalled(Context c) {
		PackageManager pm = c.getPackageManager();
		boolean app_installed;
		try {
			pm.getPackageInfo(NEON_SERVICE_PACKAGE, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		}
		catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	public synchronized long getElapsedRealtimeOffset() {
        return ElapsedRealtimeOffset;
    }

    public synchronized String getLocationServiceVersion(Context c)
	{
		PackageInfo pInfo;
		try {
			pInfo = c.getPackageManager().getPackageInfo(NEON_SERVICE_PACKAGE, 0);
			return pInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			return "ERROR: LOCATION SERVICE NOT FOUND";
		}

	}

	public synchronized void upgradeNeonLocationServices(Activity visibleActivity)
	{
		upgradeNeonLocationServices(visibleActivity, false);
	}

	public synchronized void upgradeNeonLocationServices(Activity visibleActivity, boolean mandatoryUpdate)
	{
		String ACTIVITY_UPGRADE = "com.trx.neon.framework.app.AUTO_UPGRADE";
		if (visibleActivity != null) {
			Intent intent = new Intent(ACTIVITY_UPGRADE);
			intent.putExtra("downloadIsMandatory", mandatoryUpdate);
			visibleActivity.startActivity(intent);
		}
	}

	public synchronized void upgradeDeviceFirmware(Activity visibleActivity)
	{
		String ACTIVITY_FIRMWARE_UPGRADE = "com.trx.neon.framework.app.FirmwareUpgradeActivity";
		if (visibleActivity != null)
			visibleActivity.startActivity(new Intent(ACTIVITY_FIRMWARE_UPGRADE));
	}

	public synchronized void upgradeDeviceFirmware(Activity visibleActivity, int requestCode)
	{
		String ACTIVITY_FIRMWARE_UPGRADE = "com.trx.neon.framework.app.FirmwareUpgradeActivity";
		if (visibleActivity != null)
			visibleActivity.startActivityForResult(new Intent(ACTIVITY_FIRMWARE_UPGRADE), requestCode);
	}

    public synchronized boolean startLoginActivityForResult(int requestCode, Activity visibleActivity, boolean forceLogOut)
    {
        Intent loginIntent = new Intent("com.trx.neon.framework.auth.LOGIN");

        if (loginIntent.resolveActivity(visibleActivity.getPackageManager()) != null)
        {
            loginIntent.putExtra("forceLogOut", forceLogOut);
            visibleActivity.startActivityForResult(loginIntent, requestCode);
            return true;
        }
        return false;
    }


    public synchronized void registerLocationUpdates(INeonLocationListener listener, NeonLocationType type, String tag)
	{
		FilteredLocationListener fll = new FilteredLocationListener(listener, type, tag);
		if (isBound)
		{
			//initialize the user location with the last thing we heard
			NeonLocation loc = locationCache.loc;
			if (loc != null)
			{
				ArrayList<NeonLocation> dls = new ArrayList<>();
				dls.add(loc);
				fll.onLocationChanged(dls);
			}
		}
		//We err on the side of dropped packets here.

		//Since we don't add it to the listener until afterward, whatever comes from the listener must be
		//later than what the client application has been initialized with.
		locationListeners.add(fll);
	}

	public synchronized void registerEvents(INeonEventListener listener, EnumSet<NeonEventType> events)
	{
		FilteredEventListener eventListener = new FilteredEventListener(listener, events);

		if (isBound)
		{
			//Initialize the client application with our cache of events
			for (Entry<String, INeonEvent> foo : eventCache.events.entrySet())
			{
				eventListener.onEvent(foo.getValue().getEventType(), foo.getValue());
			}
		}
		//We err on the side of dropped packets here.

		//Now add them
		eventListeners.add(eventListener);
	}

	public synchronized void registerEventsBeta(INeonEventListenerBeta listener, EnumSet<NeonEventTypeBeta> events)
	{
		FilteredEventListenerBeta eventListener = new FilteredEventListenerBeta(listener, events);

		if (isBound)
		{
			//Initialize the client application with our cache of events
			for (Entry<NeonEventTypeBeta, INeonEventBeta> foo : eventCacheBeta.events.entrySet())
				eventListener.onEvent(foo.getKey(), foo.getValue());
		}
		//We err on the side of dropped packets here.

		//Now add them
		eventListenersBeta.add(eventListener);
	}

	public synchronized void addConstraint(NeonConstraint constraint)
	{
		depositedConstraints.add(constraint);
		flushConstraints();
	}

	public synchronized void addRangingConstraint(RangingConstraint constraint)
	{
		depositedRangingConstraints.add(constraint);
		flushRangingConstraints();
	}

	public synchronized void addManualConstraint(ManualConstraint constraint)
	{
		depositedManualConstraints.add(constraint);
		flushManualConstraints();
	}

	public synchronized void addGPSConstraint(GPSConstraint constraint)
	{
		depositedGPSConstraints.add(constraint);
		flushGPSConstraints();
	}

	public synchronized  void addLineSegmentConstraint(LineSegmentConstraint constraint)
	{
		depositedLineSegmentConstraints.add(constraint);
		flushLineSegmentConstraints();
	}

	public synchronized void addDebugLocation(DebugLocation location)
	{
		depositedDebugLocations.add(location);
		flushDebugLocations();
	}

	public synchronized boolean unregisterLocationUpdates(INeonLocationListener listener)
	{
		return locationListeners.remove(new FilteredLocationListener(listener, NeonLocationType.CURRENT, "")); //Type doesn't matter, just need to find the matching inner listener.
	}

	public synchronized boolean unregisterEvents(INeonEventListener listener)
	{
		return eventListeners.remove(new FilteredEventListener(listener, EnumSet.noneOf(NeonEventType.class))); //Type doesn't matter, just need to find the matching inner listener.
	}

	public synchronized boolean unregisterEventsBeta(INeonEventListenerBeta listener)
	{
		return eventListenersBeta.remove(new FilteredEventListenerBeta(listener, EnumSet.noneOf(NeonEventTypeBeta.class))); //Type doesn't matter, just need to find the matching inner listener.
	}

	private static class ToHandlerOnChangeBoolean extends OnChangeBoolean.Stub
	{
		private final Handler mHandler;
		private final OnChangeBoolean mOnChangeBoolean;

		public ToHandlerOnChangeBoolean(final Handler handler, final OnChangeBoolean onChangeBoolean)
		{
			mHandler = handler;
			mOnChangeBoolean = onChangeBoolean;
		}
		@Override
		public void onChange(final boolean success) throws RemoteException
		{
			mHandler.post(new Runnable(){
				public void run(){
					try {
						if(mOnChangeBoolean != null)
							mOnChangeBoolean.onChange(success);
					} catch (RemoteException e) {
						Log.e(LOG_TAG, "in toHandlerOnChangeBoolean", e);
					}
				}
			});
		}
	}

	public void downloadBuildingsInArea(LatLongRect rect, final Handler h, final INeonBuildingListener callback, DownloadOptions downloadOptions)
	{
		if (!isBound)
			return;

		ServiceConnectionState scs = boundState;
		if (scs == null)
		{
			if(h == null)
				return;

			h.post(new Runnable(){
				public void run()
				{
					if(callback != null)
						callback.onComplete(new ArrayList<NeonBuilding>(), DownloadResult.CONNECTION_ERROR);
				}
			});
			return;
		}


		BuildingBinder bBinder = scs.buildingBinder;
		if (bBinder == null)
		{
			if(h == null)
				return;

			h.post(new Runnable(){
				public void run(){
					if(callback != null)
						callback.onComplete(new ArrayList<NeonBuilding>(), DownloadResult.CONNECTION_ERROR);
				}
			});
			return;
		}

		try
		{
			OnBuildingsCallback toHandlerBuildingCallback = new OnBuildingsCallback.Stub()
			{
				@Override
				public void onComplete(final List<NeonBuilding> buildings, final DownloadResult result) {
					if(h == null)
						return;

					h.post(new Runnable(){
						public void run(){
							if(callback != null)
								callback.onComplete(buildings, result);
						}
					});
				}
			};

			bBinder.downloadBuildingsInArea(rect, toHandlerBuildingCallback, downloadOptions);
		}
		catch (RemoteException e)
		{
			Log.w(LOG_TAG, "remote exception", e);
		}
	}

	public synchronized NeonBuilding getBuilding(UUID id)
	{
		if (!isBound)
			return null;

		ServiceConnectionState scs = boundState;
		if (scs == null)
			return null;

		BuildingBinder bBinder = scs.buildingBinder;
		if (bBinder == null)
			return null;

		try
		{
			return bBinder.getBuilding(id.toString());
		}
		catch (RemoteException e)
		{
			Log.w(LOG_TAG, "remote exception", e);
		}

		return null;
	}

	public static final String FILE_PATH = "com.trx.neon.locationservice.files";

	public static final String FLOORPLANS = "floorplans";

	public static final String DOWNLOAD_CONNECTIVITY_FAILURE = "FAILURE_CONNECTIVITY";
	public static final String DOWNLOAD_FATAL_FAILURE = "FAILURE_FATAL";

	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";

	public static final String DOWNLOAD_FLOORPLAN = "DownloadFloorplan";

	public static final String BLOCKING = "BLOCKING";
	public static final String REGULAR = "REGULAR";
	public static final String FILE_ID = "FILE_ID";
	public static final String FILE_TYPE = "FILE_TYPE";

	public static Uri getFileURI(String fileType, String fileName)
	{
		return new Uri.Builder().scheme("content").authority(FILE_PATH).path(fileType).appendPath(fileName).build();
	}

	/**
	 * This object will be closed as soon as the receiving delegate returns.
	 */
	public static class FileWithStatus implements AutoCloseable {
		public enum FileStatus {
			/**
			 * Signifies that this file exists and is ready to be read
			 */
			SUCCESS,
			/**
			 * Signifies that this file was unable to be downloaded due to a connectivity error
			 */
			CONNECTIVITY_FAILURE,
			/**
			 * Signifies that this file cannot be gotten
			 */
			FATAL_FAILURE,
			/**
			 * Signifies that this file has been closed
			 */
			CLOSED,;
		}

		private ParcelFileDescriptor file;
		private FileStatus status;

		public FileStatus getStatus()
		{
			return status;
		}

		/**
		 * Gets a file descriptor that can be used to read the file.
		 * Returns null unless #getStatus() == FileStatus.SUCCESS
		 *
		 * @return a file descriptor or null
		 */
		public FileDescriptor getFileDescriptor()
		{
			if (file == null)
			{
				return null;
			}
			return file.getFileDescriptor();
		}

		/**
		 * Receivers of this object are not responsible for calling this method
		 */
		public void close()
		{
			if (file != null)
			{
				try
				{
					file.close();
				}
				catch (IOException ignored)
				{
				}
			}
			file = null;
			status = FileStatus.CLOSED;
		}
	}

	private interface Delegate<T>
	{
		void run(T a);
	}

	private Bitmap decodeSampledBitmap(FileDescriptor fileDescriptor, int maxTextureWidth, int maxTextureHeight)
	{
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > maxTextureHeight || width > maxTextureWidth)
		{
			// Calculate the smallest inSampleSize value that is a power of 2 and
			// keeps both height and width smaller than the requested height and width.
			while ((height / inSampleSize) > maxTextureHeight || (width / inSampleSize) > maxTextureWidth) {
				inSampleSize *= 2;
			}
		}

		//Can't support shrinking non uniformly, can't respect required bounds.
		if (height / inSampleSize == 0 || width / inSampleSize == 0)
			return null;

		// Calculate inSampleSize
		options.inSampleSize = inSampleSize;
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
	}

	public synchronized void getFloorPlan(final Context context, final NeonBuildingFloor floor, final Handler handler, final INeonFloorPlanListener callback)
	{
		if(floor == null || !floor.hasFloorPlan())
		{
			if(callback == null)
				return;

			handler.post(new Runnable() {
				@Override
				public void run() {
					callback.onComplete(null, INeonFloorPlanListener.ImageResult.FATAL_ERROR);
				}
			});

			return;
		}

		tryGetFloorplan(context, floor.getFloorPlanImageID(), handler, new Delegate<FileWithStatus>() {
			@Override
			public void run(FileWithStatus fileStatus) {

				if(callback == null)
					return;


				switch(fileStatus.status) {
					case CONNECTIVITY_FAILURE: {
						callback.onComplete(null, INeonFloorPlanListener.ImageResult.CONNECTION_ERROR);
						break;
					}
					case FATAL_FAILURE: {
						callback.onComplete(null, INeonFloorPlanListener.ImageResult.FATAL_ERROR);
						break;
					}
					case SUCCESS: {
						Bitmap bm = decodeSampledBitmap(fileStatus.file.getFileDescriptor(), 2048, 2048);
						try {
							fileStatus.file.close();
						}
						catch(IOException e)
						{
							Log.e(LOG_TAG, "Can't close file descriptor");
						}

						NeonFloorPlan neonFloorplan = new NeonFloorPlan(bm, floor);
						callback.onComplete(neonFloorplan, INeonFloorPlanListener.ImageResult.SUCCESS);
						break;
					}
					default:
						break;
				}
			}
		});
	}

	private void tryGetFloorplan(final Context context, String floorplanID, Handler handler, final Delegate<FileWithStatus> delegate)
	{
		ParcelFileDescriptor fileDescriptor = getFloorplan(context, floorplanID);

		if (fileDescriptor != null)
		{
			final FileWithStatus status = new FileWithStatus();
			status.file = fileDescriptor;
			status.status = FileWithStatus.FileStatus.SUCCESS;
			handler.post(new Runnable() {
				@Override public void run()
				{
					try
					{
						delegate.run(status);
					}
					finally
					{
						status.close();
					}
				}
			});
			return;
		}
		downloadFloorplan(context, floorplanID, handler, delegate, false);
	}

	private ParcelFileDescriptor getFloorplan(final Context context, String floorplanID)
	{
		ContentResolver resolver = context.getContentResolver();
		try
		{
			return resolver.openFileDescriptor(getFileURI(FLOORPLANS, floorplanID), "r");
		}
		catch (FileNotFoundException e)
		{
			return null;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	private void downloadFloorplan(final Context context, final String floorplanID, final Handler handler, final Delegate<FileWithStatus> downloadStatus, boolean blocking)
	{
		final ContentResolver resolver = context.getContentResolver();
		Bundle bundle = new Bundle();
		bundle.putString(FILE_ID, floorplanID);
		bundle.putInt(FILE_TYPE, 0);
		if (downloadStatus != null)
		{
			ContentObserver observer = new ContentObserver(handler) {
				@Override public void onChange(boolean selfChange, Uri uri)
				{
					super.onChange(selfChange);

					resolver.unregisterContentObserver(this);
					if (uri.getLastPathSegment().equals(floorplanID))
					{
						// Success
						try (FileWithStatus ret = new FileWithStatus())
						{
							ret.status = FileWithStatus.FileStatus.SUCCESS;
							ret.file = getFloorplan(context, floorplanID);

							downloadStatus.run(ret);
						}
					}
					else if (uri.getLastPathSegment().equals(DOWNLOAD_CONNECTIVITY_FAILURE))
					{
						try (FileWithStatus ret = new FileWithStatus())
						{
							ret.status = FileWithStatus.FileStatus.CONNECTIVITY_FAILURE;
							ret.file = null;
							downloadStatus.run(ret);
						}
					}
					else if (uri.getLastPathSegment().equals(DOWNLOAD_FATAL_FAILURE))
					{
						try (FileWithStatus ret = new FileWithStatus())
						{
							ret.status = FileWithStatus.FileStatus.FATAL_FAILURE;
							ret.file = null;
							downloadStatus.run(ret);
						}
					}
				}
			};
			resolver.registerContentObserver(getFileURI(FLOORPLANS, floorplanID), true, observer);
		}
		call(resolver, getFileURI(FLOORPLANS, null), DOWNLOAD_FLOORPLAN, blocking ? BLOCKING : REGULAR, bundle);
	}

	private static Bundle call(ContentResolver resolver, Uri uri, String method, String parameters, Bundle extras)
	{
		try
		{
			return resolver.call(uri, method, parameters, extras);
		}
		catch (IllegalArgumentException e)
		{
			// NeonLocationService isn't updated
			Log.e(LOG_TAG, "Client is updated but NeonLocationService is outdated, unable to do operation");
			return null;
		}
		catch (SecurityException e)
		{
			// Permission mismatch, most likely NeonLocationService isn't updated, or manifest is missing write permission
			Log.e(LOG_TAG, "Client is updated but NeonLocationService is outdated, unable to do operation");
			return null;
		}
	}

	@Deprecated
	public synchronized void downloadArea(final double[] coordinates, final OnChangeBoolean onChangeBoolean)
	{
		if (!isBound)
			return;
		ServiceConnectionState scs = boundState;
		if (scs == null)
			return;

		NavigationBinder navBinder = scs.realNavBinder;
		if (navBinder == null)
			return;

		try
		{
			navBinder.downloadArea(coordinates, new ToHandlerOnChangeBoolean(h, onChangeBoolean));
		}
		catch (RemoteException e)
		{
			Log.w(LOG_TAG, "remote exception", e);
		}
	}

	//Made explicitly a weak reference to prevent accidentally leaking Activities for long periods of time.
	private WeakReference<Context> boundContext;
	public synchronized boolean bind(Context c, Handler h)
	{
		if (h == null)
			throw new IllegalArgumentException("Handler cannot be null");
		if (isBound)
		{
			/*
			 * Due to the inertial backbone of Neon's tracking, it does not make sense
			 * to allow tracking to stop and restart arbitrarily as the user passes between
			 * activities.  It is instead expected that the application will bind with
			 * an Application context and manage the lifetime of tracking manually
			 */
			throw new IllegalStateException("Cannot bind Neon while already bound");
		}

		conn = new NeonServiceConnection();
		Intent intent = new Intent(NEON_SERVICE_NAME);
		intent.setPackage(NEON_SERVICE_PACKAGE);
		isBound = c.bindService(intent, conn, Context.BIND_AUTO_CREATE);
		if (isBound)
		{
			boundContext = new WeakReference<>(c);
			this.h = h;
		}
		return isBound;
	}

	public synchronized void unbind()
	{
		if (!isBound)
			throw new IllegalStateException("Cannot unbind Neon while not bound");
		conn.setCanceled(true);
		if (boundState != null)
			boundState.unregisterAll();
		Context oldC = boundContext.get();
		if (oldC != null) //otherwise, the old context has already been garbage collected and any bindings are gone.
			oldC.unbindService(conn);
		this.isBound = false;
		this.h = null;
		boundState = null;
		conn = null;

		locationCache.clear();
		eventCache.clear();
		eventCacheBeta.clear();
	}

	public synchronized void setConfiguration(NeonConfig config)
	{
		if (isBound)
			throw new IllegalStateException("Must set configuration before binding Neon");

		this.startupConfig = config;
	}

	//TODO FIXME HACK:  This function gets run on an arbitrary binder thread, does it need to be synchronized?
	//What happens if user is adding/removing listeners when this gets called?  How is that synchronization guaranteed?
	//Hopefully by the types of the event listeners and the code in fireEvent and fireLocation.
	private synchronized void onConnect(NeonServiceConnection connection, IBinder service)
	{
		if (connection.isCanceled())
		{
			//client application unbound before we actually managed to connect to Neon (probably tracking was disabled)
			//Lets just return without linking up any events.
			return;
		}

		NeonBinder binder;
		try
		{
			binder = NeonAuthBinder.Stub.asInterface(service).awaitUserLogin();
			if (binder == null)
			{
				Log.w(LOG_TAG, "User rejected this application");
				return;
			}
		}
		catch (RemoteException e)
		{
			//Connection dropped before authentication completed, feh
			Log.w(LOG_TAG, "Couldn't stay connected to Neon Location Services", e);
			return;
		}

		boundState = new ServiceConnectionState(connection, binder, startupConfig);
		boundState.registerAll();
	}

	private void onDisconnect()
	{
		//We now differentiate the service disappearing from the RunningTracker events inside the service.
		fireEvent(new BindingEvent(System.currentTimeMillis(), BindingEvent.BindingEventType.FATAL_DISCONNECT), NeonEventType.BINDING);
	}

	private void fireEvent(INeonEvent e, NeonEventType type)
	{
		for (FilteredEventListener fel : eventListeners)
			fel.onEvent(type, e);
	}

	private void fireLocation(List<NeonLocation> locs)
	{
		for (FilteredLocationListener fll : locationListeners)
			fll.onLocationChanged(locs);
	}

	private void fireEvent(INeonEventBeta e, NeonEventTypeBeta type)
	{
		for (FilteredEventListenerBeta fel : eventListenersBeta)
			fel.onEvent(type, e);
	}

	private synchronized void flushConstraints()
	{
		if (!isBound)
			return;

		ServiceConnectionState scs = boundState;
		if (scs == null)
			return;

		NavigationBinder navBinder = scs.realNavBinder;
		if (navBinder == null)
			return;

		while (!depositedConstraints.isEmpty())
		{
			NeonConstraint nc = depositedConstraints.peek();

			try
			{
				navBinder.userCorrect(nc);
			}
			catch(RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't user correct due to remote exception, may try again later.", e);
				break;
			}

			depositedConstraints.poll();
		}
	}

	private synchronized void flushGPSConstraints()
	{
		if (!isBound)
			return;

		ServiceConnectionState scs = boundState;
		if (scs == null)
			return;

		NavigationBinder navBinder = scs.realNavBinder;
		if (navBinder == null)
			return;

		while (!depositedGPSConstraints.isEmpty())
		{
			GPSConstraint nc = depositedGPSConstraints.peek();

			try
			{
				navBinder.addGPSConstraint(nc);
			}
			catch(RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't add GPS Constraint due to remote exception, may try again later.", e);
				break;
			}

			depositedGPSConstraints.poll();
		}
	}

	private synchronized void flushManualConstraints()
	{
		if (!isBound)
			return;

		ServiceConnectionState scs = boundState;
		if (scs == null)
			return;

		NavigationBinder navBinder = scs.realNavBinder;
		if (navBinder == null)
			return;

		while (!depositedManualConstraints.isEmpty())
		{
			ManualConstraint nc = depositedManualConstraints.peek();

			try
			{
				navBinder.addManualConstraint(nc);
			}
			catch(RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't add Manual Constraint due to remote exception, may try again later.", e);
				break;
			}

			depositedManualConstraints.poll();
		}
	}

	private synchronized void flushLineSegmentConstraints()
	{
		if (!isBound)
			return;

		ServiceConnectionState scs = boundState;
		if (scs == null)
			return;

		NavigationBinder navBinder = scs.realNavBinder;
		if (navBinder == null)
			return;

		while (!depositedLineSegmentConstraints.isEmpty())
		{
			LineSegmentConstraint nc = depositedLineSegmentConstraints.peek();

			try
			{
				navBinder.addLineSegmentConstraint(nc);
			}
			catch(RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't add Line Segment Constraint due to remote exception, may try again later.", e);
				break;
			}

			depositedLineSegmentConstraints.poll();
		}
	}

	private synchronized void flushRangingConstraints()
	{
		if (!isBound)
			return;

		ServiceConnectionState scs = boundState;
		if (scs == null)
			return;

		NavigationBinder navBinder = scs.realNavBinder;
		if (navBinder == null)
			return;

		while (!depositedRangingConstraints.isEmpty())
		{
			RangingConstraint nc = depositedRangingConstraints.peek();

			try
			{
				navBinder.addRangingConstraint(nc);
			}
			catch(RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't add Ranging Constraint due to remote exception, may try again later.", e);
				break;
			}

			depositedRangingConstraints.poll();
		}
	}

	private synchronized void flushDebugLocations()
	{
		if (!isBound)
			return;

		ServiceConnectionState scs = boundState;
		if (scs == null)
			return;

		NavigationBinder navBinder = scs.realNavBinder;
		if (navBinder == null)
			return;

		while (!depositedDebugLocations.isEmpty())
		{
			DebugLocation dl = depositedDebugLocations.peek();

			try
			{
				navBinder.setDebugLocation(dl);
			}
			catch(RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't user correct due to remote exception, may try again later.", e);
				break;
			}

			depositedDebugLocations.poll();
		}
	}

	private String getID()
	{
		return "{" + UUID.randomUUID().toString() + "}";
	}

	public String startMapping()
	{
		Log.i(LOG_TAG, "Calling startMapping");
		if (!isBound)
		{
			Log.e(LOG_TAG, "Not bound");
			return null;
		}

		ServiceConnectionState scs = boundState;
		if (scs == null)
		{
			Log.e(LOG_TAG, "boundState is null");
			return null;
		}

		MapperBinder mapperBinder = scs.mapperBinder;
		if (mapperBinder == null)
		{
			Log.e(LOG_TAG, "routeBinder is null");
			return null;
		}

		try {
			String mapID = getID();
			mapperBinder.startMapperMode(mapID);
			return mapID;
		} catch (RemoteException e) {
			Log.e(LOG_TAG, "Error starting mapper mode.", e);
		}
		Log.e(LOG_TAG, "Something went wrong in start mapping");
		return null;
	}

	public boolean stopMapping(String mapID)
	{
		Log.i(LOG_TAG, "Calling stopMapping " + mapID);
		if (!isBound)
		{
			Log.e(LOG_TAG, "Not bound");
			return false;
		}

		ServiceConnectionState scs = boundState;
		if (scs == null)
		{
			Log.e(LOG_TAG, "boundState is null");
			return false;
		}

		MapperBinder mapperBinder = scs.mapperBinder;
		if (mapperBinder == null)
		{
			Log.e(LOG_TAG, "routeBinder is null");
			return false;
		}

		try {
			return mapperBinder.stopMapperMode(mapID);
		} catch (RemoteException e) {
			Log.e(LOG_TAG, "Error stopping mapper mode.", e);
		}
		Log.e(LOG_TAG, "Something went wrong in stop mapping");
		return false;
	}

	public boolean uploadMap(String mapID, final OnChangeString uploadSuccessEvent, final OnChange uploadFailEvent)
	{
		Log.i(LOG_TAG, "Calling uploadMap " + mapID);
		if (!isBound)
		{
			Log.e(LOG_TAG, "Not bound");
			return false;
		}

		ServiceConnectionState scs = boundState;
		if (scs == null)
		{
			Log.e(LOG_TAG, "boundState is null");
			return false;
		}

		MapperBinder mapperBinder = scs.mapperBinder;
		if (mapperBinder == null)
		{
			Log.e(LOG_TAG, "routeBinder is null");
			return false;
		}

		try {
			OnChangeString toHandlerUploadSuccessEvent = new OnChangeString.Stub()
			{
				@Override
				public void onChange(final String str) {
					h.post(new Runnable(){
						public void run(){try {
							uploadSuccessEvent.onChange(str);
						} catch (RemoteException e) {
							Log.e(LOG_TAG, "in toHandlerUploadSuccessEvent", e);
						}}
					});
				}
			};

			OnChange toHandlerUploadFailEvent = new OnChange.Stub()
			{
				@Override
				public void onChange() {
					h.post(new Runnable(){
						public void run(){try {
							uploadFailEvent.onChange();
						} catch (RemoteException e) {
							Log.e(LOG_TAG, "in toHandlerUploadFailEvent", e);
						}}
					});
				}
			};


			return mapperBinder.uploadMap(mapID, toHandlerUploadSuccessEvent, toHandlerUploadFailEvent);
		} catch (RemoteException e) {
			Log.e(LOG_TAG, "Error uploading map.", e);
		}
		Log.e(LOG_TAG, "Something went wrong in stop map");
		return false;
	}

	public boolean closeMap(String mapID)
	{
		Log.i(LOG_TAG, "Calling closeMap: " + mapID);
		if (!isBound)
		{
			Log.e(LOG_TAG, "Not bound");
			return false;
		}

		ServiceConnectionState scs = boundState;
		if (scs == null)
		{
			Log.e(LOG_TAG, "boundState is null");
			return false;
		}

		MapperBinder mapperBinder = scs.mapperBinder;
		if (mapperBinder == null)
		{
			Log.e(LOG_TAG, "routeBinder is null");
			return false;
		}

		try {
			return mapperBinder.deleteMap(mapID);
		} catch (RemoteException e) {
			Log.e(LOG_TAG, "Error deleting map.", e);
		}
		Log.e(LOG_TAG, "Something went wrong in delete map");
		return false;
	}

	public void processInertialDelta(NeonInertialDelta delta) {
		if (!isBound)
			return;
		ServiceConnectionState scs = boundState;
		if (scs == null)
			return;

		NavigationBinder navBinder = scs.realNavBinder;
		if (navBinder == null)
			return;

		try
		{
			navBinder.depositExternalInertialDelta(delta);
		}
		catch (RemoteException e)
		{
			Log.w(LOG_TAG, "Couldn't deposit external InertialDelta due to remote exception", e);
		}
	}

	private final static class NeonLocationCache implements INeonLocationListener
	{
		NeonLocation loc = null;
		@Override
		public void onLocationChanged(NeonLocation location)
		{
			loc = location;
		}
		public void clear()
		{
			loc = null;
		}
	}

	private final static class NeonEventCache implements INeonEventListener
	{
		public final ConcurrentHashMap<String, INeonEvent> events = new ConcurrentHashMap<>();

		@Override
		public void onEvent(NeonEventType type, INeonEvent ne)
		{
			events.put(ne.getKey(), ne);
		}

		public void clear()
		{
			events.clear();
		}
	}

	private final static class NeonEventCacheBeta implements INeonEventListenerBeta
	{
		public final ConcurrentHashMap<NeonEventTypeBeta, INeonEventBeta> events = new ConcurrentHashMap<>();

		@Override
		public void onEvent(NeonEventTypeBeta type, INeonEventBeta ne)
		{
			events.put(type, ne);
		}

		public void clear()
		{
			events.clear();
		}
	}

	private final class NeonServiceConnection implements ServiceConnection
	{
		private boolean canceled = false;
		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			onConnect(this, service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			onDisconnect();
		}

		public synchronized boolean isCanceled()
		{
			return canceled;
		}

		public synchronized void setCanceled(boolean cancel)
		{
			this.canceled = cancel;
		}
	}

	private final class FilteredEventListener implements INeonEventListener
	{
		private final INeonEventListener wrapped;
		private final EnumSet<NeonEventType> desired;
		private final boolean dontPost;

		public FilteredEventListener(INeonEventListener toWrap, EnumSet<NeonEventType> desired)
		{
			this(toWrap, desired, false);
		}

		public FilteredEventListener(INeonEventListener toWrap, EnumSet<NeonEventType> desired, boolean dontPost)
		{
			this.wrapped = toWrap;
			this.desired = desired;
			this.dontPost = dontPost;
		}

		@Override
		public void onEvent(final NeonEventType type, final INeonEvent ne)
		{
			if (desired.contains(type))
			{
				if (dontPost)
					wrapped.onEvent(type,ne);
				else
				{

					Handler handler = h;
					if (handler != null)
						handler.post(new Runnable()
						{
							public void run()
							{
								wrapped.onEvent(type, ne);
							}
						});
				}
			}
		}

		public boolean equals(Object o)
		{
			if (o == null || !(o instanceof FilteredEventListener))
				return false;
			return this.wrapped == ((FilteredEventListener)o).wrapped;
		}
	}

	private final class FilteredEventListenerBeta implements INeonEventListenerBeta
	{
		private final INeonEventListenerBeta wrapped;
		private final EnumSet<NeonEventTypeBeta> desired;
		private final boolean dontPost;

		public FilteredEventListenerBeta(INeonEventListenerBeta toWrap, EnumSet<NeonEventTypeBeta> desired)
		{
			this(toWrap, desired, false);
		}

		public FilteredEventListenerBeta(INeonEventListenerBeta toWrap, EnumSet<NeonEventTypeBeta> desired, boolean dontPost)
		{
			this.wrapped = toWrap;
			this.desired = desired;
			this.dontPost = dontPost;
		}

		@Override
		public void onEvent(final NeonEventTypeBeta type, final INeonEventBeta ne)
		{
			if (desired.contains(type))
			{
				if (dontPost)
					wrapped.onEvent(type, ne);
				else
				{
					Handler handler = h;
					if (handler != null)
					{
						handler.post(new Runnable()
						{
							public void run()
							{
								wrapped.onEvent(type, ne);
							}
						});
					}
				}
			}
		}

		public boolean equals(Object o)
		{
			if (o == null || !(o instanceof FilteredEventListenerBeta))
				return false;
			return this.wrapped == ((FilteredEventListenerBeta)o).wrapped;
		}
	}

	private final class FilteredLocationListener
	{
		private final INeonLocationListener wrapped;
		private final NeonLocationType desired;
		private NeonLocation lastSent;
		private final boolean dontPost;
        private final String tag;

		public FilteredLocationListener(INeonLocationListener toWrap, NeonLocationType desired, String tag)
		{
			this(toWrap, desired, false, tag);
		}

		public FilteredLocationListener(INeonLocationListener toWrap, NeonLocationType desired, boolean dontPost, String tag)
		{
			this.wrapped = toWrap;
			this.desired = desired;
			this.lastSent = null;
			this.dontPost = dontPost;
            this.tag = tag;
		}

		public void onLocationChanged(List<NeonLocation> locationUpdate)
		{
			if (locationUpdate.size() == 0)
				return;
			switch (desired)
			{
				//Only send last in any update list, and only send it if its new
				case CURRENT:
					{
						NeonLocation loc = locationUpdate.get(locationUpdate.size()-1);
						if (lastSent == null || lastSent.unixTimeMs < loc.unixTimeMs)
							sendToWrapped(loc);
					}
					break;

				//Only send more recent device locations, don't bother sending corrections to old data
				case PER_STEP:
                {
                    ArrayList<NeonLocation> locs = new ArrayList<>();
                    for (NeonLocation loc : locationUpdate)
                        if (lastSent == null || lastSent.unixTimeMs < loc.unixTimeMs)
                        {
                            locs.add(loc);
                        }
                    sendListToWrapped(locs);
                    break;
                }

				//Send everything the nav generates, including corrections to old points.
				case CORRECTED:
				    sendListToWrapped(new ArrayList<NeonLocation>(locationUpdate));
					break;
				default:
					throw new IllegalStateException("Unknown NeonLocationType: " + desired);
			}
		}

		private void sendToWrapped(final NeonLocation loc)
		{
			lastSent = loc;

			if (dontPost)
				wrapped.onLocationChanged(loc);
			else
			{
				Handler handler = h;
				if (handler != null)
				{
					handler.post(new Runnable()
					{
						public void run()
						{
							wrapped.onLocationChanged(loc);
						}
					});
				}
			}
		}

		private void sendListToWrapped(final List<NeonLocation> locs)
        {
            if (locs.isEmpty())
                return;
            lastSent = locs.get(locs.size() - 1);

            if (dontPost)
            {
                for (NeonLocation loc : locs)
                    wrapped.onLocationChanged(loc);
            }
            else
            {
                Handler handler = h;
                if (handler != null)
                {
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            for (NeonLocation loc : locs)
                                wrapped.onLocationChanged(loc);
                        }
                    });
                }
            }
        }

		public boolean equals(Object o)
		{
			if (o == null || !(o instanceof FilteredLocationListener))
				return false;
			return this.wrapped == ((FilteredLocationListener)o).wrapped;
		}
	}

	private final class ServiceConnectionState
	{
		public final NeonServiceConnection owner;
		public final PublicSettingsConnectionBinder.Stub setStartup;
		public final ServiceConnectionBinder.Stub servBinder;
		public final NavigationConnectionBinder.Stub navBinder;
		public final MapperConnectionBinder.Stub mapperConnBinder;
		public final BuildingConnectionBinder.Stub buildingConnBinder;

		public final OnChangeConnectivityEvent.Stub servConnectivityEvent;

		public final OnChangeCalibrationEvent.Stub navCalibrationEvent;
		public final OnChangeMotionLevelEvent.Stub navMotionLevelEvent;
		public final OnChangePostureEvent.Stub navPostureEvent;
		public final OnChangeStructuralFeatureEvent.Stub navStructuralFeatureEvent;
		public final OnChangeRawFeatureEvent.Stub navRawFeatureEvent;
		public final OnChangeFilteredRawFeatureEvent.Stub navFilteredRawFeatureEvent;
		public final OnChangeMapperStructuralFeatureEvent.Stub navMapperStructuralFeatureEvent;
		public final OnChangeSessionEvent.Stub navSessionEventLegacy;
		public final OnChangeSessionEventV2.Stub navSessionEvent;
		public final OnChangeNeonLocationListV5.Stub navLocationEvent;
		public final OnChangeVehicleStateEvent.Stub navVehicleStateEvent;
		public final OnChangeNavResetEvent.Stub navResetEvent;
		public final OnChangeNavLockEvent.Stub navLockEvent;
		public final OnChangeNewFloorEvent.Stub newFloorEvent;

		public final OnChangeUpdateAvailableEvent.Stub updateAvailableEvent;
		public final OnChangeBoolean.Stub runningEvent;

		public final OnNeonEvent.Stub messagingEvents;
		public final OnNeonEventBeta.Stub messagingEventsBeta;

		public NeonBinder		neonBinder = null;
		public NavigationBinder realNavBinder = null;
		public ServiceStatusBinder servStatusBinder = null;
		public MapperBinder mapperBinder = null;
		public BuildingBinder buildingBinder = null;
		@SuppressWarnings("unused")
		public PublicSettingsBinder publicSettingsBinder = null;

		public final Object registrationLock = new Object();

		public ServiceConnectionState(NeonServiceConnection owningConnection, final NeonBinder binder, final NeonConfig initialConfig)
		{
			this.owner = owningConnection;
			neonBinder = binder;
			updateAvailableEvent = new OnChangeUpdateAvailableEvent.Stub()
			{
				@Override
				public void onChange(UpdateAvailableEvent s) {
					fireEvent(s, NeonEventType.UPDATE_AVAILABLE);
				}
			};
			runningEvent = new OnChangeBoolean.Stub()
			{
				@Override
				public void onChange(boolean b)
				{
					if (b)
						fireEvent(new BindingEvent(System.currentTimeMillis(), BindingEvent.BindingEventType.CONNECT), NeonEventType.BINDING);
					else
						fireEvent(new BindingEvent(System.currentTimeMillis(), BindingEvent.BindingEventType.DISCONNECT), NeonEventType.BINDING);
				}
			};

			messagingEvents = new OnNeonEvent.Stub()
			{
				@Override
				public void onBatteryEvent(BatteryEvent ev) {
					fireEvent(ev, NeonEventType.BATTERY);
				}

				@Override
				public void onCalibrationEvent(CalibrationEvent ev) {
					fireEvent(ev, NeonEventType.CALIBRATION);
				}

				@Override
				public void onConnectivityEvent(ConnectivityEvent ev) {
					fireEvent(ev, NeonEventType.CONNECTIVITY);
				}

				@Override
				public void onMotionLevelEvent(MotionLevelEvent ev) {
					fireEvent(ev, NeonEventType.MOTION_LEVEL);
				}

				@Override
				public void onPostureEvent(PostureEvent ev) {
					fireEvent(ev, NeonEventType.POSTURE);
				}

				@Override
				public void onRemoteRangeEvent(RemoteRangeEvent ev) {
					fireEvent(ev, NeonEventType.REMOTE_RANGE);
				}

				@Override
				public void onSafetyAlertEvent(SafetyAlertEvent ev) {
					fireEvent(ev, NeonEventType.SAFETY_ALERT);
				}

				@Override
				public void onSessionEvent(SessionEventLegacy ev) {
				}

				@Override
				public void onStructuralFeatureEvent(StructuralFeatureEvent ev) {
					fireEvent(ev, NeonEventType.STRUCTURAL_FEATURE);
				}

				@Override
				public void onMapperStructuralFeatureEvent(MapperStructuralFeatureEvent ev) {
					fireEvent(ev, NeonEventType.MAPPER_STRUCTURAL_FEATURE);
				}

				@Override
				public void onUpdateAvailableEvent(UpdateAvailableEvent ev) {
					fireEvent(ev, NeonEventType.UPDATE_AVAILABLE);
				}

				@Override
				public void onVehicleStateEvent(VehicleStateEvent ev) {
					fireEvent(ev, NeonEventType.VEHICLE);
				}

				@Override
				public void onNavResetEvent(NavResetEvent ev) {
					fireEvent(ev, NeonEventType.NAV_RESET);
				}

				@Override
				public void onNavLockEvent(NavLockEvent ev) {
					fireEvent(ev, NeonEventType.NAV_LOCK);
				}

				@Override
				public void onNewFloorEvent(NewFloorEvent ev) {
					fireEvent(ev, NeonEventType.NEW_FLOOR);
				}

				@Override
				public void onSessionEventV2(SessionEvent ev) {
					fireEvent(ev, NeonEventType.SESSION);
				}
			};

			messagingEventsBeta = new OnNeonEventBeta.Stub()
			{
				@Override
				public void onUserActivityEvent(UserActivityEvent ev) {
					fireEvent(ev, NeonEventTypeBeta.USER_ACTIVITY);
				}

				@Override
				public void onWarfighterEvent(WarfighterEvent ev) {
					fireEvent(ev, NeonEventTypeBeta.WARFIGHTER);
				}
			};

			setStartup = new PublicSettingsConnectionBinder.Stub()
			{
				@Override
				public void onConnect(PublicSettingsBinder b)
				{
					synchronized(registrationLock)
					{
						if (owner.isCanceled())
							return;
						publicSettingsBinder = b;
						initialConfig.updateSettings(b);
						try {
                            // TODO get this in a better place
                            NeonImpl.this.ElapsedRealtimeOffset = b.getLong("elapsedRealtimeOffset");
						} catch (RemoteException e) {
							Log.w(LOG_TAG, "Unable to retrieve offset", e); //ugh.
						}
					}
				}

				@Override
				public void onDisconnect()
				{

				}
			};

			servConnectivityEvent = new OnChangeConnectivityEvent.Stub()
			{
				final int id = idGen++;
				public void onChange(ConnectivityEvent ce)
				{
					Log.i(LOG_TAG, "Received Connectivity Event, My ID: " + id);
					fireEvent(ce, NeonEventType.CONNECTIVITY);
				}
			};

			servBinder = new ServiceConnectionBinder.Stub()
			{
				@Override
				public void onConnect(ServiceStatusBinder b)
				{
					synchronized(registrationLock)
					{
						if (owner.isCanceled())
							return;

						servStatusBinder = b;
						try
						{
							b.registerConnectivityEventUpdates(servConnectivityEvent);
						}
						catch (RemoteException e)
						{
							Log.w(LOG_TAG, "Unable to register connectivity event updates");
						}
					}
				}

				@Override
				public void onDisconnect() {

				}
			};

			Log.d(LOG_TAG, "Creating mapperConnBinder");
			mapperConnBinder = new MapperConnectionBinder.Stub()
			{

				@Override
				public void onConnect(MapperBinder binder) {
					Log.d(LOG_TAG, "mapperConnBinder's onConnect");
					mapperBinder = binder;

				}

				@Override
				public void onDisconnect() {

				}

			};

			buildingConnBinder = new BuildingConnectionBinder.Stub()
			{

				@Override
				public void onConnect(BuildingBinder binder) {
					Log.d(LOG_TAG, "buildingConnBinder's onConnect");
					synchronized(registrationLock) {
						if (owner.isCanceled())
							return;
						buildingBinder = binder;
					}

				}

				@Override
				public void onDisconnect() {

				}

			};


			navCalibrationEvent = new OnChangeCalibrationEvent.Stub()
			{
				public void onChange(CalibrationEvent ce)
				{
					fireEvent(ce, NeonEventType.CALIBRATION);
				}
			};

			navStructuralFeatureEvent = new OnChangeStructuralFeatureEvent.Stub()
			{
				public void onChange(StructuralFeatureEvent sfe)
				{
					fireEvent(sfe, NeonEventType.STRUCTURAL_FEATURE);
				}
			};

			navMapperStructuralFeatureEvent = new OnChangeMapperStructuralFeatureEvent.Stub()
			{
				public void onChange(MapperStructuralFeatureEvent sfe)
				{
					fireEvent(sfe, NeonEventType.MAPPER_STRUCTURAL_FEATURE);
				}
			};

			navRawFeatureEvent = new OnChangeRawFeatureEvent.Stub()
			{
				public void onChange(RawFeatureEvent rfe)
				{
					fireEvent(rfe, NeonEventType.RAW_FEATURE);
				}
			};

			navFilteredRawFeatureEvent = new OnChangeFilteredRawFeatureEvent.Stub()
			{
				public void onChange(FilteredRawFeatureEvent rfe)
				{
					fireEvent(rfe, NeonEventType.FILTERED_RAW_FEATURE);
				}
			};

			navMotionLevelEvent = new OnChangeMotionLevelEvent.Stub()
			{
				public void onChange(MotionLevelEvent mle)
				{
					fireEvent(mle, NeonEventType.MOTION_LEVEL);
				}
			};

			navPostureEvent = new OnChangePostureEvent.Stub()
			{
				public void onChange(PostureEvent pe)
				{
					fireEvent(pe, NeonEventType.POSTURE);
				}
			};

			navVehicleStateEvent = new OnChangeVehicleStateEvent.Stub()
			{
				public void onChange(VehicleStateEvent ve)
				{
					fireEvent(ve, NeonEventType.VEHICLE);
				}
			};

			navSessionEventLegacy = new OnChangeSessionEvent.Stub()
			{
				public void onChange(SessionEventLegacy se)
				{
				}
			};

			navSessionEvent = new OnChangeSessionEventV2.Stub()
			{
				public void onChange(SessionEvent se)
				{
					fireEvent(se, NeonEventType.SESSION);
				}
			};

			navLocationEvent = new OnChangeNeonLocationListV5.Stub()
			{
				@Override
				public void onChange(List<NeonLocation> dl)
				{
					fireLocation(dl);
				}
			};

			navResetEvent = new OnChangeNavResetEvent.Stub()
			{
				@Override
				public void onChange(NavResetEvent re) { fireEvent(re, NeonEventType.NAV_RESET); }
			};

			navLockEvent = new OnChangeNavLockEvent.Stub()
			{
				@Override
				public void onChange(NavLockEvent re) { fireEvent(re, NeonEventType.NAV_LOCK); }
			};

			newFloorEvent = new OnChangeNewFloorEvent.Stub()
			{
				@Override
				public void onChange(NewFloorEvent re) { fireEvent(re, NeonEventType.NEW_FLOOR); }
			};

			navBinder = new NavigationConnectionBinder.Stub()
			{
				@Override
				public void onConnect(NavigationBinder b)
						throws RemoteException
				{
					synchronized(registrationLock)
					{
						if (owner.isCanceled())
							return;

						realNavBinder = b;
						b.registerCalibrationEventUpdates(navCalibrationEvent);
						b.registerFeatureEventUpdates(navStructuralFeatureEvent);
						b.registerRawFeatureEventUpdates(navRawFeatureEvent);
						b.registerFilteredRawFeatureEventUpdates(navFilteredRawFeatureEvent);
						b.registerMapperFeatureEventUpdates(navMapperStructuralFeatureEvent);
						b.registerMotionLevelEventUpdates(navMotionLevelEvent);
						b.registerNavResetEventUpdates(navResetEvent);
						b.registerNavLockEventUpdates(navLockEvent);
						b.registerNewFloorEventUpdates(newFloorEvent);
						b.registerPostureEventUpdates(navPostureEvent);
						b.registerVehicleStateUpdates(navVehicleStateEvent);
						b.registerSessionUpdatesV2(navSessionEvent);
						b.registerLocationUpdatesV5(navLocationEvent);

						flushConstraints();
						flushDebugLocations();
					}
				}

				@Override
				public void onDisconnect() {

				}
			};
		}

		public void registerAll()
		{
			try{
				neonBinder.registerForAvailableUpdates(boundState.updateAvailableEvent);
			} catch (RemoteException e) {
				Log.w(LOG_TAG, "Unable to bind for available updates", e);
			}

			try
			{
				neonBinder.registerIsRunningUpdates(runningEvent);
			}catch (RemoteException e){
				Log.w(LOG_TAG, "Unable to bind running event", e);
			}

			try
			{
				neonBinder.registerForMessagingEvents(messagingEvents);
			}catch (RemoteException e){
				Log.w(LOG_TAG, "Unable to bind messaging events", e);
			}

			//This is a little worrisome... this can't crash the client right?
			try
			{
				neonBinder.registerForMessagingEventsBeta(messagingEventsBeta);
			}catch (RemoteException e){
				Log.w(LOG_TAG, "Unable to bind messaging events", e);
			}

			if (startupConfig != null)
			{
				//6/3/2016 notice:  A configuration is good based on the combined lifetime of the bindings of the client application
				//Be careful not to bind settings, update settings, unbind settings, and then bind everything else later, or the config won't live that long.
				try
				{
					neonBinder.bindSettings(boundState.setStartup);
				}
				catch (RemoteException e)
				{
					Log.w(LOG_TAG, "Couldn't stay connected to NLS to set configuration", e);
				}
			}

			try
			{
				Log.i(LOG_TAG, "Binding buildingConnBinder");
				neonBinder.bindBuildings(boundState.buildingConnBinder);
			}
			catch (RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't start Neon Location Service Mapper", e);
			}

			try
			{
				neonBinder.bindServices(boundState.servBinder);
			}
			catch (RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't start Neon Location Service services", e);
			}

			try
			{
				neonBinder.bindNavigation(boundState.navBinder);
			}
			catch (RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't start Neon Location Service navigation", e);
			}


			try
			{
				Log.i(LOG_TAG, "Binding mapperConnBinder");
				neonBinder.bindMapper(boundState.mapperConnBinder);
			}
			catch (RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't start Neon Location Service Mapper", e);
			}
		}

		public void unregisterAll()
		{
			//NOTICE:  I know this looks like its just a pointless error check
			//HOWEVER, it also blocks until any actively running registration functions are done.
			//If you remove this synchronized block, you will introduce race conditions between the register and unregister.
			synchronized(registrationLock)
			{
				if (!owner.isCanceled())
				{
					//Race Conditions!  unregisterAll can run after calling registerAll but before the individual services
					//have been bound and had their events registered.  We deal with this by guaranteeing that the connection
					//has been canceled by the time we get here, so that if one of the used to register events inside of
					//registerAll is called after running this function, then it is just silently ignored.
					throw new IllegalStateException("Potential Race Condition Detected");
				}
			}

			if (realNavBinder != null)
			{
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterCalibrationEventUpdates(navCalibrationEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister calibration event updates");
				}
				try {
				if (realNavBinder != null)
					realNavBinder.unregisterFeatureEventUpdates(navStructuralFeatureEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister feature event updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterMapperFeatureEventUpdates(navMapperStructuralFeatureEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister mapper feature event updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterRawFeatureEventUpdates(navRawFeatureEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister raw feature updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterFilteredRawFeatureEventUpdates(navFilteredRawFeatureEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister filtered raw feature updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterMapperFeatureEventUpdates(navMapperStructuralFeatureEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister mapper feature event updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterMotionLevelEventUpdates(navMotionLevelEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister motion level updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterPostureEventUpdates(navPostureEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister posture updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterVehicleStateUpdates(navVehicleStateEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister vehicle state updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterSessionUpdatesV2(navSessionEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister session updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterLocationUpdatesV5(navLocationEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister location updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterNavResetEventUpdates(navResetEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister nav reset updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterNewFloorEventUpdates(newFloorEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister new floor updates");
				}
				try {
					if (realNavBinder != null)
						realNavBinder.unregisterNavLockEventUpdates(navLockEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister nav lock updates");
				}
				try {
					if (neonBinder != null)
						neonBinder.unbindNavigation(navBinder);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unbind Navigation");
				}
			}

			if (servBinder != null)
			{
				try {
					if (servStatusBinder != null)
						servStatusBinder.unregisterConnectivityEventUpdates(servConnectivityEvent);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister Connectivity");
				}

				try {
					if (neonBinder != null)
						neonBinder.unbindServices(servBinder);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unbind services");
				}
			}

			if (setStartup != null)
			{
				try {
					if (neonBinder != null)
						neonBinder.unbindSettings(setStartup);
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unbind settings");
				}
			}

			if(buildingConnBinder != null)
			{
				Log.i(LOG_TAG, "buildingConnBinder != null");
				try {
					if (neonBinder != null)
					{
						Log.i(LOG_TAG, "neonBinder != null");
						neonBinder.unbindBuildings(buildingConnBinder);
						Log.i(LOG_TAG, "neonBinder.unbind(buildingConnBinder) called");
					}
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unbind Buildings");
				}
			}
			else
			{
				Log.i(LOG_TAG, "buildingConnBinder == null");
			}

			if (updateAvailableEvent != null)
			{
				try {
					if (neonBinder != null)
						neonBinder.unregisterForAvailableUpdates(updateAvailableEvent);
				}
				catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unregister available update events");
				}
			}

			if (runningEvent != null)
			{
				try
				{
					neonBinder.unregisterIsRunningUpdates(runningEvent);
				}catch (RemoteException e){
					Log.w(LOG_TAG, "Unable to unbind running event", e);
				}
			}

			if (messagingEvents != null)
			{
				try
				{
					neonBinder.unregisterForMessagingEvents(messagingEvents);
				}
				catch (RemoteException e){
					Log.w(LOG_TAG, "Unable to unbind messaging events", e);
				}
			}

			if (messagingEventsBeta != null)
			{
				try
				{
					neonBinder.unregisterForMessagingEventsBeta(messagingEventsBeta);
				}
				catch (RemoteException e){
					Log.w(LOG_TAG, "Unable to unbind messaging events beta", e);
				}
			}

			//TODO is this in the right place?
			if(mapperConnBinder != null)
			{
				Log.i(LOG_TAG, "mapperConnBinder != null");
				try {
					if (neonBinder != null)
					{
						Log.i(LOG_TAG, "neonBinder != null");
						neonBinder.unbindMapper(mapperConnBinder);
						Log.i(LOG_TAG, "neonBinder.unbind(mapperConnBinder) called");
					}
				} catch (RemoteException e) {
					Log.w(LOG_TAG, "Could not unbind Mapper");
				}
			}
			else
			{
				Log.i(LOG_TAG, "mapperConnBinder == null");
			}
		}
	}
}
