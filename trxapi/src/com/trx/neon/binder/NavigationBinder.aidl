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

import com.trx.neon.model.NeonInertialDelta;
import com.trx.neon.model.NeonLocation;
import com.trx.neon.model.legacy.NeonLocationLegacy;
import com.trx.neon.beta.DebugLocation;
import com.trx.neon.model.constraint.NeonConstraint;
import com.trx.neon.model.extendedConstraint.GPSConstraint;
import com.trx.neon.model.extendedConstraint.ManualConstraint;
import com.trx.neon.model.extendedConstraint.LineSegmentConstraint;
import com.trx.neon.model.extendedConstraint.RangingConstraint;
import com.trx.neon.binder.OnChangeBoolean;
import com.trx.neon.binder.OnChangeCalibrationEvent;
import com.trx.neon.binder.OnChangeNeonLocationList;
import com.trx.neon.binder.OnChangeNeonLocationListV2;
import com.trx.neon.binder.OnChangeNeonLocationListV3;
import com.trx.neon.binder.OnChangeNeonLocationListV4;
import com.trx.neon.binder.OnChangeNeonLocationListV5;
import com.trx.neon.binder.OnChangePostureEvent;
import com.trx.neon.binder.OnChangeMotionLevelEvent;
import com.trx.neon.binder.OnChangeStructuralFeatureEvent;
import com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent;
import com.trx.neon.binder.OnChangeVehicleStateEvent;
import com.trx.neon.binder.OnChangeSessionEvent;
import com.trx.neon.binder.OnChangeSessionEventV2;
import com.trx.neon.binder.OnChangeRawFeatureEvent;
import com.trx.neon.binder.OnChangeFilteredRawFeatureEvent;
import com.trx.neon.binder.OnChangeNavResetEvent;
import com.trx.neon.binder.OnChangeNavLockEvent;
import com.trx.neon.binder.OnChangeNewFloorEvent;

//WARNING: CHANGES TO THIS FILE MUST BE BACKWARDS COMPATIBLE.
//HERE ARE SOME THINGS YOU CANNOT DO WITHOUT BREAKING BACKWARDS COMPATIBILITY:
//YOU MAY NOT REMOVE FUNCTIONS.  YOU MAY NOT REORDER FUNCTIONS.
//YOU MAY NOT CHANGE THE TYPES OF ANY ARGUMENTS.  THAT INCLUDES IN/OUT/INOUT CHANGES.
//YOU MAY NOT MARK FUNCTIONS ONEWAY
//HERE IS WHAT YOU CAN DO:
//YOU MAY ADD NEW FUNCTIONS AT THE END OF THE CLASS
//Hilariously, you can also probably rename functions, BUT DON'T DO IT
oneway interface NavigationBinder
{
	oneway void userCorrect(in NeonConstraint knownLocation);
	oneway void setDebugLocation(in DebugLocation debugLocation);

	oneway void registerCalibrationEventUpdates(OnChangeCalibrationEvent onChange);
	oneway void unregisterCalibrationEventUpdates(OnChangeCalibrationEvent onChange);

	oneway void registerLocationUpdates(OnChangeNeonLocationList onChange);
	oneway void unregisterLocationUpdates(OnChangeNeonLocationList onChange);

	oneway void registerPostureEventUpdates(OnChangePostureEvent onChange);
	oneway void unregisterPostureEventUpdates(OnChangePostureEvent onChange);

	oneway void registerMotionLevelEventUpdates(OnChangeMotionLevelEvent onChange);
	oneway void unregisterMotionLevelEventUpdates(OnChangeMotionLevelEvent onChange);

	oneway void registerFeatureEventUpdates(OnChangeStructuralFeatureEvent onChange);
	oneway void unregisterFeatureEventUpdates(OnChangeStructuralFeatureEvent onChange);

	oneway void registerVehicleStateUpdates(OnChangeVehicleStateEvent onChange);
	oneway void unregisterVehicleStateUpdates(OnChangeVehicleStateEvent onChange);

	oneway void registerSessionUpdates(OnChangeSessionEvent onChange);
	oneway void unregisterSessionUpdates(OnChangeSessionEvent onChange);

	oneway void resyncEnvironment();
	oneway void downloadArea(in double[] coordinates, OnChangeBoolean onChangeBoolean);
	oneway void depositExternalInertialDelta(in NeonInertialDelta delta);

	oneway void registerMapperFeatureEventUpdates(OnChangeMapperStructuralFeatureEvent onChange);
    oneway void unregisterMapperFeatureEventUpdates(OnChangeMapperStructuralFeatureEvent onChange);

    oneway void registerRawFeatureEventUpdates(OnChangeRawFeatureEvent onChange);
    oneway void unregisterRawFeatureEventUpdates(OnChangeRawFeatureEvent onChange);

    oneway void registerFilteredRawFeatureEventUpdates(OnChangeFilteredRawFeatureEvent onChange);
    oneway void unregisterFilteredRawFeatureEventUpdates(OnChangeFilteredRawFeatureEvent onChange);

    oneway void addGPSConstraint(in GPSConstraint constraint);
    oneway void addManualConstraint(in ManualConstraint constraint);
    oneway void addLineSegmentConstraint(in LineSegmentConstraint constraint);
    oneway void addRangingConstraint(in RangingConstraint constraint);

    oneway void registerNavResetEventUpdates(OnChangeNavResetEvent onChange);
    oneway void unregisterNavResetEventUpdates(OnChangeNavResetEvent onChange);

	oneway void registerLocationUpdatesV2(OnChangeNeonLocationListV2 onChange);
	oneway void unregisterLocationUpdatesV2(OnChangeNeonLocationListV2 onChange);

	oneway void registerNavLockEventUpdates(OnChangeNavLockEvent onChange);
    oneway void unregisterNavLockEventUpdates(OnChangeNavLockEvent onChange);

    oneway void registerLocationUpdatesV3(OnChangeNeonLocationListV3 onChange);
    oneway void unregisterLocationUpdatesV3(OnChangeNeonLocationListV3 onChange);

    oneway void registerNewFloorEventUpdates(OnChangeNewFloorEvent onChange);
    oneway void unregisterNewFloorEventUpdates(OnChangeNewFloorEvent onChange);

    oneway void registerLocationUpdatesV4(OnChangeNeonLocationListV4 onChange);
    oneway void unregisterLocationUpdatesV4(OnChangeNeonLocationListV4 onChange);

    oneway void registerLocationUpdatesV5(OnChangeNeonLocationListV5 onChange);
    oneway void unregisterLocationUpdatesV5(OnChangeNeonLocationListV5 onChange);

    oneway void registerSessionUpdatesV2(OnChangeSessionEventV2 onChange);
    oneway void unregisterSessionUpdatesV2(OnChangeSessionEventV2 onChange);
}
