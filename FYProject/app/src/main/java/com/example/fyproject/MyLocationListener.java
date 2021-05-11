package com.example.fyproject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class MyLocationListener extends Service implements LocationListener {
	
	
	protected Context myContext;
protected	LocationManager locationManager;
	boolean networkEnbled = false;
	boolean gpsEnbled = false, canGetLocation = false;
	double longi = 0.0, lati = 0.0;
	String t = "sdsd";
	boolean flag = false;
	double clatitude = 65.9667, clongitude = -18.5333;
	String outAction;
	int counter = 0;
	int count = 0;
	Location location = new Location("A");

	@Override
	public void onCreate() {
		this.myContext = this;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	public Location getLocation(Context context) {
		myContext = context;
		Location location = new Location("A");
		
		locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
		
		networkEnbled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		
		gpsEnbled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			
		
		if (networkEnbled) {

				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 5, 10,(LocationListener) this);
				
				
				if (locationManager != null)
					
					location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				
				if (location != null) {
				
					longi = location.getLongitude();
					lati = location.getLatitude();
				}
			}
			if (gpsEnbled) {

				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 5, 10,(LocationListener) this);
				
				if (locationManager != null)
              location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				
				if (location != null) {
					longi = location.getLongitude();
					lati = location.getLatitude();
				}
			}
		if (location == null) {
			if (networkEnbled) {

	   locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 5, 10,(LocationListener) this);
	   
	   
				if (locationManager != null)
		location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				
				
				if (location != null) {
					longi = location.getLongitude();
					lati = location.getLatitude();
				}
			}
		}
		return location;
	}

	

	 	
	

}
