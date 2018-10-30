/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /media/dh/DATA4TB/zz_jax/projects/dhBFTDemo/trxapi/src/main/java/com/trx/neon/binder/NavigationBinder.aidl
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

public interface NavigationBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.trx.neon.binder.NavigationBinder
{
private static final java.lang.String DESCRIPTOR = "com.trx.neon.binder.NavigationBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.trx.neon.binder.NavigationBinder interface,
 * generating a proxy if needed.
 */
public static com.trx.neon.binder.NavigationBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.trx.neon.binder.NavigationBinder))) {
return ((com.trx.neon.binder.NavigationBinder)iin);
}
return new com.trx.neon.binder.NavigationBinder.Stub.Proxy(obj);
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
case TRANSACTION_userCorrect:
{
data.enforceInterface(descriptor);
com.trx.neon.model.constraint.NeonConstraint _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.constraint.NeonConstraint.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.userCorrect(_arg0);
return true;
}
case TRANSACTION_setDebugLocation:
{
data.enforceInterface(descriptor);
com.trx.neon.beta.DebugLocation _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.beta.DebugLocation.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.setDebugLocation(_arg0);
return true;
}
case TRANSACTION_registerCalibrationEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeCalibrationEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeCalibrationEvent.Stub.asInterface(data.readStrongBinder());
this.registerCalibrationEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterCalibrationEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeCalibrationEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeCalibrationEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterCalibrationEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerLocationUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationList _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationList.Stub.asInterface(data.readStrongBinder());
this.registerLocationUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterLocationUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationList _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationList.Stub.asInterface(data.readStrongBinder());
this.unregisterLocationUpdates(_arg0);
return true;
}
case TRANSACTION_registerPostureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangePostureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangePostureEvent.Stub.asInterface(data.readStrongBinder());
this.registerPostureEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterPostureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangePostureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangePostureEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterPostureEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerMotionLevelEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeMotionLevelEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeMotionLevelEvent.Stub.asInterface(data.readStrongBinder());
this.registerMotionLevelEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterMotionLevelEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeMotionLevelEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeMotionLevelEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterMotionLevelEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerFeatureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeStructuralFeatureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeStructuralFeatureEvent.Stub.asInterface(data.readStrongBinder());
this.registerFeatureEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterFeatureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeStructuralFeatureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeStructuralFeatureEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterFeatureEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerVehicleStateUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeVehicleStateEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeVehicleStateEvent.Stub.asInterface(data.readStrongBinder());
this.registerVehicleStateUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterVehicleStateUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeVehicleStateEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeVehicleStateEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterVehicleStateUpdates(_arg0);
return true;
}
case TRANSACTION_registerSessionUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeSessionEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeSessionEvent.Stub.asInterface(data.readStrongBinder());
this.registerSessionUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterSessionUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeSessionEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeSessionEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterSessionUpdates(_arg0);
return true;
}
case TRANSACTION_resyncEnvironment:
{
data.enforceInterface(descriptor);
this.resyncEnvironment();
return true;
}
case TRANSACTION_downloadArea:
{
data.enforceInterface(descriptor);
double[] _arg0;
_arg0 = data.createDoubleArray();
com.trx.neon.binder.OnChangeBoolean _arg1;
_arg1 = com.trx.neon.binder.OnChangeBoolean.Stub.asInterface(data.readStrongBinder());
this.downloadArea(_arg0, _arg1);
return true;
}
case TRANSACTION_depositExternalInertialDelta:
{
data.enforceInterface(descriptor);
com.trx.neon.model.NeonInertialDelta _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.NeonInertialDelta.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.depositExternalInertialDelta(_arg0);
return true;
}
case TRANSACTION_registerMapperFeatureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent.Stub.asInterface(data.readStrongBinder());
this.registerMapperFeatureEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterMapperFeatureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterMapperFeatureEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerRawFeatureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeRawFeatureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeRawFeatureEvent.Stub.asInterface(data.readStrongBinder());
this.registerRawFeatureEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterRawFeatureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeRawFeatureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeRawFeatureEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterRawFeatureEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerFilteredRawFeatureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeFilteredRawFeatureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeFilteredRawFeatureEvent.Stub.asInterface(data.readStrongBinder());
this.registerFilteredRawFeatureEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterFilteredRawFeatureEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeFilteredRawFeatureEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeFilteredRawFeatureEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterFilteredRawFeatureEventUpdates(_arg0);
return true;
}
case TRANSACTION_addGPSConstraint:
{
data.enforceInterface(descriptor);
com.trx.neon.model.extendedConstraint.GPSConstraint _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.extendedConstraint.GPSConstraint.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.addGPSConstraint(_arg0);
return true;
}
case TRANSACTION_addManualConstraint:
{
data.enforceInterface(descriptor);
com.trx.neon.model.extendedConstraint.ManualConstraint _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.extendedConstraint.ManualConstraint.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.addManualConstraint(_arg0);
return true;
}
case TRANSACTION_addLineSegmentConstraint:
{
data.enforceInterface(descriptor);
com.trx.neon.model.extendedConstraint.LineSegmentConstraint _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.extendedConstraint.LineSegmentConstraint.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.addLineSegmentConstraint(_arg0);
return true;
}
case TRANSACTION_addRangingConstraint:
{
data.enforceInterface(descriptor);
com.trx.neon.model.extendedConstraint.RangingConstraint _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.extendedConstraint.RangingConstraint.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.addRangingConstraint(_arg0);
return true;
}
case TRANSACTION_registerNavResetEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNavResetEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeNavResetEvent.Stub.asInterface(data.readStrongBinder());
this.registerNavResetEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterNavResetEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNavResetEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeNavResetEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterNavResetEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerLocationUpdatesV2:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationListV2 _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationListV2.Stub.asInterface(data.readStrongBinder());
this.registerLocationUpdatesV2(_arg0);
return true;
}
case TRANSACTION_unregisterLocationUpdatesV2:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationListV2 _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationListV2.Stub.asInterface(data.readStrongBinder());
this.unregisterLocationUpdatesV2(_arg0);
return true;
}
case TRANSACTION_registerNavLockEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNavLockEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeNavLockEvent.Stub.asInterface(data.readStrongBinder());
this.registerNavLockEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterNavLockEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNavLockEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeNavLockEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterNavLockEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerLocationUpdatesV3:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationListV3 _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationListV3.Stub.asInterface(data.readStrongBinder());
this.registerLocationUpdatesV3(_arg0);
return true;
}
case TRANSACTION_unregisterLocationUpdatesV3:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationListV3 _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationListV3.Stub.asInterface(data.readStrongBinder());
this.unregisterLocationUpdatesV3(_arg0);
return true;
}
case TRANSACTION_registerNewFloorEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNewFloorEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeNewFloorEvent.Stub.asInterface(data.readStrongBinder());
this.registerNewFloorEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterNewFloorEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNewFloorEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeNewFloorEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterNewFloorEventUpdates(_arg0);
return true;
}
case TRANSACTION_registerLocationUpdatesV4:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationListV4 _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationListV4.Stub.asInterface(data.readStrongBinder());
this.registerLocationUpdatesV4(_arg0);
return true;
}
case TRANSACTION_unregisterLocationUpdatesV4:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationListV4 _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationListV4.Stub.asInterface(data.readStrongBinder());
this.unregisterLocationUpdatesV4(_arg0);
return true;
}
case TRANSACTION_registerLocationUpdatesV5:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationListV5 _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationListV5.Stub.asInterface(data.readStrongBinder());
this.registerLocationUpdatesV5(_arg0);
return true;
}
case TRANSACTION_unregisterLocationUpdatesV5:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeNeonLocationListV5 _arg0;
_arg0 = com.trx.neon.binder.OnChangeNeonLocationListV5.Stub.asInterface(data.readStrongBinder());
this.unregisterLocationUpdatesV5(_arg0);
return true;
}
case TRANSACTION_registerSessionUpdatesV2:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeSessionEventV2 _arg0;
_arg0 = com.trx.neon.binder.OnChangeSessionEventV2.Stub.asInterface(data.readStrongBinder());
this.registerSessionUpdatesV2(_arg0);
return true;
}
case TRANSACTION_unregisterSessionUpdatesV2:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeSessionEventV2 _arg0;
_arg0 = com.trx.neon.binder.OnChangeSessionEventV2.Stub.asInterface(data.readStrongBinder());
this.unregisterSessionUpdatesV2(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.trx.neon.binder.NavigationBinder
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
@Override public void userCorrect(com.trx.neon.model.constraint.NeonConstraint knownLocation) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((knownLocation!=null)) {
_data.writeInt(1);
knownLocation.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_userCorrect, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void setDebugLocation(com.trx.neon.beta.DebugLocation debugLocation) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((debugLocation!=null)) {
_data.writeInt(1);
debugLocation.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setDebugLocation, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerCalibrationEventUpdates(com.trx.neon.binder.OnChangeCalibrationEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCalibrationEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterCalibrationEventUpdates(com.trx.neon.binder.OnChangeCalibrationEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCalibrationEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerLocationUpdates(com.trx.neon.binder.OnChangeNeonLocationList onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerLocationUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterLocationUpdates(com.trx.neon.binder.OnChangeNeonLocationList onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterLocationUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerPostureEventUpdates(com.trx.neon.binder.OnChangePostureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerPostureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterPostureEventUpdates(com.trx.neon.binder.OnChangePostureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterPostureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerMotionLevelEventUpdates(com.trx.neon.binder.OnChangeMotionLevelEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerMotionLevelEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterMotionLevelEventUpdates(com.trx.neon.binder.OnChangeMotionLevelEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterMotionLevelEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerFeatureEventUpdates(com.trx.neon.binder.OnChangeStructuralFeatureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerFeatureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterFeatureEventUpdates(com.trx.neon.binder.OnChangeStructuralFeatureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterFeatureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerVehicleStateUpdates(com.trx.neon.binder.OnChangeVehicleStateEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerVehicleStateUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterVehicleStateUpdates(com.trx.neon.binder.OnChangeVehicleStateEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterVehicleStateUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerSessionUpdates(com.trx.neon.binder.OnChangeSessionEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerSessionUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterSessionUpdates(com.trx.neon.binder.OnChangeSessionEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterSessionUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void resyncEnvironment() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_resyncEnvironment, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void downloadArea(double[] coordinates, com.trx.neon.binder.OnChangeBoolean onChangeBoolean) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeDoubleArray(coordinates);
_data.writeStrongBinder((((onChangeBoolean!=null))?(onChangeBoolean.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_downloadArea, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void depositExternalInertialDelta(com.trx.neon.model.NeonInertialDelta delta) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((delta!=null)) {
_data.writeInt(1);
delta.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_depositExternalInertialDelta, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerMapperFeatureEventUpdates(com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerMapperFeatureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterMapperFeatureEventUpdates(com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterMapperFeatureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerRawFeatureEventUpdates(com.trx.neon.binder.OnChangeRawFeatureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerRawFeatureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterRawFeatureEventUpdates(com.trx.neon.binder.OnChangeRawFeatureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterRawFeatureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerFilteredRawFeatureEventUpdates(com.trx.neon.binder.OnChangeFilteredRawFeatureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerFilteredRawFeatureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterFilteredRawFeatureEventUpdates(com.trx.neon.binder.OnChangeFilteredRawFeatureEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterFilteredRawFeatureEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addGPSConstraint(com.trx.neon.model.extendedConstraint.GPSConstraint constraint) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((constraint!=null)) {
_data.writeInt(1);
constraint.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addGPSConstraint, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addManualConstraint(com.trx.neon.model.extendedConstraint.ManualConstraint constraint) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((constraint!=null)) {
_data.writeInt(1);
constraint.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addManualConstraint, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addLineSegmentConstraint(com.trx.neon.model.extendedConstraint.LineSegmentConstraint constraint) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((constraint!=null)) {
_data.writeInt(1);
constraint.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addLineSegmentConstraint, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void addRangingConstraint(com.trx.neon.model.extendedConstraint.RangingConstraint constraint) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((constraint!=null)) {
_data.writeInt(1);
constraint.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addRangingConstraint, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerNavResetEventUpdates(com.trx.neon.binder.OnChangeNavResetEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerNavResetEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterNavResetEventUpdates(com.trx.neon.binder.OnChangeNavResetEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterNavResetEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerLocationUpdatesV2(com.trx.neon.binder.OnChangeNeonLocationListV2 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerLocationUpdatesV2, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterLocationUpdatesV2(com.trx.neon.binder.OnChangeNeonLocationListV2 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterLocationUpdatesV2, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerNavLockEventUpdates(com.trx.neon.binder.OnChangeNavLockEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerNavLockEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterNavLockEventUpdates(com.trx.neon.binder.OnChangeNavLockEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterNavLockEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerLocationUpdatesV3(com.trx.neon.binder.OnChangeNeonLocationListV3 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerLocationUpdatesV3, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterLocationUpdatesV3(com.trx.neon.binder.OnChangeNeonLocationListV3 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterLocationUpdatesV3, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerNewFloorEventUpdates(com.trx.neon.binder.OnChangeNewFloorEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerNewFloorEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterNewFloorEventUpdates(com.trx.neon.binder.OnChangeNewFloorEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterNewFloorEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerLocationUpdatesV4(com.trx.neon.binder.OnChangeNeonLocationListV4 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerLocationUpdatesV4, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterLocationUpdatesV4(com.trx.neon.binder.OnChangeNeonLocationListV4 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterLocationUpdatesV4, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerLocationUpdatesV5(com.trx.neon.binder.OnChangeNeonLocationListV5 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerLocationUpdatesV5, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterLocationUpdatesV5(com.trx.neon.binder.OnChangeNeonLocationListV5 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterLocationUpdatesV5, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void registerSessionUpdatesV2(com.trx.neon.binder.OnChangeSessionEventV2 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerSessionUpdatesV2, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterSessionUpdatesV2(com.trx.neon.binder.OnChangeSessionEventV2 onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterSessionUpdatesV2, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_userCorrect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setDebugLocation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerCalibrationEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregisterCalibrationEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_registerLocationUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_unregisterLocationUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_registerPostureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_unregisterPostureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_registerMotionLevelEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_unregisterMotionLevelEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_registerFeatureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_unregisterFeatureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_registerVehicleStateUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_unregisterVehicleStateUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_registerSessionUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_unregisterSessionUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_resyncEnvironment = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_downloadArea = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_depositExternalInertialDelta = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_registerMapperFeatureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_unregisterMapperFeatureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_registerRawFeatureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_unregisterRawFeatureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_registerFilteredRawFeatureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_unregisterFilteredRawFeatureEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_addGPSConstraint = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_addManualConstraint = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_addLineSegmentConstraint = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
static final int TRANSACTION_addRangingConstraint = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
static final int TRANSACTION_registerNavResetEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
static final int TRANSACTION_unregisterNavResetEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 30);
static final int TRANSACTION_registerLocationUpdatesV2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 31);
static final int TRANSACTION_unregisterLocationUpdatesV2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 32);
static final int TRANSACTION_registerNavLockEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 33);
static final int TRANSACTION_unregisterNavLockEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 34);
static final int TRANSACTION_registerLocationUpdatesV3 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 35);
static final int TRANSACTION_unregisterLocationUpdatesV3 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 36);
static final int TRANSACTION_registerNewFloorEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 37);
static final int TRANSACTION_unregisterNewFloorEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 38);
static final int TRANSACTION_registerLocationUpdatesV4 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 39);
static final int TRANSACTION_unregisterLocationUpdatesV4 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 40);
static final int TRANSACTION_registerLocationUpdatesV5 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 41);
static final int TRANSACTION_unregisterLocationUpdatesV5 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 42);
static final int TRANSACTION_registerSessionUpdatesV2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 43);
static final int TRANSACTION_unregisterSessionUpdatesV2 = (android.os.IBinder.FIRST_CALL_TRANSACTION + 44);
}
public void userCorrect(com.trx.neon.model.constraint.NeonConstraint knownLocation) throws android.os.RemoteException;
public void setDebugLocation(com.trx.neon.beta.DebugLocation debugLocation) throws android.os.RemoteException;
public void registerCalibrationEventUpdates(com.trx.neon.binder.OnChangeCalibrationEvent onChange) throws android.os.RemoteException;
public void unregisterCalibrationEventUpdates(com.trx.neon.binder.OnChangeCalibrationEvent onChange) throws android.os.RemoteException;
public void registerLocationUpdates(com.trx.neon.binder.OnChangeNeonLocationList onChange) throws android.os.RemoteException;
public void unregisterLocationUpdates(com.trx.neon.binder.OnChangeNeonLocationList onChange) throws android.os.RemoteException;
public void registerPostureEventUpdates(com.trx.neon.binder.OnChangePostureEvent onChange) throws android.os.RemoteException;
public void unregisterPostureEventUpdates(com.trx.neon.binder.OnChangePostureEvent onChange) throws android.os.RemoteException;
public void registerMotionLevelEventUpdates(com.trx.neon.binder.OnChangeMotionLevelEvent onChange) throws android.os.RemoteException;
public void unregisterMotionLevelEventUpdates(com.trx.neon.binder.OnChangeMotionLevelEvent onChange) throws android.os.RemoteException;
public void registerFeatureEventUpdates(com.trx.neon.binder.OnChangeStructuralFeatureEvent onChange) throws android.os.RemoteException;
public void unregisterFeatureEventUpdates(com.trx.neon.binder.OnChangeStructuralFeatureEvent onChange) throws android.os.RemoteException;
public void registerVehicleStateUpdates(com.trx.neon.binder.OnChangeVehicleStateEvent onChange) throws android.os.RemoteException;
public void unregisterVehicleStateUpdates(com.trx.neon.binder.OnChangeVehicleStateEvent onChange) throws android.os.RemoteException;
public void registerSessionUpdates(com.trx.neon.binder.OnChangeSessionEvent onChange) throws android.os.RemoteException;
public void unregisterSessionUpdates(com.trx.neon.binder.OnChangeSessionEvent onChange) throws android.os.RemoteException;
public void resyncEnvironment() throws android.os.RemoteException;
public void downloadArea(double[] coordinates, com.trx.neon.binder.OnChangeBoolean onChangeBoolean) throws android.os.RemoteException;
public void depositExternalInertialDelta(com.trx.neon.model.NeonInertialDelta delta) throws android.os.RemoteException;
public void registerMapperFeatureEventUpdates(com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent onChange) throws android.os.RemoteException;
public void unregisterMapperFeatureEventUpdates(com.trx.neon.binder.OnChangeMapperStructuralFeatureEvent onChange) throws android.os.RemoteException;
public void registerRawFeatureEventUpdates(com.trx.neon.binder.OnChangeRawFeatureEvent onChange) throws android.os.RemoteException;
public void unregisterRawFeatureEventUpdates(com.trx.neon.binder.OnChangeRawFeatureEvent onChange) throws android.os.RemoteException;
public void registerFilteredRawFeatureEventUpdates(com.trx.neon.binder.OnChangeFilteredRawFeatureEvent onChange) throws android.os.RemoteException;
public void unregisterFilteredRawFeatureEventUpdates(com.trx.neon.binder.OnChangeFilteredRawFeatureEvent onChange) throws android.os.RemoteException;
public void addGPSConstraint(com.trx.neon.model.extendedConstraint.GPSConstraint constraint) throws android.os.RemoteException;
public void addManualConstraint(com.trx.neon.model.extendedConstraint.ManualConstraint constraint) throws android.os.RemoteException;
public void addLineSegmentConstraint(com.trx.neon.model.extendedConstraint.LineSegmentConstraint constraint) throws android.os.RemoteException;
public void addRangingConstraint(com.trx.neon.model.extendedConstraint.RangingConstraint constraint) throws android.os.RemoteException;
public void registerNavResetEventUpdates(com.trx.neon.binder.OnChangeNavResetEvent onChange) throws android.os.RemoteException;
public void unregisterNavResetEventUpdates(com.trx.neon.binder.OnChangeNavResetEvent onChange) throws android.os.RemoteException;
public void registerLocationUpdatesV2(com.trx.neon.binder.OnChangeNeonLocationListV2 onChange) throws android.os.RemoteException;
public void unregisterLocationUpdatesV2(com.trx.neon.binder.OnChangeNeonLocationListV2 onChange) throws android.os.RemoteException;
public void registerNavLockEventUpdates(com.trx.neon.binder.OnChangeNavLockEvent onChange) throws android.os.RemoteException;
public void unregisterNavLockEventUpdates(com.trx.neon.binder.OnChangeNavLockEvent onChange) throws android.os.RemoteException;
public void registerLocationUpdatesV3(com.trx.neon.binder.OnChangeNeonLocationListV3 onChange) throws android.os.RemoteException;
public void unregisterLocationUpdatesV3(com.trx.neon.binder.OnChangeNeonLocationListV3 onChange) throws android.os.RemoteException;
public void registerNewFloorEventUpdates(com.trx.neon.binder.OnChangeNewFloorEvent onChange) throws android.os.RemoteException;
public void unregisterNewFloorEventUpdates(com.trx.neon.binder.OnChangeNewFloorEvent onChange) throws android.os.RemoteException;
public void registerLocationUpdatesV4(com.trx.neon.binder.OnChangeNeonLocationListV4 onChange) throws android.os.RemoteException;
public void unregisterLocationUpdatesV4(com.trx.neon.binder.OnChangeNeonLocationListV4 onChange) throws android.os.RemoteException;
public void registerLocationUpdatesV5(com.trx.neon.binder.OnChangeNeonLocationListV5 onChange) throws android.os.RemoteException;
public void unregisterLocationUpdatesV5(com.trx.neon.binder.OnChangeNeonLocationListV5 onChange) throws android.os.RemoteException;
public void registerSessionUpdatesV2(com.trx.neon.binder.OnChangeSessionEventV2 onChange) throws android.os.RemoteException;
public void unregisterSessionUpdatesV2(com.trx.neon.binder.OnChangeSessionEventV2 onChange) throws android.os.RemoteException;
}
