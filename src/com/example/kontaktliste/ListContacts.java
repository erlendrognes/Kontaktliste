package com.example.kontaktliste;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ListContacts extends FragmentActivity{
	
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

