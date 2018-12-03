/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\User\\StudioProjects\\dhBFTLocal\\trxapi\\src\\main\\java\\com\\trx\\neon\\binder\\NeonBinder.aidl
 */
package com.trx.neon.binder;
//WARNING: CHANGES TO THIS FILE MUST BE BACKWARDS COMPATIBLE.
//HERE ARE SOME THINGS YOU CANNOT DO WITHOUT BREAKING BACKWARDS COMPATIBILITY:
//YOU MAY NOT REMOVE FUNCTIONS.  YOU MAY NOT REORDER FUNCTIONS.
//YOU MAY NOT CHANGE THE TYPES OF ANY ARGUMENTS.  THAT INCLUDES IN/OUT/INOUT CHANGES.
//YOU MAY NOT MARK FUNCTIONS ONEWAY
//HERE IS WHAT YOU CAN DO:
//YOU MAY ADD NEW FUNCTIONS AT THE END OF THE CLASS
//Hilariously, you can also probably rename functions, BUT DON'T DO IT

public interface NeonBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.trx.neon.binder.NeonBinder
{
private static final java.lang.String DESCRIPTOR = "com.trx.neon.binder.NeonBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.trx.neon.binder.NeonBinder interface,
 * generating a proxy if needed.
 */
public static com.trx.neon.binder.NeonBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.trx.neon.binder.NeonBinder))) {
return ((com.trx.neon.binder.NeonBinder)iin);
}
return new com.trx.neon.binder.NeonBinder.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
java.lang.String descriptor = DESCRIPTOR;
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(descriptor);
return true;
}
case TRANSACTION_registerForAccount:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
com.trx.neon.binder.OnChangeAccountEvent _arg1;
_arg1 = com.trx.neon.binder.OnChangeAccountEvent.Stub.asInterface(data.readStrongBinder());
this.registerForAccount(_arg0, _arg1);
return true;
}
case TRANSACTION_unregisterForAccount:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeAccountEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeAccountEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterForAccount(_arg0);
return true;
}
case TRANSACTION_registerForAvailableUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeUpdateAvailableEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeUpdateAvailableEvent.Stub.asInterface(data.readStrongBinder());
this.registerForAvailableUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterForAvailableUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeUpdateAvailableEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeUpdateAvailableEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterForAvailableUpdates(_arg0);
return true;
}
case TRANSACTION_bindSettings:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.PublicSettingsConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.PublicSettingsConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.bindSettings(_arg0);
return true;
}
case TRANSACTION_unbindSettings:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.PublicSettingsConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.PublicSettingsConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.unbindSettings(_arg0);
return true;
}
case TRANSACTION_bindServices:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.ServiceConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.ServiceConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.bindServices(_arg0);
return true;
}
case TRANSACTION_unbindServices:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.ServiceConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.ServiceConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.unbindServices(_arg0);
return true;
}
case TRANSACTION_bindNavigation:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.NavigationConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.NavigationConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.bindNavigation(_arg0);
return true;
}
case TRANSACTION_unbindNavigation:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.NavigationConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.NavigationConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.unbindNavigation(_arg0);
return true;
}
case TRANSACTION_bindMapper:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.MapperConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.MapperConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.bindMapper(_arg0);
return true;
}
case TRANSACTION_unbindMapper:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.MapperConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.MapperConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.unbindMapper(_arg0);
return true;
}
case TRANSACTION_registerIsRunningUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeBoolean _arg0;
_arg0 = com.trx.neon.binder.OnChangeBoolean.Stub.asInterface(data.readStrongBinder());
this.registerIsRunningUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterIsRunningUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeBoolean _arg0;
_arg0 = com.trx.neon.binder.OnChangeBoolean.Stub.asInterface(data.readStrongBinder());
this.unregisterIsRunningUpdates(_arg0);
return true;
}
case TRANSACTION_registerForMessagingEvents:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnNeonEvent _arg0;
_arg0 = com.trx.neon.binder.OnNeonEvent.Stub.asInterface(data.readStrongBinder());
this.registerForMessagingEvents(_arg0);
return true;
}
case TRANSACTION_unregisterForMessagingEvents:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnNeonEvent _arg0;
_arg0 = com.trx.neon.binder.OnNeonEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterForMessagingEvents(_arg0);
return true;
}
case TRANSACTION_registerForMessagingEventsBeta:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnNeonEventBeta _arg0;
_arg0 = com.trx.neon.binder.OnNeonEventBeta.Stub.asInterface(data.readStrongBinder());
this.registerForMessagingEventsBeta(_arg0);
return true;
}
case TRANSACTION_unregisterForMessagingEventsBeta:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnNeonEventBeta _arg0;
_arg0 = com.trx.neon.binder.OnNeonEventBeta.Stub.asInterface(data.readStrongBinder());
this.unregisterForMessagingEventsBeta(_arg0);
return true;
}
case TRANSACTION_bindBuildings:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.BuildingConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.BuildingConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.bindBuildings(_arg0);
return true;
}
case TRANSACTION_unbindBuildings:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.BuildingConnectionBinder _arg0;
_arg0 = com.trx.neon.binder.BuildingConnectionBinder.Stub.asInterface(data.readStrongBinder());
this.unbindBuildings(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.trx.neon.binder.NeonBinder
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void registerForAccount(java.lang.String license, com.trx.neon.binder.OnChangeAccountEvent tokenReceiver) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(license);
_data.writeStrongBinder((((tokenReceiver!=null))?(tokenReceiver.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerForAccount, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterForAccount(com.trx.neon.binder.OnChangeAccountEvent tokenReceiver) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((tokenReceiver!=null))?(tokenReceiver.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterForAccount, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerForAvailableUpdates(com.trx.neon.binder.OnChangeUpdateAvailableEvent toBind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toBind!=null))?(toBind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerForAvailableUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterForAvailableUpdates(com.trx.neon.binder.OnChangeUpdateAvailableEvent toUnbind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toUnbind!=null))?(toUnbind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterForAvailableUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void bindSettings(com.trx.neon.binder.PublicSettingsConnectionBinder toBind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toBind!=null))?(toBind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_bindSettings, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unbindSettings(com.trx.neon.binder.PublicSettingsConnectionBinder toUnbind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toUnbind!=null))?(toUnbind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unbindSettings, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void bindServices(com.trx.neon.binder.ServiceConnectionBinder toBind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toBind!=null))?(toBind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_bindServices, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unbindServices(com.trx.neon.binder.ServiceConnectionBinder toUnbind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toUnbind!=null))?(toUnbind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unbindServices, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void bindNavigation(com.trx.neon.binder.NavigationConnectionBinder toBind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toBind!=null))?(toBind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_bindNavigation, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unbindNavigation(com.trx.neon.binder.NavigationConnectionBinder toUnbind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toUnbind!=null))?(toUnbind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unbindNavigation, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void bindMapper(com.trx.neon.binder.MapperConnectionBinder toBind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toBind!=null))?(toBind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_bindMapper, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unbindMapper(com.trx.neon.binder.MapperConnectionBinder toUnbind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toUnbind!=null))?(toUnbind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unbindMapper, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerIsRunningUpdates(com.trx.neon.binder.OnChangeBoolean onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerIsRunningUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterIsRunningUpdates(com.trx.neon.binder.OnChangeBoolean onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterIsRunningUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerForMessagingEvents(com.trx.neon.binder.OnNeonEvent onEvent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onEvent!=null))?(onEvent.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerForMessagingEvents, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterForMessagingEvents(com.trx.neon.binder.OnNeonEvent onEvent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onEvent!=null))?(onEvent.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterForMessagingEvents, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerForMessagingEventsBeta(com.trx.neon.binder.OnNeonEventBeta onEvent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onEvent!=null))?(onEvent.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerForMessagingEventsBeta, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterForMessagingEventsBeta(com.trx.neon.binder.OnNeonEventBeta onEvent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onEvent!=null))?(onEvent.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterForMessagingEventsBeta, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void bindBuildings(com.trx.neon.binder.BuildingConnectionBinder toBind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toBind!=null))?(toBind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_bindBuildings, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unbindBuildings(com.trx.neon.binder.BuildingConnectionBinder toUnbind) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((toUnbind!=null))?(toUnbind.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unbindBuildings, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_registerForAccount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterForAccount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerForAvailableUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregisterForAvailableUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_bindSettings = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_unbindSettings = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_bindServices = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_unbindServices = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_bindNavigation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_unbindNavigation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_bindMapper = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_unbindMapper = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_registerIsRunningUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_unregisterIsRunningUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_registerForMessagingEvents = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_unregisterForMessagingEvents = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_registerForMessagingEventsBeta = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_unregisterForMessagingEventsBeta = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_bindBuildings = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_unbindBuildings = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
}
public void registerForAccount(java.lang.String license, com.trx.neon.binder.OnChangeAccountEvent tokenReceiver) throws android.os.RemoteException;
public void unregisterForAccount(com.trx.neon.binder.OnChangeAccountEvent tokenReceiver) throws android.os.RemoteException;
public void registerForAvailableUpdates(com.trx.neon.binder.OnChangeUpdateAvailableEvent toBind) throws android.os.RemoteException;
public void unregisterForAvailableUpdates(com.trx.neon.binder.OnChangeUpdateAvailableEvent toUnbind) throws android.os.RemoteException;
public void bindSettings(com.trx.neon.binder.PublicSettingsConnectionBinder toBind) throws android.os.RemoteException;
public void unbindSettings(com.trx.neon.binder.PublicSettingsConnectionBinder toUnbind) throws android.os.RemoteException;
public void bindServices(com.trx.neon.binder.ServiceConnectionBinder toBind) throws android.os.RemoteException;
public void unbindServices(com.trx.neon.binder.ServiceConnectionBinder toUnbind) throws android.os.RemoteException;
public void bindNavigation(com.trx.neon.binder.NavigationConnectionBinder toBind) throws android.os.RemoteException;
public void unbindNavigation(com.trx.neon.binder.NavigationConnectionBinder toUnbind) throws android.os.RemoteException;
public void bindMapper(com.trx.neon.binder.MapperConnectionBinder toBind) throws android.os.RemoteException;
public void unbindMapper(com.trx.neon.binder.MapperConnectionBinder toUnbind) throws android.os.RemoteException;
public void registerIsRunningUpdates(com.trx.neon.binder.OnChangeBoolean onChange) throws android.os.RemoteException;
public void unregisterIsRunningUpdates(com.trx.neon.binder.OnChangeBoolean onChange) throws android.os.RemoteException;
public void registerForMessagingEvents(com.trx.neon.binder.OnNeonEvent onEvent) throws android.os.RemoteException;
public void unregisterForMessagingEvents(com.trx.neon.binder.OnNeonEvent onEvent) throws android.os.RemoteException;
public void registerForMessagingEventsBeta(com.trx.neon.binder.OnNeonEventBeta onEvent) throws android.os.RemoteException;
public void unregisterForMessagingEventsBeta(com.trx.neon.binder.OnNeonEventBeta onEvent) throws android.os.RemoteException;
public void bindBuildings(com.trx.neon.binder.BuildingConnectionBinder toBind) throws android.os.RemoteException;
public void unbindBuildings(com.trx.neon.binder.BuildingConnectionBinder toUnbind) throws android.os.RemoteException;
}
