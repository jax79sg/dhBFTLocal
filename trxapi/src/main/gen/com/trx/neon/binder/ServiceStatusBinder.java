/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /media/dh/DATA4TB/zz_jax/projects/dhBFTDemo/trxapi/src/main/java/com/trx/neon/binder/ServiceStatusBinder.aidl
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

public interface ServiceStatusBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.trx.neon.binder.ServiceStatusBinder
{
private static final java.lang.String DESCRIPTOR = "com.trx.neon.binder.ServiceStatusBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.trx.neon.binder.ServiceStatusBinder interface,
 * generating a proxy if needed.
 */
public static com.trx.neon.binder.ServiceStatusBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.trx.neon.binder.ServiceStatusBinder))) {
return ((com.trx.neon.binder.ServiceStatusBinder)iin);
}
return new com.trx.neon.binder.ServiceStatusBinder.Stub.Proxy(obj);
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
case TRANSACTION_registerConnectivityEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeConnectivityEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeConnectivityEvent.Stub.asInterface(data.readStrongBinder());
this.registerConnectivityEventUpdates(_arg0);
return true;
}
case TRANSACTION_unregisterConnectivityEventUpdates:
{
data.enforceInterface(descriptor);
com.trx.neon.binder.OnChangeConnectivityEvent _arg0;
_arg0 = com.trx.neon.binder.OnChangeConnectivityEvent.Stub.asInterface(data.readStrongBinder());
this.unregisterConnectivityEventUpdates(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.trx.neon.binder.ServiceStatusBinder
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
@Override public void registerConnectivityEventUpdates(com.trx.neon.binder.OnChangeConnectivityEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerConnectivityEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void unregisterConnectivityEventUpdates(com.trx.neon.binder.OnChangeConnectivityEvent onChange) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((onChange!=null))?(onChange.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterConnectivityEventUpdates, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_registerConnectivityEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterConnectivityEventUpdates = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void registerConnectivityEventUpdates(com.trx.neon.binder.OnChangeConnectivityEvent onChange) throws android.os.RemoteException;
public void unregisterConnectivityEventUpdates(com.trx.neon.binder.OnChangeConnectivityEvent onChange) throws android.os.RemoteException;
}
