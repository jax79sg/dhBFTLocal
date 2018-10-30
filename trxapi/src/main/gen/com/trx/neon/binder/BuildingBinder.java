/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /media/dh/DATA4TB/zz_jax/projects/dhBFTDemo/trxapi/src/main/java/com/trx/neon/binder/BuildingBinder.aidl
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

public interface BuildingBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.trx.neon.binder.BuildingBinder
{
private static final java.lang.String DESCRIPTOR = "com.trx.neon.binder.BuildingBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.trx.neon.binder.BuildingBinder interface,
 * generating a proxy if needed.
 */
public static com.trx.neon.binder.BuildingBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.trx.neon.binder.BuildingBinder))) {
return ((com.trx.neon.binder.BuildingBinder)iin);
}
return new com.trx.neon.binder.BuildingBinder.Stub.Proxy(obj);
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
case TRANSACTION_getBuilding:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
com.trx.neon.model.building.NeonBuilding _result = this.getBuilding(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getBuildings:
{
data.enforceInterface(descriptor);
com.trx.neon.model.building.LatLongRect _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.building.LatLongRect.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
java.util.List<com.trx.neon.model.building.NeonBuilding> _result = this.getBuildings(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_downloadBuildingsInArea:
{
data.enforceInterface(descriptor);
com.trx.neon.model.building.LatLongRect _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.building.LatLongRect.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
com.trx.neon.binder.OnBuildingsCallback _arg1;
_arg1 = com.trx.neon.binder.OnBuildingsCallback.Stub.asInterface(data.readStrongBinder());
com.trx.neon.model.DownloadOptions _arg2;
if ((0!=data.readInt())) {
_arg2 = com.trx.neon.model.DownloadOptions.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.downloadBuildingsInArea(_arg0, _arg1, _arg2);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.trx.neon.binder.BuildingBinder
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
@Override public com.trx.neon.model.building.NeonBuilding getBuilding(java.lang.String buildingID) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.trx.neon.model.building.NeonBuilding _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(buildingID);
mRemote.transact(Stub.TRANSACTION_getBuilding, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.trx.neon.model.building.NeonBuilding.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<com.trx.neon.model.building.NeonBuilding> getBuildings(com.trx.neon.model.building.LatLongRect bounds) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<com.trx.neon.model.building.NeonBuilding> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((bounds!=null)) {
_data.writeInt(1);
bounds.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_getBuildings, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(com.trx.neon.model.building.NeonBuilding.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void downloadBuildingsInArea(com.trx.neon.model.building.LatLongRect bounds, com.trx.neon.binder.OnBuildingsCallback callback, com.trx.neon.model.DownloadOptions options) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((bounds!=null)) {
_data.writeInt(1);
bounds.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
if ((options!=null)) {
_data.writeInt(1);
options.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_downloadBuildingsInArea, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_getBuilding = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getBuildings = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_downloadBuildingsInArea = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public com.trx.neon.model.building.NeonBuilding getBuilding(java.lang.String buildingID) throws android.os.RemoteException;
public java.util.List<com.trx.neon.model.building.NeonBuilding> getBuildings(com.trx.neon.model.building.LatLongRect bounds) throws android.os.RemoteException;
public void downloadBuildingsInArea(com.trx.neon.model.building.LatLongRect bounds, com.trx.neon.binder.OnBuildingsCallback callback, com.trx.neon.model.DownloadOptions options) throws android.os.RemoteException;
}
