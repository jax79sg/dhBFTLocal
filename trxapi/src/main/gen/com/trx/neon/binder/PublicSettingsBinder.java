/*___Generated_by_IDEA___*/

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /media/dh/DATA4TB/zz_jax/projects/dhBFTDemo/trxapi/src/main/java/com/trx/neon/binder/PublicSettingsBinder.aidl
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

public interface PublicSettingsBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.trx.neon.binder.PublicSettingsBinder
{
private static final java.lang.String DESCRIPTOR = "com.trx.neon.binder.PublicSettingsBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.trx.neon.binder.PublicSettingsBinder interface,
 * generating a proxy if needed.
 */
public static com.trx.neon.binder.PublicSettingsBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.trx.neon.binder.PublicSettingsBinder))) {
return ((com.trx.neon.binder.PublicSettingsBinder)iin);
}
return new com.trx.neon.binder.PublicSettingsBinder.Stub.Proxy(obj);
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
case TRANSACTION_getBoolean:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.getBoolean(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setBoolean:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _arg1;
_arg1 = (0!=data.readInt());
boolean _result = this.setBoolean(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getFloat:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
float _result = this.getFloat(_arg0);
reply.writeNoException();
reply.writeFloat(_result);
return true;
}
case TRANSACTION_setFloat:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
float _arg1;
_arg1 = data.readFloat();
boolean _result = this.setFloat(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getInt:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.getInt(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setInt:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
boolean _result = this.setInt(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getLong:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
long _result = this.getLong(_arg0);
reply.writeNoException();
reply.writeLong(_result);
return true;
}
case TRANSACTION_setLong:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
long _arg1;
_arg1 = data.readLong();
boolean _result = this.setLong(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getString:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.getString(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_setString:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.setString(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getTimeSlicePolicy:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
com.trx.neon.model.config.TimeSlicePolicy _result = this.getTimeSlicePolicy(_arg0);
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
case TRANSACTION_setTimeSlicePolicy:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
com.trx.neon.model.config.TimeSlicePolicy _arg1;
if ((0!=data.readInt())) {
_arg1 = com.trx.neon.model.config.TimeSlicePolicy.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
boolean _result = this.setTimeSlicePolicy(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements com.trx.neon.binder.PublicSettingsBinder
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
@Override public boolean getBoolean(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_getBoolean, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean setBoolean(java.lang.String key, boolean value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
_data.writeInt(((value)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setBoolean, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public float getFloat(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_getFloat, _data, _reply, 0);
_reply.readException();
_result = _reply.readFloat();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean setFloat(java.lang.String key, float value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
_data.writeFloat(value);
mRemote.transact(Stub.TRANSACTION_setFloat, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getInt(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_getInt, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean setInt(java.lang.String key, int value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
_data.writeInt(value);
mRemote.transact(Stub.TRANSACTION_setInt, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public long getLong(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
long _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_getLong, _data, _reply, 0);
_reply.readException();
_result = _reply.readLong();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean setLong(java.lang.String key, long value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
_data.writeLong(value);
mRemote.transact(Stub.TRANSACTION_setLong, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getString(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_getString, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean setString(java.lang.String key, java.lang.String value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
_data.writeString(value);
mRemote.transact(Stub.TRANSACTION_setString, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public com.trx.neon.model.config.TimeSlicePolicy getTimeSlicePolicy(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.trx.neon.model.config.TimeSlicePolicy _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_getTimeSlicePolicy, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.trx.neon.model.config.TimeSlicePolicy.CREATOR.createFromParcel(_reply);
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
@Override public boolean setTimeSlicePolicy(java.lang.String key, com.trx.neon.model.config.TimeSlicePolicy policy) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
if ((policy!=null)) {
_data.writeInt(1);
policy.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setTimeSlicePolicy, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getBoolean = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setBoolean = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getFloat = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setFloat = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getInt = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_setInt = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getLong = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_setLong = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getString = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_setString = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getTimeSlicePolicy = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_setTimeSlicePolicy = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
}
public boolean getBoolean(java.lang.String key) throws android.os.RemoteException;
public boolean setBoolean(java.lang.String key, boolean value) throws android.os.RemoteException;
public float getFloat(java.lang.String key) throws android.os.RemoteException;
public boolean setFloat(java.lang.String key, float value) throws android.os.RemoteException;
public int getInt(java.lang.String key) throws android.os.RemoteException;
public boolean setInt(java.lang.String key, int value) throws android.os.RemoteException;
public long getLong(java.lang.String key) throws android.os.RemoteException;
public boolean setLong(java.lang.String key, long value) throws android.os.RemoteException;
public java.lang.String getString(java.lang.String key) throws android.os.RemoteException;
public boolean setString(java.lang.String key, java.lang.String value) throws android.os.RemoteException;
public com.trx.neon.model.config.TimeSlicePolicy getTimeSlicePolicy(java.lang.String key) throws android.os.RemoteException;
public boolean setTimeSlicePolicy(java.lang.String key, com.trx.neon.model.config.TimeSlicePolicy policy) throws android.os.RemoteException;
}
