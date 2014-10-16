package com.example.kontaktliste;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class ContactDetails extends Activity{

	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_contact_details);
	 
	        //Button btnSave = (Button) findViewById(R.id.btnSave);
	        Button btnChange = (Button) findViewById(R.id.btnChange);
	        Button btnDelete = (Button) findViewById(R.id.btnDelete);
	 
	        
			TextView usrFirstname = (TextView) findViewById(R.id.usrFirstname);
	        TextView usrLastname = (TextView) findViewById(R.id.usrLastname);
	        TextView usrPhone = (TextView) findViewById(R.id.usrPhone);
	        TextView usrBirthday = (TextView) findViewById(R.id.usrBirthday);
	        
	        
	        
	 		usrFirstname.setText(DBAdapter.FIRSTNAME);
	 		usrLastname.setText(DBAdapter.LASTNAME);
	 		usrPhone.setText(DBAdapter.PHONE);
	 		usrBirthday.setText(DBAdapter.BIRTHDAY);
	        
	 }
	 
}
