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

package com.trx.neon.binder;

import com.trx.neon.model.event.BatteryEvent;
import com.trx.neon.model.event.CalibrationEvent;
import com.trx.neon.model.event.ConnectivityEvent;
import com.trx.neon.model.event.MotionLevelEvent;
import com.trx.neon.model.event.NavResetEvent;
import com.trx.neon.model.event.NavLockEvent;
import com.trx.neon.model.event.PostureEvent;
import com.trx.neon.model.event.RemoteRangeEvent;
import com.trx.neon.model.event.SafetyAlertEvent;
import com.trx.neon.model.legacy.SessionEventLegacy;
import com.trx.neon.model.event.StructuralFeatureEvent;
import com.trx.neon.model.event.MapperStructuralFeatureEvent;
import com.trx.neon.model.event.UpdateAvailableEvent;
import com.trx.neon.model.event.VehicleStateEvent;
import com.trx.neon.model.event.NewFloorEvent;
import com.trx.neon.model.event.SessionEvent;

//WARNING: CHANGES TO THIS FILE MUST BE BACKWARDS COMPATIBLE.
//HERE ARE SOME THINGS YOU CANNOT DO WITHOUT BREAKING BACKWARDS COMPATIBILITY:
//YOU MAY NOT REMOVE FUNCTIONS.  YOU MAY NOT REORDER FUNCTIONS.
//YOU MAY NOT CHANGE THE TYPES OF ANY ARGUMENTS.  THAT INCLUDES IN/OUT/INOUT CHANGES.
//YOU MAY NOT MARK FUNCTIONS ONEWAY
//HERE IS WHAT YOU CAN DO:
//YOU MAY ADD NEW FUNCTIONS AT THE END OF THE CLASS
//Hilariously, you can also probably rename functions, BUT DON'T DO IT
oneway interface OnNeonEvent
{
	oneway void onBatteryEvent(in BatteryEvent ev);
	oneway void onCalibrationEvent(in CalibrationEvent ev);
	oneway void onConnectivityEvent(in ConnectivityEvent ev);
	oneway void onMotionLevelEvent(in MotionLevelEvent ev);
	oneway void onPostureEvent(in PostureEvent ev);
	oneway void onRemoteRangeEvent(in RemoteRangeEvent ev);
	oneway void onSafetyAlertEvent(in SafetyAlertEvent ev);
	oneway void onSessionEvent(in SessionEventLegacy ev);
	oneway void onStructuralFeatureEvent(in StructuralFeatureEvent ev);
	oneway void onUpdateAvailableEvent(in UpdateAvailableEvent ev);
	oneway void onVehicleStateEvent(in VehicleStateEvent ev);
	oneway void onMapperStructuralFeatureEvent(in MapperStructuralFeatureEvent ev);
	oneway void onNavResetEvent(in NavResetEvent ev);
	oneway void onNavLockEvent(in NavLockEvent ev);
	oneway void onNewFloorEvent(in NewFloorEvent ev);
	oneway void onSessionEventV2(in SessionEvent ev);
}