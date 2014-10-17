package com.example.kontaktliste;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class ContactDetails extends Activity{

	TextView fname;
	TextView lname;
	TextView phone;
	TextView bday;
	ApplicationObject app;
	Contact c;
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_contact_details);
	        app = (ApplicationObject)getApplicationContext();
	        c = app.getContact();
	 
	        //Button btnSave = (Button) findViewById(R.id.btnSave);
	        Button btnChange = (Button) findViewById(R.id.btnChange);
	        Button btnDelete = (Button) findViewById(R.id.btnDelete);
	 
	        
			fname = (TextView) findViewById(R.id.usrFirstname);
	        lname = (TextView) findViewById(R.id.usrLastname);
	        phone = (TextView) findViewById(R.id.usrPhone);
	        bday = (TextView) findViewById(R.id.usrBirthday);
	        
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
