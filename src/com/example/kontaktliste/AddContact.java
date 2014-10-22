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
				Intent i = new Intent(AddContact.this, Main.class);
				startActivity(i);
				finish();
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
		switch(request){
		case 0:
			if(result == RESULT_OK){
				Bundle bundle = data.getExtras();
				txtBday.setText(bundle.getString("selectedDate"));
				break;
			}
		}
	}
	
	
	
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
    	Toast.makeText(getActionBar().getThemedContext(), firstname + " er lagret!", Toast.LENGTH_SHORT).show();
	}

}
