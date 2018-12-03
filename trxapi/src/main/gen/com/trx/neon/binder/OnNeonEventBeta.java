/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\User\\StudioProjects\\dhBFTLocal\\trxapi\\src\\main\\java\\com\\trx\\neon\\binder\\OnNeonEventBeta.aidl
 */
package com.trx.neon.binder;
//Notice:  FEEL FREE TO BREAK BACKWARDS COMPATIBILITY HERE!
//Anyone using a beta API agrees to be punished whenever we change things.
//Good lord trying to maintain a backwards compatible aidl interface with support
//for new features is nerve wracking.  I think this method is legit.  Here's hoping it
//doesn't blow up 3 months later.

public interface OnNeonEventBeta extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.trx.neon.binder.OnNeonEventBeta
{
private static final java.lang.String DESCRIPTOR = "com.trx.neon.binder.OnNeonEventBeta";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.trx.neon.binder.OnNeonEventBeta interface,
 * generating a proxy if needed.
 */
public static com.trx.neon.binder.OnNeonEventBeta asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.trx.neon.binder.OnNeonEventBeta))) {
return ((com.trx.neon.binder.OnNeonEventBeta)iin);
}
return new com.trx.neon.binder.OnNeonEventBeta.Stub.Proxy(obj);
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
case TRANSACTION_onUserActivityEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.beta.UserActivityEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.beta.UserActivityEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onUserActivityEvent(_arg0);
return true;
}
case TRANSACTION_onWarfighterEvent:
{
data.enforceInterface(descriptor);
com.trx.neon.beta.WarfighterEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.trx.neon.beta.WarfighterEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onWarfighterEvent(_arg0);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.trx.neon.binder.OnNeonEventBeta
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
@Override public void onUserActivityEvent(com.trx.neon.beta.UserActivityEvent ev) throws android.os.RemoteException
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
mRemote.transact(Stub.TRANSACTION_onUserActivityEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
@Override public void onWarfighterEvent(com.trx.neon.beta.WarfighterEvent ev) throws android.os.RemoteException
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
mRemote.transact(Stub.TRANSACTION_onWarfighterEvent, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onUserActivityEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onWarfighterEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void onUserActivityEvent(com.trx.neon.beta.UserActivityEvent ev) throws android.os.RemoteException;
public void onWarfighterEvent(com.trx.neon.beta.WarfighterEvent ev) throws android.os.RemoteException;
}
