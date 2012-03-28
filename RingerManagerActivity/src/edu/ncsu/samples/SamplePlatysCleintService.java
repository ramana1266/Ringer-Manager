package edu.ncsu.samples;

import static edu.ncsu.mas.platys.applications.constants.PlatysConstants.REMOTE_PACKAGE_NAME;
import static edu.ncsu.mas.platys.applications.constants.PlatysConstants.REMOTE_SERVICE_NAME;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;
import edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService;

public class SamplePlatysCleintService extends Service {
  static final String TAG = "SamplePlatysCleintService";
  static final List<String> currentActivities = new ArrayList<String>();
  private final IBinder mBinder = new SamplePlatysCleintBinder();
  public static Hashtable newcall = new Hashtable();

  public class SamplePlatysCleintBinder extends Binder {
    SamplePlatysCleintService getService() {
      return SamplePlatysCleintService.this;
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    return mBinder;
  }

  static final String APP_NAME = "SamplePlatysClientWithService";
  static final String APP_SUMMARY = "A sample platys client with a service";

  static final String ACTION_REQUEST_PLACE_UPDATES = "edu.ncsu.mas.samples.request_place_updates";

  static String privateKey;
  static String currentPlace;
  static List allPlaces ;
  AlarmManager am;

  @Override
  public void onCreate() {
    super.onCreate();
    am  = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if (intent.getAction() != null && intent.getAction().equals(ACTION_REQUEST_PLACE_UPDATES)) {
      Log.i(TAG, "Update current place");
      updateCurrentPlace();
    }

    return START_STICKY;
  }

  private IPlatysMiddlewareRemoteService mService = null;

  ServiceConnection mConnection = new ServiceConnection() {
  //  @Override
    public void onServiceConnected(ComponentName className, IBinder service) {
      mService = IPlatysMiddlewareRemoteService.Stub.asInterface(service);
      Log.i("","inside onServiceConnected");
      try {
    	  Log.i("","inside try ");
        privateKey = mService.registerApplication(APP_NAME, APP_SUMMARY);
        Log.i(TAG, "Private key : " + privateKey);
        schedulePlaceUpdates();
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

 //   @Override
    public void onServiceDisconnected(ComponentName className) {
      stopPlaceUpdates();
      privateKey = null;
      mService = null;
    }
  };

  public void registerWithPlatysMiddleware() {
	  Log.i("","inside registerWithPlatysMiddleware");
    Intent intent = new Intent();
    intent.setClassName(REMOTE_PACKAGE_NAME, REMOTE_SERVICE_NAME);
    bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
  }

  public void unRegisterWithPlatysMiddleware() {
    try {
      if (mService != null) {
        mService.unregisterApplication(privateKey);
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public String getCurrentPlace() {
    return currentPlace;
  }
  
  public List getAllPlaces() throws RemoteException {
	return mService.getAllPlaces(privateKey);
	  
		// TODO Auto-generated method stub
		
	}
  public List<String> getAllActivities() throws RemoteException {
	    
	  
	  return mService.getAllActivities(privateKey);
	  }
  
  public void updateCurrentPlace() {
    try {
      if (mService != null && privateKey != null) {
        currentPlace = mService.getCurrentPlace(privateKey);
        Log.i(TAG, "Current place is: " + currentPlace);

        List<String> currentactivity = mService.getCurrentActivities(privateKey);
        List activity = new ArrayList();
        
        String ringer_mode_activity ="Loud and Vibrate";
        
        for(int i =0;i<currentactivity.size();i++)
        {
        	Log.i("","acti  is " +currentactivity.get(i) );
        	 String currentact = (String) SamplePlatysClientActivity.userPrefActivity.get(currentactivity.get(i));
        	 if(currentact!=null)
        	 {
        		 Log.i("","1");
        		 if(ringer_mode_activity.equals("Lights Only"))
        		 {
        			 Log.i("","2");	 
        		 }
        		 else if(ringer_mode_activity.equals("Vibrate"))
        		 {
        			 Log.i("","3");
        			 if(currentact.equals("Lights Only"))
        			 {
        				 Log.i("","4");
        				 ringer_mode_activity="Lights Only";
        			 }
        		 }
        		 else if(ringer_mode_activity.equals("Loud and Vibrate"))
        		 {
        			 Log.i("","5");
        			 if(currentact.equals("Vibrate"))
        			 {
        				 Log.i("","6");
        				 ringer_mode_activity="Vibrate";
        			 }
        			 else if (currentact.equals("Lights Only"))
        			 {
        				 Log.i("","7");
        				 ringer_mode_activity="Lights Only";
        			 }
        		 }
        		 
        	 }
        }
        
        if(currentPlace!=null)
        {
        	Log.i("","8");
        String ringer_mode_place = (String) SamplePlatysClientActivity.userPref.get(currentPlace);
        Log.i("","ringer mode set to  " + ringer_mode_place );
        
        if(ringer_mode_place!=null)
        {
        	Log.i("","9");
        	if(ringer_mode_place.equals("Lights Only") || ringer_mode_activity.equals("Lights Only"))
        	setRingermode("Lights Only");
        	else if(ringer_mode_place.equals("Vibrate") || ringer_mode_activity.equals("Vibrate"))
        	setRingermode("Vibrate");
        	else
        	{
        		setRingermode("Loud and Vibrate");
        		
        	}
        	
        		
        }
        else if(ringer_mode_place==null)
        setRingermode(ringer_mode_activity);
        
        lookForMissedCalls(currentPlace);
        mService.getAllPlaces(privateKey);
        }
        else
        {
        	Log.i("","10");
        	setRingermode(ringer_mode_activity);
        }
        
         
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void lookForMissedCalls(String currentPlace2) {
	  
	  
	  
	// TODO Auto-generated method stub
	  final String[] projection = null;
	  final String selection = null;
	  final String[] selectionArgs = null;
	  final String sortOrder = android.provider.CallLog.Calls.DATE + " DESC";
	  final int MISSED_CALL_TYPE = android.provider.CallLog.Calls.MISSED_TYPE;
	  
	  Cursor cursor = null;
	  try{
		  
		  Log.i("","msd call");
		  cursor = getContentResolver().query(Uri.parse("content://call_log/calls"),projection,selection, selectionArgs,sortOrder);
		  while (cursor.moveToNext()) {
			  boolean isnew = false; 
	          String callLogID = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls._ID));
	          String callNumber = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER));
	          String callName = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME));
	          String callDate = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.DATE));
	          String callType = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.TYPE));
	          String isCallNew = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.NEW));
	          //String email = cursor.getString(cursor.getColumnIndex(ContactsContract. Contacts._IDEmail.DATA));
	          if((String)(newcall.get(callLogID))==null)
	          {
	        	  isnew=true;
	          }
	          if(Integer.parseInt(callType) == MISSED_CALL_TYPE && Integer.parseInt(isCallNew) > 0 && isnew==true){
	        	  
	        	  newcall.put(callLogID, "done");
	           	  Log.i("","In sampleclientservice");
	           	  Log.i("Missed Call Found: ","number is " + callNumber);
	           	  ContentResolver contentResolver = getContentResolver();
	           	Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(callNumber));
	           	String[] projection1 = new String[] {PhoneLookup.DISPLAY_NAME, PhoneLookup._ID};
	           	Cursor cursor1 =  
	           	   contentResolver.query(
	           	        uri, 
	           	        projection1, 
	           	        null, 
	           	        null, 
	           	        null);

