package com.example.fyproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertForm extends Activity implements OnClickListener {
	
	EditText name,cellno,email,cnic;

	Button register;
	
     private ProgressDialog pDialog;
	
	JSONParser jsonParser= new JSONParser();

	private static final String url_vehicle_detials = "http://192.168.97.1:80/FYProject/vehicleinfo.php";
	private static final String TAG_SUCCESS = "success";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contect_form);
		
		init();  /////////user define Method....
		
		register.setOnClickListener(this);
	}
	
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	
		
		
		final String namen=name.getText().toString();
		final String cellnon=cellno.getText().toString();
		final String emailn=email.getText().toString();
		final String cnicn=cnic.getText().toString();
		
		///////name validation......
		
		if(!nameValid(namen))
		{
		Toast.makeText(this,"invalid name", Toast.LENGTH_LONG).show();	
		return;
		
			
		}
		/////////// if name is empty
		
		else if(namen.equals(""))
		{
Toast.makeText(this, "please Enter name", Toast.LENGTH_LONG).show();
return;
			
		}
		/////// cellno validation
		
		else if(!cellnoValid(cellnon))
		{
		Toast.makeText(this, "invalid Cellno",Toast.LENGTH_LONG).show();
		return;
			
			}
		//// if cellno is empty
		else if(cellnon.equals(""))
		{
	Toast.makeText(this, "Please Enter cellno..", Toast.LENGTH_LONG).show();
	return;
		}
		//// cellno digit lenght
		
		else if(cellnon.length()!=11)
		{
Toast.makeText(this,"please enter 11 digit number..", Toast.LENGTH_LONG).show();	
return;
			
		}
		///// email validation
		else if(!emailValid(emailn))
		{
	Toast.makeText(this,"invalid email", Toast.LENGTH_LONG).show();		
	return;
			
		}
		//// if email is empty
		else if(emailn.equals(""))
		{
Toast.makeText(this, "please enter Email....",Toast.LENGTH_LONG).show();
return;
			
		}
		/// cnic validation....
		else if(!cnicValid(cnicn))
		{
			
Toast.makeText(this, "invalid cnic...", Toast.LENGTH_LONG).show();
return;
		}
		
		/// if cnic is empty
		else if(cnicn.equals(""))
		{
Toast.makeText(this,"please Enter cnic...", Toast.LENGTH_LONG).show();	
return;
			
		}
		//// cnic length
		else if(cnic.length()!=13)
		{
			
Toast.makeText(this, "please enter 13 digit cnic no...",Toast.LENGTH_LONG).show();	
return;
		}
		else
		{
		new NetCheck().execute();
			
			
		}
	}
	
	///////user define Methods ....
	
	private boolean nameValid(String name)
	{
		String namePattren="^[a-zA-Z]*";
		Pattern pattren=Pattern.compile(namePattren);
		Matcher match=pattren.matcher(name);
		return match.matches();
		
		
	}
	private boolean cellnoValid(String cell)
	{
		String cellPattren="^[0][3][0+1+2+3+4+][0-9]*";
		Pattern cpattren=Pattern.compile(cellPattren);
		Matcher cmatch=cpattren.matcher(cell);
		return cmatch.matches();
		
	}
	private boolean emailValid(String email)
	{
		String emailPattren="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern epattren=Pattern.compile(emailPattren);
		Matcher ematch=epattren.matcher(email);
		return ematch.matches();
	}
	private boolean cnicValid(String cnic)
	{
		String cnicPattren="^[0-9]*";
		Pattern cpattren=Pattern.compile(cnicPattren);
		Matcher cmatch=cpattren.matcher(cnic);
		return cmatch.matches();
		
	}



////////////initialize EditText and button//////////
public void init()
{
name=(EditText) findViewById(R.id.name);
cellno=(EditText) findViewById(R.id.cellno);
email=(EditText) findViewById(R.id.email);
cnic=(EditText) findViewById(R.id.cnic);
register=(Button) findViewById(R.id.register);


}



class NetCheck extends AsyncTask<String,String,Boolean>
{
    private ProgressDialog nDialog;

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		nDialog = new ProgressDialog(InsertForm.this);
        nDialog.setMessage("Loading...");
        nDialog.setTitle("Checking Network");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();
	}
	@Override
	protected Boolean doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL("http://192.168.97.1:80/FYProject/vehicleinfo.php");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(3000);
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return true;
                }
            } catch (MalformedURLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		return false;
	}

	@Override
	protected void onPostExecute(Boolean th) {
		// TODO Auto-generated method stub
		super.onPostExecute(th);
		if(th == true){
            nDialog.dismiss();
            new Insertion().execute();
        }
        else{
            nDialog.dismiss();
            //tv.setText("Error in Network Connection");
            Toast.makeText(getApplicationContext(), "Error in Network Connection", Toast.LENGTH_SHORT).show();
            
        }
    }
	}

class Insertion extends AsyncTask<String, String, String>{
	
	
	

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		    pDialog = new ProgressDialog(InsertForm.this);
            pDialog.setMessage("inserting new record...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		
		
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("namep", name.getText().toString()));
        params.add(new BasicNameValuePair("cellnop",cellno.getText().toString()));
        params.add(new BasicNameValuePair("emailp",email.getText().toString()));
        params.add(new BasicNameValuePair("cnicp",cnic.getText().toString()));


        
        // Note that create product url accepts POST or GET method
        JSONObject json = jsonParser.makeHttpRequest(url_vehicle_detials,"GET", params);
        
        // check log cat for response
        Log.d("Create Response", json.toString());
        
        

        
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
            	
            	
              
            	return json.getString(TAG_SUCCESS);
            	
            	
            	
            
            } else {
              
            	return json.getString(TAG_SUCCESS);
            	
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

		return null;
	}

	@Override
	protected void onPostExecute(String message) {
		// TODO Auto-generated method stub
		super.onPostExecute(message);
		
		 pDialog.dismiss();
            
		 if (message != null){
                Toast.makeText(InsertForm.this,"insert successfully", Toast.LENGTH_LONG).show();
                
                Intent i = new Intent(InsertForm.this,MainActivity.class);
                
                startActivity(i);

	}
	
	}
	
}



}



