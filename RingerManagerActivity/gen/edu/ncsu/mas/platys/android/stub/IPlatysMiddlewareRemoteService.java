/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\malladi\\soc_android_rms\\RingerManagerActivity\\src\\edu\\ncsu\\mas\\platys\\android\\stub\\IPlatysMiddlewareRemoteService.aidl
 */
package edu.ncsu.mas.platys.android.stub;
/**
 * An interface exposing the services provided by the Platys middleware.
 * Applications need to bind to the middleware and invoke appropriate functions.
 * 
 * @author Pradeep Murukannaiah
 */
public interface IPlatysMiddlewareRemoteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService
{
private static final java.lang.String DESCRIPTOR = "edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService interface,
 * generating a proxy if needed.
 */
public static edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService))) {
return ((edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService)iin);
}
return new edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_registerApplication:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _result = this.registerApplication(_arg0, _arg1);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_unregisterApplication:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.unregisterApplication(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getAplicationStatus:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.getAplicationStatus(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getCurrentPlace:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.getCurrentPlace(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getCurrentActivities:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<java.lang.String> _result = this.getCurrentActivities(_arg0);
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_getAllPlaces:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<java.lang.String> _result = this.getAllPlaces(_arg0);
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_getAllActivities:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<java.lang.String> _result = this.getAllActivities(_arg0);
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_getSocialCircles:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.util.List<java.lang.String> _result = this.getSocialCircles(_arg0, _arg1);
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_getSharableSocialCircles:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.util.List<java.lang.String> _result = this.getSharableSocialCircles(_arg0, _arg1);
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
   * Registers the application with the middleware. It returns immediately with
   * a private key for the application. The application should use the private key for all
   * further communication with the middleware.
   * 
   * @param name
   *          Unique name of the application. Platys middleware includes this name 
   *          in all broadcast messages directed to this application.
   * @param description
   *          A brief description of the application shown to the user.
   * @return privateKey - private key for the application. Platys generates a unique
   *         private key for applications. Applications should use this key for
   *         communicating with Platy middleware.
   */
public java.lang.String registerApplication(java.lang.String name, java.lang.String description) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
_data.writeString(description);
mRemote.transact(Stub.TRANSACTION_registerApplication, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Unregisters the application.
   * 
   * @param privateKey
   *          Private key of the applicaion
   */
public void unregisterApplication(java.lang.String privateKey) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(privateKey);
mRemote.transact(Stub.TRANSACTION_unregisterApplication, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
   * Returns the status of the application.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return applicationStatus - applicaion status; one of the values in
   *         {@link edu.ncsu.mas.platys.applications.constants.ApplicationStatus} as String.
   */
public java.lang.String getAplicationStatus(java.lang.String privateKey) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(privateKey);
mRemote.transact(Stub.TRANSACTION_getAplicationStatus, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Get the current place.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return current place, if user's privacy preferences allow the applications
   *         to access it; null, otherwise.
   */
public java.lang.String getCurrentPlace(java.lang.String privateKey) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(privateKey);
mRemote.transact(Stub.TRANSACTION_getCurrentPlace, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Get the current activities.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of current activities; an empty list is returned if there are
   *         no activities the application can access.
   */
public java.util.List<java.lang.String> getCurrentActivities(java.lang.String privateKey) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(privateKey);
mRemote.transact(Stub.TRANSACTION_getCurrentActivities, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Get the list of all places the application is allowed to access.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of all places the application can access.
   */
public java.util.List<java.lang.String> getAllPlaces(java.lang.String privateKey) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(privateKey);
mRemote.transact(Stub.TRANSACTION_getAllPlaces, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Get the list of all activities the application is allowed to access.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of all activities the application can access.
   */
public java.util.List<java.lang.String> getAllActivities(java.lang.String privateKey) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(privateKey);
mRemote.transact(Stub.TRANSACTION_getAllActivities, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Get the list of social circles a connection (i.e., a friend) of the user
   * belongs to.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @param connectionName
   *          Name of connection whose social circles are to be retrieved
   * @return list of social circles to which the connection belongs; an empty
   *         list is returned if the social circles are unknown.
   */
public java.util.List<java.lang.String> getSocialCircles(java.lang.String privateKey, java.lang.String connectionName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(privateKey);
_data.writeString(connectionName);
mRemote.transact(Stub.TRANSACTION_getSocialCircles, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Get the list of social circles with which a place or activity information
   * can be shared. Currently, this feature is not implemented and the function
   * returns a list of all social circles.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @param placeOrActivityName
   *          Name of a place or an activity
   * @return list of social circles to which the place or the activity
   *         information can be shared.
   */
public java.util.List<java.lang.String> getSharableSocialCircles(java.lang.String privateKey, java.lang.String placeOrActivityName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(privateKey);
_data.writeString(placeOrActivityName);
mRemote.transact(Stub.TRANSACTION_getSharableSocialCircles, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_registerApplication = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterApplication = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getAplicationStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getCurrentPlace = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getCurrentActivities = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getAllPlaces = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getAllActivities = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getSocialCircles = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getSharableSocialCircles = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
}
/**
   * Registers the application with the middleware. It returns immediately with
   * a private key for the application. The application should use the private key for all
   * further communication with the middleware.
   * 
   * @param name
   *          Unique name of the application. Platys middleware includes this name 
   *          in all broadcast messages directed to this application.
   * @param description
   *          A brief description of the application shown to the user.
   * @return privateKey - private key for the application. Platys generates a unique
   *         private key for applications. Applications should use this key for
   *         communicating with Platy middleware.
   */
public java.lang.String registerApplication(java.lang.String name, java.lang.String description) throws android.os.RemoteException;
/**
   * Unregisters the application.
   * 
   * @param privateKey
   *          Private key of the applicaion
   */
public void unregisterApplication(java.lang.String privateKey) throws android.os.RemoteException;
/**
   * Returns the status of the application.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return applicationStatus - applicaion status; one of the values in
   *         {@link edu.ncsu.mas.platys.applications.constants.ApplicationStatus} as String.
   */
public java.lang.String getAplicationStatus(java.lang.String privateKey) throws android.os.RemoteException;
/**
   * Get the current place.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return current place, if user's privacy preferences allow the applications
   *         to access it; null, otherwise.
   */
public java.lang.String getCurrentPlace(java.lang.String privateKey) throws android.os.RemoteException;
/**
   * Get the current activities.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of current activities; an empty list is returned if there are
   *         no activities the application can access.
   */
public java.util.List<java.lang.String> getCurrentActivities(java.lang.String privateKey) throws android.os.RemoteException;
/**
   * Get the list of all places the application is allowed to access.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of all places the application can access.
   */
public java.util.List<java.lang.String> getAllPlaces(java.lang.String privateKey) throws android.os.RemoteException;
/**
   * Get the list of all activities the application is allowed to access.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @return list of all activities the application can access.
   */
public java.util.List<java.lang.String> getAllActivities(java.lang.String privateKey) throws android.os.RemoteException;
/**
   * Get the list of social circles a connection (i.e., a friend) of the user
   * belongs to.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @param connectionName
   *          Name of connection whose social circles are to be retrieved
   * @return list of social circles to which the connection belongs; an empty
   *         list is returned if the social circles are unknown.
   */
public java.util.List<java.lang.String> getSocialCircles(java.lang.String privateKey, java.lang.String connectionName) throws android.os.RemoteException;
/**
   * Get the list of social circles with which a place or activity information
   * can be shared. Currently, this feature is not implemented and the function
   * returns a list of all social circles.
   * 
   * @param privateKey
   *          Private key of the applicaion
   * @param placeOrActivityName
   *          Name of a place or an activity
   * @return list of social circles to which the place or the activity
   *         information can be shared.
   */
public java.util.List<java.lang.String> getSharableSocialCircles(java.lang.String privateKey, java.lang.String placeOrActivityName) throws android.os.RemoteException;
}
