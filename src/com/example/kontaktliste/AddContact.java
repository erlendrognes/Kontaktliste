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
import android.widget.TextView;
import android.widget.Toast;

public class AddContact extends Activity {
	
	
	private TextView txtBday;
	private Button save, calendar;
	private EditText txtFname, txtLname, txtPhone;
	
	
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        
        bindInputFields();
        bindButtons();
        
        /*Button btnSave = (Button) findViewById(R.id.saveContact);
        final EditText txtFirstname = (EditText) findViewById(R.id.firsname);
        final EditText txtLastname = (EditText) findViewById(R.id.lastname);
        final EditText txtPhone = (EditText) findViewById(R.id.phone);
        final EditText txtBirthday = (EditText) findViewById(R.id.birthday);
        
        btnSave.setOnClickListener(new OnClickListener() {
            @Override  
            public void onClick(View v) {
            	String firstname = txtFirstname.getText().toString();
            	String lastname = txtLastname.getText().toString();
            	String phone = txtPhone.getText().toString();
            	String birthday = txtBirthday.getText().toString();
            	ContentValues cv = new ContentValues(3);
            	cv.put(DBAdapter.FIRSTNAME, firstname);
            	cv.put(DBAdapter.LASTNAME, lastname);
            	cv.put(DBAdapter.PHONE, phone);
            	cv.put(DBAdapter.BIRTHDAY, birthday);
            	getContentResolver().insert(DBAdapter.CONTENT_URI, cv);
            	Log.d("Navn", firstname + ", " + lastname + " Er lagt inn");
            	Toast.makeText(getActionBar().getThemedContext(), firstname + " er lagt til", Toast.LENGTH_SHORT).show();
            	Intent i = new Intent(AddContact.this, Main.class);
            	finish();
            	startActivity(i);
            }
        });*/
	}
	
	private void bindButtons(){
		calendar = (Button) findViewById(R.id.btnBday);
		calendar.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AddContact.this, DatePicker.class);
				startActivityForResult(intent, 0);
			}
		});
		
		save = (Button) findViewById(R.id.saveContact);
		save.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				saveContact();	
			}
		});
	}
	
	
	private void bindInputFields(){
		txtFname = (EditText) findViewById(R.id.firstname); 
		txtLname = (EditText) findViewById(R.id.lastname);
		txtPhone = (EditText) findViewById(R.id.phone);
		txtBday = (TextView) findViewById(R.id.birthday);
	}
	
	private void clearInput(){
		txtFname.setText("");
		txtLname.setText("");
		txtPhone.setText("");
		txtBday.setText("");
	}
	
	
	public void onActivityResult(int request, int result, Intent data){
		Log.v("Hei", "Kom inn i onDateResult");
		switch(request){
		case 0:
			Log.v("Krasj1", "HER?");
			if(result == RESULT_OK){
				Log.v("Krasj2", "HER?");
				Bundle bundle = data.getExtras();
				Log.v("Krasj4", "HER?");
				txtBday.setText(bundle.getString("selectedDate"));
				Log.v("Krasj5", "HER?");
				Log.v("Dato2", txtBday.toString());
				break;
			}
		}
	}
	
	/*private boolean noEmpty(){
		return txtFname.getText().toString().length() != 0 &&
				txtLname.getText().toString().length() != 0 &&
				txtPhone.getText().toString().length() != 0 &&
				txtBday.getText().toString().length() != 0;
	}*/
	
	private void saveContact(){
		String firstname = txtFname.getText().toString();
    	String lastname = txtLname.getText().toString();
    	String phone = txtPhone.getText().toString();
    	String birthday = txtBday.getText().toString();
		ContentValues cv = new ContentValues(4);
		
			cv.put(DBAdapter.FIRSTNAME, firstname);
	    	cv.put(DBAdapter.LASTNAME, lastname);
	    	cv.put(DBAdapter.PHONE, phone);
	    	cv.put(DBAdapter.BIRTHDAY, birthday);
		
		
		getContentResolver().insert(DBAdapter.CONTENT_URI, cv);
    	clearInput();
    	Toast.makeText(getActionBar().getThemedContext(), "Added", Toast.LENGTH_SHORT).show();
	}
	
	



























}
