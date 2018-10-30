/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /media/dh/DATA4TB/zz_jax/projects/dhBFTDemo/trxapi/src/main/java/com/trx/neon/binder/OnChangeVehicleStateEvent.aidl
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

public interface OnChangeVehicleStateEvent extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.trx.neon.binder.OnChangeVehicleStateEvent
{
private static final java.lang.String DESCRIPTOR = "com.trx.neon.binder.OnChangeVehicleStateEvent";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.trx.neon.binder.OnChangeVehicleStateEvent interface,
 * generating a proxy if needed.
 */
public static com.trx.neon.binder.OnChangeVehicleStateEvent asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.trx.neon.binder.OnChangeVehicleStateEvent))) {
return ((com.trx.neon.binder.OnChangeVehicleStateEvent)iin);
}
return new com.trx.neon.binder.OnChangeVehicleStateEvent.Stub.Proxy(obj);
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
case TRANSACTION_onChange:
{
data.enforceInterface(descriptor);
com.trx.neon.model.event.VehicleStateEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.model.event.VehicleStateEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onChange(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.trx.neon.binder.OnChangeVehicleStateEvent
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
@Override public void onChange(com.trx.neon.model.event.VehicleStateEvent vse) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((vse!=null)) {
_data.writeInt(1);
vse.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onChange, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onChange = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onChange(com.trx.neon.model.event.VehicleStateEvent vse) throws android.os.RemoteException;
}
