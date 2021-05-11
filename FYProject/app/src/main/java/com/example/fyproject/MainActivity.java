package com.example.fyproject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button register,alert;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        register=(Button) findViewById(R.id.register);
        alert=(Button) findViewById(R.id.alert);
        
    
       
    }
    
    public void onClick(View v)
    {
    switch(v.getId())
    {
    case R.id.register:	 
    Intent i=new Intent(MainActivity.this,InsertForm.class);
    startActivity(i);
    	break;
    case R.id.alert:
    
    	Intent j=new Intent(MainActivity.this,AlertMessage.class);
    	startActivity(j);
    	break;

    	}
    	
    }
    
   
    
}
