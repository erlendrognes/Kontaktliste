package com.example.kontaktliste;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class ContactDetails extends Activity{

	TextView fname;
	TextView lname;
	TextView phone;
	TextView bday;
	AppObject app;
	Contact c;
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_contact_details);
	        app = (AppObject)getApplicationContext();
	        c = app.getContact();
	 
	        
			fname = (EditText) findViewById(R.id.editFirstName);
			fname.setText(c.getFirstname());
	        lname = (EditText) findViewById(R.id.editLastName);
	        lname.setText(c.getLastname());
	        phone = (EditText) findViewById(R.id.editPhone);
	        phone.setText(c.getPhone());
	        bday = (EditText) findViewById(R.id.editBirthday);
	        bday.setText(c.getBirthday());
	        
	 }
	 
	 public void changeCon(View v){
		 String firstname = output(fname.getText().toString(), c.getFirstname());
		 String lastname = output(lname.getText().toString(), c.getLastname());
		 String cellphone = output(phone.getText().toString(), c.getPhone());
		 String birthday = output(bday.getText().toString(), c.getBirthday());
		 
		 c.setFirstname(firstname);
		 c.setLastname(lastname);
		 c.setPhone(cellphone);
		 c.setBirthday(birthday);
		 
		 
		 ContentValues cv = new ContentValues(3);
		 cv.put(DBAdapter.FIRSTNAME, firstname);
		 cv.put(DBAdapter.LASTNAME, lastname);
		 cv.put(DBAdapter.PHONE, cellphone);
		 cv.put(DBAdapter.BIRTHDAY, birthday);
		 
		 Boolean passed = DBAdapter.update(c.getId(), cv);
		 if(passed){
			 Toast.makeText(getBaseContext(), firstname + " er endret", Toast.LENGTH_SHORT).show();
			 Intent i = new Intent(ContactDetails.this, Main.class);
			 finish();
			 startActivity(i);
		 }
		 else
			 Toast.makeText(getBaseContext(), firstname + " ble ikke endret", Toast.LENGTH_SHORT).show();
	 }
	 
	 private String output(String b, String a){
		 String txt = "";
		 if(b.equals("")){
			 txt = b;
		 }
		 else{
			 txt = b;
		 }
		 return txt;
	 }
	 
	 public void delCon(View v){
		 Boolean ok = DBAdapter.delete(c.getId());
		 
		 if(ok){
			 Toast.makeText(getBaseContext(), c.getFirstname() + " er slettet!", Toast.LENGTH_SHORT).show();
			 app.setContact(null);
			 Intent i = new Intent(ContactDetails.this, Main.class);
			 finish();
			 startActivity(i);
		 }
		 else
			 Toast.makeText(getBaseContext(), c.getFirstname() + " ble ikke slettet!", Toast.LENGTH_SHORT).show();
	 }
}
