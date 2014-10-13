package com.example.kontaktliste;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends Activity {
	
	DBAdapter db;
	
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        db = new DBAdapter(this);
        db.open();
        
        Button btnSave = (Button) findViewById(R.id.saveContact);
        
        final EditText txtFirstname = (EditText) findViewById(R.id.firsname);
        final EditText txtLastname = (EditText) findViewById(R.id.lastname);
        final EditText txtPhone = (EditText) findViewById(R.id.phone);
        
        // Legges inn når databasen er opprettet og fungerer
        //final EditText txtBirthday = (EditText) findViewById(R.id.birthday);
	
        
        
        
        btnSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	String firstname = txtFirstname.getText().toString();
            	String lastname = txtLastname.getText().toString();
            	String phone = txtPhone.getText().toString();
            	ContentValues cv = new ContentValues(3);
            	cv.put(DBAdapter.FIRSTNAME, firstname);
            	cv.put(DBAdapter.LASTNAME, lastname);
            	cv.put(DBAdapter.PHONE, phone);
            	db.insert(cv);
            	Log.d("HEI", "Lagt inn i DB");
            	Log.d("Navn", firstname + " Er lagt inn");
            	
            }
        });
	}
	
	public void onResume(){
		super.onResume();
		db.open();
	}
	
	public void onPause(){
		
	}
	
	
	
	public void addToDB(Contact contact){
		
	}
}
