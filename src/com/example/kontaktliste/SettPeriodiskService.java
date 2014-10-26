package com.example.kontaktliste;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class SettPeriodiskService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Calendar cal = Calendar.getInstance();
		
		Intent i = new Intent(this, MyService.class);
		PendingIntent pintent = PendingIntent.getService(this, 0, i, 0);
		
		AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 40*1000, pintent);
		
		return super.onStartCommand(intent, flags, startId);
	}
	

}
