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

import com.trx.neon.model.building.NeonFloorPlan;

/** Returns a NeonFloorPlan and result status */
public interface INeonFloorPlanListener {
	enum ImageResult {
		/** This image is unobtainable */
		FATAL_ERROR,
		/** There are connectivity issues preventing download at this time */
		CONNECTION_ERROR,
		/** We have successfully retrieved the image */
		SUCCESS
	}
	/** Will be called when NeonLocationService has had some sort of result */
	void onComplete(NeonFloorPlan floorplan, ImageResult result);
}