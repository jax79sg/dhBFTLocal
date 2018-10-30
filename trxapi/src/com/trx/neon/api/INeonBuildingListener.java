/******************************************************************************
 *
 * 	 Copyright 2017, TRX Systems, Inc.  All Rights Reserved.
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

import com.trx.neon.model.DownloadResult;
import com.trx.neon.model.building.NeonBuilding;

import java.util.List;

/** Returns a NeonFloorPlan and result status */
public interface INeonBuildingListener {

	/** Will be called when NeonLocationService has had some sort of result */
	void onComplete(List<NeonBuilding> buildings, DownloadResult result);
}