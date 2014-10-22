package com.example.kontaktliste;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


public class MyService extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		int hour = pref.getInt("hour", 12);
		int minute = pref.getInt("minute", 0);
		Log.v("SERVICE", "Hentet fra Service?");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		
		
		Intent service = new Intent(context, BdayCheck.class);
		PendingIntent reminder = PendingIntent.getService(context, 0, service, PendingIntent.FLAG_ONE_SHOT);
		AlarmManager a = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		a.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, reminder);
	}

	
}
