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

public class NoteCheck extends Activity{
	
	private Context context;
	private String name, phonenr;
	private int index;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	public NoteCheck(Context context, String fname, String phone, int index){
		this.context = context;
		name = fname;
		this.phonenr = phone;
		this.index = index;
	}
	
	public void createNote(){
		int reqID = (int) System.currentTimeMillis();
		
		Intent i = new Intent(context, NoteReceive.class);
		
		i.putExtra("sms_body", "Gratulerer med dagen" + name + "!!!!");
		i.putExtra("adress", phonenr);
		
		PendingIntent pintent = PendingIntent.getActivity(context, reqID, i, PendingIntent.FLAG_ONE_SHOT);
		Uri noteSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		
		Notification note = new Notification.Builder(context)
											.setContentTitle(name + " har bursdag i dag!")
											.setContentText("Send melding")
											.setSmallIcon(R.drawable.ic_launcher)
											.setContentIntent(pintent)
											.setSound(noteSound).build();
		
		NotificationManager nm = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
		
		note.flags |= Notification.FLAG_AUTO_CANCEL;
		nm.notify(index, note);
	}
}
