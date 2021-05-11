package com.example.fyproject;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

public class AlertMessage extends Activity {
	
	MyLocationListener loc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.message);
	    
	    loc=new MyLocationListener();
	    
	    Location locat=loc.getLocation(AlertMessage.this);
		
		 String uri = "i am in emergency please help me\n"+"http://maps.google.com/maps?q=" + locat.getLatitude()+","+locat.getLongitude();
			SmsManager smsManager = SmsManager.getDefault();
			StringBuffer smsBody = new StringBuffer();
			 smsBody.append(Uri.parse(uri));
			 smsManager.sendTextMessage("03068002112", null, smsBody.toString(), null, null);
			
			Toast.makeText(AlertMessage.this, "Location send", Toast.LENGTH_SHORT).show();
			
			
			Intent i =new Intent(AlertMessage.this,MainActivity.class);
			startActivity(i);
			
		
			
	 
	    
	    }
	

	}


	


