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

import com.trx.neon.binder.OnChange;
import com.trx.neon.binder.OnChangeBoolean;
import com.trx.neon.binder.OnChangeAccountEvent;
import com.trx.neon.binder.OnChangeUpdateAvailableEvent;
import com.trx.neon.binder.NavigationConnectionBinder;
import com.trx.neon.binder.PublicSettingsConnectionBinder;
import com.trx.neon.binder.ServiceConnectionBinder;
import com.trx.neon.binder.MapperConnectionBinder;
import com.trx.neon.binder.BuildingConnectionBinder;
import com.trx.neon.binder.OnNeonEvent;
import com.trx.neon.binder.OnNeonEventBeta;

//WARNING: CHANGES TO THIS FILE MUST BE BACKWARDS COMPATIBLE.
//HERE ARE SOME THINGS YOU CANNOT DO WITHOUT BREAKING BACKWARDS COMPATIBILITY:
//YOU MAY NOT REMOVE FUNCTIONS.  YOU MAY NOT REORDER FUNCTIONS.
//YOU MAY NOT CHANGE THE TYPES OF ANY ARGUMENTS.  THAT INCLUDES IN/OUT/INOUT CHANGES.
//YOU MAY NOT MARK FUNCTIONS ONEWAY
//HERE IS WHAT YOU CAN DO:
//YOU MAY ADD NEW FUNCTIONS AT THE END OF THE CLASS
//Hilariously, you can also probably rename functions, BUT DON'T DO IT
oneway interface NeonBinder
{
	oneway void registerForAccount(String license, OnChangeAccountEvent tokenReceiver);
	oneway void unregisterForAccount(OnChangeAccountEvent tokenReceiver);

	oneway void registerForAvailableUpdates(OnChangeUpdateAvailableEvent toBind);
	oneway void unregisterForAvailableUpdates(OnChangeUpdateAvailableEvent toUnbind);

	oneway void bindSettings(PublicSettingsConnectionBinder toBind);
	oneway void unbindSettings(PublicSettingsConnectionBinder toUnbind);
		
	oneway void bindServices(ServiceConnectionBinder toBind);
	oneway void unbindServices(ServiceConnectionBinder toUnbind);
	
	oneway void bindNavigation(NavigationConnectionBinder toBind);
	oneway void unbindNavigation(NavigationConnectionBinder toUnbind);
	
	oneway void bindMapper(MapperConnectionBinder toBind);
	oneway void unbindMapper(MapperConnectionBinder toUnbind);
	
	oneway void registerIsRunningUpdates(OnChangeBoolean onChange);
	oneway void unregisterIsRunningUpdates(OnChangeBoolean onChange);

	oneway void registerForMessagingEvents(OnNeonEvent onEvent);
	oneway void unregisterForMessagingEvents(OnNeonEvent onEvent);

	oneway void registerForMessagingEventsBeta(OnNeonEventBeta onEvent);
	oneway void unregisterForMessagingEventsBeta(OnNeonEventBeta onEvent);

	oneway void bindBuildings(BuildingConnectionBinder toBind);
	oneway void unbindBuildings(BuildingConnectionBinder toUnbind);
}