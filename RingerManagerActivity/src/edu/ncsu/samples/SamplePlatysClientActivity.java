package edu.ncsu.samples;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.sax.Element;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import edu.ncsu.mas.platys.android.stub.IPlatysMiddlewareRemoteService;
import edu.ncsu.samples.SamplePlatysCleintService.SamplePlatysCleintBinder;
import android.widget.*;

/**
 * Similar to the SamplePlatysClient project, but with a Service requesting for
 * place updates. As a consequence, once you click on the register in the
 * activity the background service keeps requesting location updates
 * periodically. The service lives and updates place information as long as it
 * is explicitly stopped.
 *
 * Note that the SamplePlatysClientActivity can bind to the local service using
 * a local binder to get updated place.
 *
 * @author Pradeep Murukannaiah
 */
public class SamplePlatysClientActivity extends Activity {
  public SamplePlatysCleintService mService;
  boolean mBound = false;
  boolean isInSettings=false;
  boolean isInSettings1=false;
  public static Hashtable userPref = new Hashtable();
  public static Hashtable userPrefActivity = new Hashtable();
   /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
	  
	  Log.i("","in oncreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    startService(new Intent(this, SamplePlatysCleintService.class));
    
    Button b = (Button) findViewById(R.id.emailOptions);
    b.setOnClickListener(new View.OnClickListener() {
    public void onClick(View v) {
    	try {
    		Log.i("","1");
    		Intent in = new Intent(v.getContext(), emailOptionsActivity.class);
    		Log.i("","1");
            startActivity(in);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	}
});
  }

  @Override
  public void onStart() {
    super.onStart();
    Intent intent = new Intent(this, SamplePlatysCleintService.class);
    bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    
  }

  @Override
  public void onStop() {
    super.onStop();
    if (mBound) {
      unbindService(mConnection);
      mBound = false;
    }
  }
  @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && (isInSettings==true || isInSettings1 == true)) {
	    	Intent in = new Intent(this, SamplePlatysClientActivity.class);
    		startActivity(in);
	    	isInSettings=false;
	    	isInSettings1=false;
	    	return true;
	    }
	    else if ((keyCode == KeyEvent.KEYCODE_MENU) && (isInSettings==true || isInSettings1 == true)) {
	    	//isInSettings=false;
	    	return true;
	    } 
	    return super.onKeyDown(keyCode, event);
	    
	}

  
  /** Defines callbacks for service binding, passed to bindService() */
  private final ServiceConnection mConnection = new ServiceConnection() {

//    @Override
    public void onServiceConnected(ComponentName className, IBinder service) {
      SamplePlatysCleintBinder binder = (SamplePlatysCleintBinder) service;
      mService = binder.getService();
      mBound = true;
      mService.getCurrentPlace();
      mService.registerWithPlatysMiddleware();
      
    }

  //  @Override
    public void onServiceDisconnected(ComponentName arg0) {
      mBound = false;
    }
  };

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    
    
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
	  TextView placeResponseView = (TextView) findViewById(R.id.placeResponseTextView);
    super.onOptionsItemSelected(item);
    switch (item.getItemId()) {
    case R.id.register:
      if (mBound) {
    	  Log.i("","inside switch if");
        mService.registerWithPlatysMiddleware();
        
      }
      break;
    case R.id.unregister:
      if (mBound) {
        mService.registerWithPlatysMiddleware();
      }
      break;
    case R.id.requestPlace:
      String place = null;
      if (mBound) {
        place = mService.getCurrentPlace();
        
      }
       //
      if (place != null && !place.equals("")) {
        placeResponseView.setText(place);
        } else {
        placeResponseView.setText("Unknown");
      }
      break;
    case R.id.Settings:
       	
    	try {
    		isInSettings = true;
    		setContentView(R.layout.settings);
    	//	TextView allPlacesTextView = (TextView) findViewById(R.id.allPlacesTextView);
    		//allPlacesTextView.setText("Please give your Ringer Mode preference for each location\n");
    		//String allplaces1=""; 
    		

    		final List allPlaces = mService.getAllPlaces();
    		 
    		List ringer_modes=new ArrayList<String>();
    		ringer_modes.add("Loud and Vibrate");
    		
    		ringer_modes.add("Vibrate");
    		ringer_modes.add("Lights Only");
    		
    		final Hashtable userInputs = new Hashtable();
    	LinearLayout ll = new LinearLayout(this);
    		ll.setOrientation(LinearLayout.VERTICAL);
    		
    		ScrollView sv = new ScrollView(this);
    		
    		TextView tv=new TextView(this);
    		  tv.setText("Please give your Ringer Mode preference for each location\n");
    	    ll.addView(tv);
    	    sv.removeAllViews();
    	    sv.addView(ll);
    	    
    	    for(int i =0;i<allPlaces.size();i++)
      		{
    	    	
      			TextView tv1 = new TextView(this);
      			tv1.setText((CharSequence) allPlaces.get(i)+"\n");
      			ll.addView(tv1);
      			
      			Log.i("all places","all places "+ allPlaces.get(i));
      			Spinner spinner = new Spinner(this);
      		//Make sure you have valid layout parameters.
      		    spinner.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
      		    ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
      		    android.R.layout.simple_spinner_dropdown_item,ringer_modes);
      		    spinner.setAdapter(spinnerArrayAdapter);
      		    
      		    String mode = (String) userPref.get(allPlaces.get(i));
      		    
      		    if(mode==null)
      		    {
      		    	int spinnerPosition = spinnerArrayAdapter.getPosition("Loud and Vibrate");
          		    spinner.setSelection(spinnerPosition);
      		    }
      		    else{
      		    	int spinnerPosition = spinnerArrayAdapter.getPosition(mode);
      		    	spinner.setSelection(spinnerPosition);
      		    }
      		    
      		    
      		 
      		    	

      		  //set the default according to value
      		      		    
      		    ll.addView(spinner);
      		  //this.setContentView(sv);
      		    userInputs.put(allPlaces.get(i), spinner);
      		}
    	    Button saveButton = new Button(this); 
    	    saveButton.setText("Save"); 
            ll.addView(saveButton);
            
            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	try {
                		isInSettings=false;
                	Log.i("","on button click");
                	for(int i=0;i<userInputs.size();i++)
                	{
                   	Spinner x= (Spinner) userInputs.get(allPlaces.get(i));
              
                	String currentPlace = (String) allPlaces.get(i);
              
                	String currentMode = (String) userPref.get(allPlaces.get(i));
              
                	String selectedRingerMode = x.getSelectedItem().toString();
              
                	if(currentMode==null)
                	{
              	
                	userPref.put(allPlaces.get(i),x.getSelectedItem().toString());
                	}
                	else{
                	if(! (currentMode.equals(selectedRingerMode)))
                	{
              
                  	userPref.put(allPlaces.get(i),x.getSelectedItem().toString());
                	}
                	}
                	mService.updateCurrentPlace();
                	//setContentView(R.layout.main);
                	}
                	Intent in = new Intent(v.getContext(), SamplePlatysClientActivity.class);
            		startActivity(in);
                	
                	} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
                	}
            });

    	    this.setContentView(sv);
    	    
      	} catch (Exception e) {
      		// TODO Auto-generated catch block
      		e.printStackTrace();
      	}
    	
    	break;
    	
    case R.id.settings1:
    	try {
    		isInSettings1 = true;
    		setContentView(R.layout.settings1);
    	//	TextView allPlacesTextView = (TextView) findViewById(R.id.allPlacesTextView);
    		//allPlacesTextView.setText("Please give your Ringer Mode preference for each location\n");
    		//String allplaces1=""; 
    		

    		final List allActivities = mService.getAllActivities();
    		 
    		List ringer_modes=new ArrayList<String>();
    		ringer_modes.add("Loud and Vibrate");
    		
    		ringer_modes.add("Vibrate");
    		ringer_modes.add("Lights Only");
    		
    		final Hashtable userInputs = new Hashtable();
    	LinearLayout ll = new LinearLayout(this);
    		ll.setOrientation(LinearLayout.VERTICAL);
    		
    		ScrollView sv = new ScrollView(this);
    		
    		TextView tv=new TextView(this);
    		  tv.setText("Please give your Ringer Mode preference for each activity\n");
    	    ll.addView(tv);
    	    sv.removeAllViews();
    	    sv.addView(ll);
    	    
    	    for(int i=1;i<allActivities.size()-1;i++)
      		{
    	    	
      			TextView tv1 = new TextView(this);
      			tv1.setText((CharSequence) allActivities.get(i)+"\n");
      			ll.addView(tv1);
      			
      			Log.i("all places","all places "+ allActivities.get(i));
      			Spinner spinner = new Spinner(this);
      		//Make sure you have valid layout parameters.
      		    spinner.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
      		    ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
      		    android.R.layout.simple_spinner_dropdown_item,ringer_modes);
      		    spinner.setAdapter(spinnerArrayAdapter);
      		    
      		    String mode = (String) userPrefActivity.get(allActivities.get(i));
      		    
      		    if(mode==null)
      		    {
      		    	int spinnerPosition = spinnerArrayAdapter.getPosition("Loud and Vibrate");
          		    spinner.setSelection(spinnerPosition);
      		    }
      		    else{
      		    	int spinnerPosition = spinnerArrayAdapter.getPosition(mode);
      		    	spinner.setSelection(spinnerPosition);
      		    }
      		    
      		    
      		 
      		    	

      		  //set the default according to value
      		      		    
      		    ll.addView(spinner);
      		  //this.setContentView(sv);
      		    userInputs.put(allActivities.get(i), spinner);
      		}
    	    Button saveButton = new Button(this); 
    	    saveButton.setText("Save"); 
            ll.addView(saveButton);
            
            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	try {
                		isInSettings1=false;
                	Log.i("","on button click");
                	for(int i=0;i<userInputs.size();i++)
                	{
                   	Spinner x= (Spinner) userInputs.get(allActivities.get(i+1));
              
                	String currentPlace = (String) allActivities.get(i+1);
              
                	String currentMode = (String) userPrefActivity.get(allActivities.get(i+1));
              
                	String selectedRingerMode = x.getSelectedItem().toString();
              
                	if(currentMode==null)
                	{
              	
                	userPrefActivity.put(allActivities.get(i+1),x.getSelectedItem().toString());
                	}
                	else{
                	if(! (currentMode.equals(selectedRingerMode)))
                	{
              
                  	userPrefActivity.put(allActivities.get(i+1),x.getSelectedItem().toString());
                	}
                	}
                	mService.updateCurrentPlace();
                	//setContentView(R.layout.main);
                	
              
                	}
                	Intent in = new Intent(v.getContext(), SamplePlatysClientActivity.class);
            		
                    startActivity(in);
                	
                	} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
                	}
            });

            this.setContentView(sv);
    	    
      	} catch (Exception e) {
      		// TODO Auto-generated catch block
      		e.printStackTrace();
      	}
  	  
  	  break;
    }
  
    return true;
  }


}
