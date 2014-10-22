package com.example.kontaktliste;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class NoteReceive extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		
		String msg = i.getStringExtra("sms_body");
		String nr = i.getStringExtra("adress");
		
		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		smsIntent.setData(Uri.parse("sms:"));
		smsIntent.setType("vnd.android-dir/mms-sms");
		smsIntent.putExtra("adress", nr);
		smsIntent.putExtra("sms_body", msg);
		
		
		startActivity(smsIntent);
		finish();
	}

}
