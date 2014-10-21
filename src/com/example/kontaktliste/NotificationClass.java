package com.example.kontaktliste;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

public class NotificationClass extends Activity {
	private Context context;
	private String name;
	private String phonenumber;
	private int index;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public NotificationClass(Context context, String firstname, String phonenumber, int index){
		this.context = context;
		name = firstname;
		this.phonenumber = phonenumber;
		this.index = index;
	}

	public void createNotification(){
		int requestID = (int) System.currentTimeMillis();
		
		Intent intent = new Intent(context, NotReciever.class);
		
		intent.putExtra("sms_body","Gratulerer med dagen " + name + "! Hurra hurra");
		intent.putExtra("address", phonenumber);
		
		PendingIntent pIntent =	PendingIntent.getActivity(context, requestID, intent, PendingIntent.FLAG_ONE_SHOT);
		Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		
		Notification notification = new Notification.Builder(context)
										.setContentTitle("It's " + name + "'s birthday!")
										.setContentText("Click here to send them a message")
										.setSmallIcon(R.drawable.ic_launcher)
										.setContentIntent(pIntent)
										.setSound(notificationSound).build();
		
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
		
	    notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(index, notification);		
		
	}
}