package com.example.kontaktliste;


import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent i = new Intent(this, Resultat.class);
		PendingIntent pintent = PendingIntent.getActivity(this, 0,i,0);
		Notification noti = new Notification.Builder(this)
		.setContentTitle("Tittel")
		.setContentText("Tekst")
		.setSmallIcon(R.drawable.bdaything)
		.setContentIntent(pintent).build();
		
		//String melding ="Hei %NAME, grattis bla blabl a";
		//meling.replace("%NAME",c.getSTring(2)); 
		
		noti.flags |= Notification.FLAG_AUTO_CANCEL;
		nm.notify(0, noti);
		Toast.makeText(getApplicationContext(), "I MyService", Toast.LENGTH_SHORT).show();
		String[] projection = {DBAdapter.BIRTHDAY, DBAdapter.PHONE, DBAdapter.FIRSTNAME};
		Cursor c = getContentResolver().query(DBAdapter.CONTENT_URI, projection, null, null, null);
		
		while(c.moveToNext()){
			int day = Calendar.getInstance().get(Calendar.DATE);
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			if((Integer.parseInt(c.getString(4).substring(0, 2)) == day) && (Integer.parseInt(c.getString(4).substring(3)) == month)){
				SmsManager m = SmsManager.getDefault(); 
				m.sendTextMessage(c.getString(3),null,"Grattis med dagen " + c.getString(1) + ", Håper den blir bra!",null,null);
				Toast.makeText(getApplicationContext(), "Bursdagsmelding sendt til " + c.getString(1), Toast.LENGTH_SHORT).show();
				Log.v("Bursdag", "BUrsdageddfd");
			}else {
				Log.v("MØ", "MØ");
			}
		}
		
		
		
		/*while(c.moveToNext()){
			if(c.getString(1) == Calendar.getInstance()){
				SmsManager m = SmsManager.getDefault(); 
				m.sendTextMessage(c.getString(2),null,"Grattis med dagen " + c.getString(3) + ", Håper den blir bra!",null,null);
			}
		}
		*/
		
		
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void getBday(){
		
	}
	

}
