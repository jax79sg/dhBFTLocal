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

import android.accounts.Account;
import android.os.RemoteException;
import android.util.Log;

import com.trx.neon.binder.PublicSettingsBinder;
import com.trx.neon.model.config.TimeSlicePolicy;

/**
 * Defines a configuration of the Neon Location Service, 
 * Headless systems, as they lack any way to allow the user to choose his settings
 * should use this class to set a configuration before binding Neon.  
 * Any other system should instead start Neon.ACTIVITY_SETTINGS to allow the user
 * to choose his own settings at run time.  
 * @author Dan
 *
 */
public class NeonConfig 
{
	private final static String LOG_TAG = "NeonConfig";
	public final static String TRACKING_UNIT_BLUETOOTH_ADDR = "BTAddr";
	public final static String USER_ACCOUNT_NAME = "UserAccountName";
	public final static String USER_ACCOUNT_TYPE = "UserAccountType";
	public final static String NEON_LOCATION_SERVICE_MODE = "NeonLocationServiceMode";
	public final static String NEON_ENABLE_ON_BIND = "pref_tracking_enabled";
	public final static String NEON_BLE_SCAN_MODE = "NeonBleScanMode";
	public final static String NEON_INSTALLED_VERSION_STRING = "NeonInstalledVersionString";

	private String trackingUnitBTAddr = null;
	private Account userAccount = null;
	private String neonLocationServiceMode = null;
	private Boolean withEnableOnBind = null;
	private TimeSlicePolicy blePolicy = null;

	/** Construct a new NeonConfig, you can then set the settings you wish to be updated when Neon is bound */
	public NeonConfig()
	{

	}
	
	/** Sets the bluetooth address of the external tracking unit */
	public NeonConfig withTrackingUnitBTAddr(String bluetoothAddr)
	{
		trackingUnitBTAddr = bluetoothAddr;
		return this;
	}
	
	/**
	 * Sets the chosen account to use to download location assistance data.
	 * This should correspond to an account known to the end user device,
	 * or the user will be forced to log in again.
	 */
	public NeonConfig withUserAccount(Account account)
	{
		userAccount = account;
		return this;
	}

	/**
	 * Sets the chosen account to use to download location assistance data.
	 * The api key must match your subscription's api key at neon.trxsystems.com
     */
	public NeonConfig withServiceAccount(String apiKey)
	{
		userAccount = new Account(apiKey, "com.trx.apikey");
		return this;
	}
	
	/** Sets the configuration of the Neon Location Services. */
	public NeonConfig withLocationServiceMode(String mode)
	{
		neonLocationServiceMode = mode;
		return this;
	}

	/** Force enables tracking when binding this client.
	 * This should only be used on headless devices */
	public NeonConfig withEnableOnBind(boolean enableOnBind)
	{
		withEnableOnBind = enableOnBind;
		return this;
	}

	/**
	 * Specifies a time slicing policy for scanning BLE anchors.
	 * Specifying an interval of 0 will prevent all scanning.
	 * Specifying a delay of 0 will enable continuous scanning
	 * Both must be non negative, and at least one must be positive.
	 *
	 * Neon will scan continuously by default, and specifying any other policy
	 * will negatively impact the end user's tracking results in areas with
	 * BLE beacons.
	 * @param intervalMillis The length of the scanning interval in milliseconds
	 * @param delayMillis The length of time between two scans in milliseconds
     */
	public NeonConfig withBleScanMode(long intervalMillis, long delayMillis)
	{
		//Future versions should enable more complex scan modes,
		//but time slicing should be sufficient for now.
		blePolicy = new TimeSlicePolicy(intervalMillis, delayMillis);
		return this;
	}

	/**
	 * Used by Neon to update the settings.  Client application should not worry about this.  
	 * @param b
	 * @return
	 */
	protected boolean updateSettings(PublicSettingsBinder b)
	{
		boolean set = true;
		if (trackingUnitBTAddr != null)
		{
			try 
			{
				set = b.setString(TRACKING_UNIT_BLUETOOTH_ADDR, trackingUnitBTAddr);
			} 
			catch (RemoteException e) 
			{
				Log.w(LOG_TAG, "Couldn't set setting: " + TRACKING_UNIT_BLUETOOTH_ADDR, e);
				set = false;
			}
		}
		
		if (userAccount != null)
		{
			try
			{
				set &= b.setString(USER_ACCOUNT_NAME, userAccount.name);
				set &= b.setString(USER_ACCOUNT_TYPE, userAccount.type);
			}
			catch(RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't set setting: " + USER_ACCOUNT_NAME, e);
				set = false;
			}
		}
		
		if (neonLocationServiceMode != null)
		{
			try
			{
				set &= b.setString(NEON_LOCATION_SERVICE_MODE, neonLocationServiceMode);
			}
			catch(RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't set setting: " + NEON_LOCATION_SERVICE_MODE, e);
				set = false;
			}
		}

		if (withEnableOnBind != null)
		{
			try
			{
				set &= b.setBoolean(NEON_ENABLE_ON_BIND, withEnableOnBind);
			}
			catch (RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't set setting: " + NEON_ENABLE_ON_BIND, e);
				set = false;
			}
		}

		if (blePolicy != null)
		{
			try
			{
				set &= b.setTimeSlicePolicy(NEON_BLE_SCAN_MODE, blePolicy);
			}
			catch (RemoteException e)
			{
				Log.w(LOG_TAG, "Couldn't set setting: " + NEON_BLE_SCAN_MODE, e);
				set = false;
			}
		}
		
		//More settings go here.  Remember to keep anding into set flag.
		return set;
	}
}