	           	if(cursor1!=null) {
	           	  while(cursor1.moveToNext()){
	           	    String contactName = cursor1.getString(cursor1.getColumnIndexOrThrow(PhoneLookup.DISPLAY_NAME));
	           	    String contactId = cursor1.getString(cursor1.getColumnIndexOrThrow(PhoneLookup._ID));
	           	    Log.i("", "contactMatch name: " + contactName);
	           	 Log.i("", "contact name: " + callName);
	           	    Log.i("", "contactMatch id: " + contactId);
	           	 

	           	ContentResolver cr = getContentResolver();

	           	Cursor emails = cr.query(Email.CONTENT_URI, null,
                        Email.CONTACT_ID + " = " + contactId, null, null);
                while (emails.moveToNext()) {
                    String email = emails.getString(emails
                            .getColumnIndex(Email.DATA));
                    
                    
                    List socialCircles = mService.getSocialCircles(privateKey, callName);
                	List sharableSocialCircles = mService.getSharableSocialCircles(privateKey, currentPlace2);
                	boolean flag=false;
                	boolean sharable=false;
                	for(int i =0;i<socialCircles.size();i++)
                	{
                		String x = (String) socialCircles.get(i);
                		for(int j=0;j<sharableSocialCircles.size();j++)
                		{
                			if(x.equals((String)sharableSocialCircles.get(j)))
                			{
                				flag=true;
                				Log.i("", "Matched: " + callName);
                				Log.i("", "Socialcircle: " + x);
                				sharable=true;
                				break;
                			}
                		}
                		if(flag==true)
                		{	
                			if(email!=null )
                            {
                            	if(sharable==true)
                            	{
                            		Log.i("", "send place update to: " + callName);
                            		sendAnEmailToContact(email,currentPlace2);
                            	}
                            	else{
                            		Log.i("", "dont send place update to: " + callName);
                            	sendAnEmailToContact(email,"");
                            	}
                            }
                			break;
                		}
                	}
                	
                    
                    
                }
	           	  
		           	}
	           	  cursor1.close();
                
	          }
	          }}
	      }catch(Exception ex){
	      // Log.e("ERROR: " + ex.toString());
	  }finally{
	      cursor.close();
	  }

	  
}

private void sendAnEmailToContact(String email, String currentPlace2) {
	// TODO Auto-generated method stub
	Log.i("",email);
	MailSender.sendEmail(email,currentPlace2);
	
}

private void setRingermode(String ringer_mode) {
	// TODO Auto-generated method stub
	  AudioManager audioMnger = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
	  		  
	  Log.i("","setting" +ringer_mode );
	  if(ringer_mode.equals("Loud and Vibrate"))
	  audioMnger .setRingerMode(AudioManager.RINGER_MODE_NORMAL); // NORMAL
	  if(ringer_mode.equals("Lights Only"))
	  audioMnger .setRingerMode(AudioManager.RINGER_MODE_SILENT); // SILENT
	  if(ringer_mode.equals("Vibrate"))
	  audioMnger .setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	  
	
}

public void schedulePlaceUpdates() {
    Log.i(TAG, "Scheduling place updates");

    Intent intent = new Intent(this, SamplePlatysCleintService.class);
    intent.setAction(ACTION_REQUEST_PLACE_UPDATES);
    PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);

    // Repeat every 10 minutes, starting now
    am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10 * 60 * 1000, pendingIntent);
  }

  public void stopPlaceUpdates() {
    Log.i(TAG, "Stop place updates");

    Intent intent = new Intent(this, SamplePlatysCleintService.class);
    intent.setAction(ACTION_REQUEST_PLACE_UPDATES);
    PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);

    am.cancel(pendingIntent);
  }


}
