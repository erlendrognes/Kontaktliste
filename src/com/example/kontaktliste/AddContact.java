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
import android.widget.Toast;

public class AddContact extends Activity {
	
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        
        Button btnSave = (Button) findViewById(R.id.saveContact);
        
        final EditText txtFirstname = (EditText) findViewById(R.id.firsname);
        final EditText txtLastname = (EditText) findViewById(R.id.lastname);
        final EditText txtPhone = (EditText) findViewById(R.id.phone);
        //final EditText txtBirthday = (EditText) findViewById(R.id.birthday);
	
        
        
        
        btnSave.setOnClickListener(new OnClickListener() {

            @Override  
            public void onClick(View v) {
            	String firstname = txtFirstname.getText().toString();
            	String lastname = txtLastname.getText().toString();
            	String phone = txtPhone.getText().toString();
            	//String birthday = txtBirthday.getText().toString();
            	ContentValues cv = new ContentValues(3);
            	cv.put(DBAdapter.FIRSTNAME, firstname);
            	cv.put(DBAdapter.LASTNAME, lastname);
            	cv.put(DBAdapter.PHONE, phone);
            	//cv.put(DBAdapter.BIRTHDAY, birthday);
            	getContentResolver().insert(DBAdapter.CONTENT_URI, cv);
            	Log.d("Navn", firstname + ", " + lastname + " Er lagt inn");
            	Toast.makeText(getActionBar().getThemedContext(), firstname + " er lagt til", Toast.LENGTH_SHORT).show();
            	Intent i = new Intent(AddContact.this, Main.class);
            	finish();
            	startActivity(i);
            }
        });
	}
}
