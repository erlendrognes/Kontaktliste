package com.example.kontaktliste;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Toast.makeText(getApplicationContext(), "I MyService", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}
	

}
