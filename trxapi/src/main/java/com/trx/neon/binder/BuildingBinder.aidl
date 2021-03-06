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

import com.trx.neon.model.building.NeonBuilding;
import com.trx.neon.model.building.LatLongRect;
import com.trx.neon.binder.OnBuildingsCallback;
import com.trx.neon.model.DownloadOptions;


//WARNING: CHANGES TO THIS FILE MUST BE BACKWARDS COMPATIBLE.
//HERE ARE SOME THINGS YOU CANNOT DO WITHOUT BREAKING BACKWARDS COMPATIBILITY:
//YOU MAY NOT REMOVE FUNCTIONS.  YOU MAY NOT REORDER FUNCTIONS.
//YOU MAY NOT CHANGE THE TYPES OF ANY ARGUMENTS.  THAT INCLUDES IN/OUT/INOUT CHANGES.
//YOU MAY NOT MARK FUNCTIONS ONEWAY
//HERE IS WHAT YOU CAN DO:
//YOU MAY ADD NEW FUNCTIONS AT THE END OF THE CLASS
//Hilariously, you can also probably rename functions, BUT DON'T DO IT
interface BuildingBinder
{
    NeonBuilding getBuilding(in String buildingID);
	List<NeonBuilding> getBuildings(in LatLongRect bounds);
	oneway void downloadBuildingsInArea(in LatLongRect bounds, OnBuildingsCallback callback, in DownloadOptions options);
}