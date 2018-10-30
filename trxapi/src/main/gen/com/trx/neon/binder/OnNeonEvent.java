/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /media/dh/DATA4TB/zz_jax/projects/dhBFTDemo/trxapi/src/main/java/com/trx/neon/binder/OnNeonEvent.aidl
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

public interface OnNeonEvent extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.trx.neon.binder.OnNeonEvent
{
private static final java.lang.String DESCRIPTOR = "com.trx.neon.binder.OnNeonEvent";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.trx.neon.binder.OnNeonEvent interface,
 * generating a proxy if needed.
 */
public static com.trx.neon.binder.OnNeonEvent asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.trx.neon.binder.OnNeonEvent))) {
return ((com.trx.neon.binder.OnNeonEvent)iin);
}
return new com.trx.neon.binder.OnNeonEvent.Stub.Proxy(obj);
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
case TRANSACTION_onBatteryEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.BatteryEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.BatteryEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onBatteryEvent(_arg0);
return true;
}
case TRANSACTION_onCalibrationEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.CalibrationEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.CalibrationEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onCalibrationEvent(_arg0);
return true;
}
case TRANSACTION_onConnectivityEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.ConnectivityEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.ConnectivityEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onConnectivityEvent(_arg0);
return true;
}
case TRANSACTION_onMotionLevelEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.MotionLevelEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.MotionLevelEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onMotionLevelEvent(_arg0);
return true;
}
case TRANSACTION_onPostureEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.PostureEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.PostureEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onPostureEvent(_arg0);
return true;
}
case TRANSACTION_onRemoteRangeEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.RemoteRangeEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.RemoteRangeEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onRemoteRangeEvent(_arg0);
return true;
}
case TRANSACTION_onSafetyAlertEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.SafetyAlertEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.SafetyAlertEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onSafetyAlertEvent(_arg0);
return true;
}
case TRANSACTION_onSessionEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.legacy.SessionEventLegacy _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.legacy.SessionEventLegacy.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onSessionEvent(_arg0);
return true;
}
case TRANSACTION_onStructuralFeatureEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.StructuralFeatureEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.StructuralFeatureEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onStructuralFeatureEvent(_arg0);
return true;
}
case TRANSACTION_onUpdateAvailableEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.UpdateAvailableEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.UpdateAvailableEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onUpdateAvailableEvent(_arg0);
return true;
}
case TRANSACTION_onVehicleStateEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.VehicleStateEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.VehicleStateEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onVehicleStateEvent(_arg0);
return true;
}
case TRANSACTION_onMapperStructuralFeatureEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.MapperStructuralFeatureEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.MapperStructuralFeatureEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onMapperStructuralFeatureEvent(_arg0);
return true;
}
case TRANSACTION_onNavResetEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.NavResetEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.NavResetEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onNavResetEvent(_arg0);
return true;
}
case TRANSACTION_onNavLockEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.NavLockEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.NavLockEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onNavLockEvent(_arg0);
return true;
}
case TRANSACTION_onNewFloorEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.NewFloorEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.NewFloorEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onNewFloorEvent(_arg0);
return true;
}
case TRANSACTION_onSessionEventV2:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.SessionEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.SessionEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onSessionEventV2(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.trx.neon.binder.OnNeonEvent
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
@Override public void onBatteryEvent(com.trx.neon.model.event.BatteryEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onBatteryEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onCalibrationEvent(com.trx.neon.model.event.CalibrationEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onCalibrationEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onConnectivityEvent(com.trx.neon.model.event.ConnectivityEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onConnectivityEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onMotionLevelEvent(com.trx.neon.model.event.MotionLevelEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onMotionLevelEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onPostureEvent(com.trx.neon.model.event.PostureEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onPostureEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onRemoteRangeEvent(com.trx.neon.model.event.RemoteRangeEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onRemoteRangeEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onSafetyAlertEvent(com.trx.neon.model.event.SafetyAlertEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onSafetyAlertEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onSessionEvent(com.trx.neon.model.legacy.SessionEventLegacy ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onSessionEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onStructuralFeatureEvent(com.trx.neon.model.event.StructuralFeatureEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onStructuralFeatureEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onUpdateAvailableEvent(com.trx.neon.model.event.UpdateAvailableEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onUpdateAvailableEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onVehicleStateEvent(com.trx.neon.model.event.VehicleStateEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onVehicleStateEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onMapperStructuralFeatureEvent(com.trx.neon.model.event.MapperStructuralFeatureEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onMapperStructuralFeatureEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onNavResetEvent(com.trx.neon.model.event.NavResetEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onNavResetEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onNavLockEvent(com.trx.neon.model.event.NavLockEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onNavLockEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onNewFloorEvent(com.trx.neon.model.event.NewFloorEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onNewFloorEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onSessionEventV2(com.trx.neon.model.event.SessionEvent ev) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((ev!=null)) {
_data.writeInt(1);
ev.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onSessionEventV2, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onBatteryEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onCalibrationEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onConnectivityEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_onMotionLevelEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_onPostureEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_onRemoteRangeEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_onSafetyAlertEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_onSessionEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_onStructuralFeatureEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_onUpdateAvailableEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_onVehicleStateEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_onMapperStructuralFeatureEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_onNavResetEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_onNavLockEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_onNewFloorEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_onSessionEventV2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
}
public void onBatteryEvent(com.trx.neon.model.event.BatteryEvent ev) throws android.os.RemoteException;
public void onCalibrationEvent(com.trx.neon.model.event.CalibrationEvent ev) throws android.os.RemoteException;
public void onConnectivityEvent(com.trx.neon.model.event.ConnectivityEvent ev) throws android.os.RemoteException;
public void onMotionLevelEvent(com.trx.neon.model.event.MotionLevelEvent ev) throws android.os.RemoteException;
public void onPostureEvent(com.trx.neon.model.event.PostureEvent ev) throws android.os.RemoteException;
public void onRemoteRangeEvent(com.trx.neon.model.event.RemoteRangeEvent ev) throws android.os.RemoteException;
public void onSafetyAlertEvent(com.trx.neon.model.event.SafetyAlertEvent ev) throws android.os.RemoteException;
public void onSessionEvent(com.trx.neon.model.legacy.SessionEventLegacy ev) throws android.os.RemoteException;
public void onStructuralFeatureEvent(com.trx.neon.model.event.StructuralFeatureEvent ev) throws android.os.RemoteException;
public void onUpdateAvailableEvent(com.trx.neon.model.event.UpdateAvailableEvent ev) throws android.os.RemoteException;
public void onVehicleStateEvent(com.trx.neon.model.event.VehicleStateEvent ev) throws android.os.RemoteException;
public void onMapperStructuralFeatureEvent(com.trx.neon.model.event.MapperStructuralFeatureEvent ev) throws android.os.RemoteException;
public void onNavResetEvent(com.trx.neon.model.event.NavResetEvent ev) throws android.os.RemoteException;
public void onNavLockEvent(com.trx.neon.model.event.NavLockEvent ev) throws android.os.RemoteException;
public void onNewFloorEvent(com.trx.neon.model.event.NewFloorEvent ev) throws android.os.RemoteException;
public void onSessionEventV2(com.trx.neon.model.event.SessionEvent ev) throws android.os.RemoteException;
}
