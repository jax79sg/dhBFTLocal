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

import com.trx.neon.beta.UserActivityEvent;
import com.trx.neon.beta.WarfighterEvent;

//Notice:  FEEL FREE TO BREAK BACKWARDS COMPATIBILITY HERE!
//Anyone using a beta API agrees to be punished whenever we change things.

//Good lord trying to maintain a backwards compatible aidl interface with support
//for new features is nerve wracking.  I think this method is legit.  Here's hoping it
//doesn't blow up 3 months later.
oneway interface OnNeonEventBeta
{
	oneway void onUserActivityEvent(in UserActivityEvent ev);
	oneway void onWarfighterEvent(in WarfighterEvent ev);
}