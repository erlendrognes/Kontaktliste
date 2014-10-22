package com.example.kontaktliste;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class BdayCheck extends Service {
	private DBAdapter dbAdapter = new DBAdapter();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v("INN her?", "PIKK");
		SimpleDateFormat df = new SimpleDateFormat("dd MM YYYY");
		Log.v("INN her? 2", "PIKK");
		String today = df.format(Calendar.getInstance().getTime()).toString();

		ArrayList<Contact> celebrants = dbAdapter
				.getContactsByBirthday(today);
		
		if (celebrants.size() > 0) {
			for (Contact c : celebrants) {
				NotificationClass nc = new NotificationClass(this,c.getFirstname(),
						c.getPhone(), celebrants.indexOf(c));
				nc.createNotification();
			}
		}
		
	
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}