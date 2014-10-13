package com.example.kontaktliste;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class ListContact extends FragmentActivity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		if(findViewById(R.id.fragment_gontainer) != null){
			if(savedInstanceState != null){
				return;
			}
			ContactClass contactFragment = new ContactClass();
			contactFragment.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(R.id.fragment_gontainer, contactFragment).commit();
		}
	}
}