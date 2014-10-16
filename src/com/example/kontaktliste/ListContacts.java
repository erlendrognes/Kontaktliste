package com.example.kontaktliste;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

public class ListContacts extends FragmentActivity{
	
	private DBAdapter db;
	private ArrayAdapter<Contact> la;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
		if(findViewById(R.id.fragment_container) != null){
			if(savedInstanceState != null){
				return; 
			}
			Contacts contactFragment = new Contacts();
			contactFragment.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(R.id.fragment_container, contactFragment).commit();
		}
	}
}

