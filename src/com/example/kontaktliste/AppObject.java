package com.example.kontaktliste;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppObject extends Application{
	
	
	private static AppObject a;
	private String message;
	private Contact contact;
	
	public AppObject getInstance(){
		return a;
	}
	
	public void onCreate(){
		super.onCreate();
		a = this;
		
		SharedPreferences save = PreferenceManager.getDefaultSharedPreferences(this);
		message = save.getString("Message", "Gratulerer med dagen");
	}
	
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String m){
		this.message = m;
	}
	
	public Contact getContact(){
		return contact;
	}
	
	public void setContact(Contact contact){
		this.contact = contact;
	}
	
	
	public void finish(){
		SharedPreferences save = PreferenceManager.getDefaultSharedPreferences(AppObject.this);
		SharedPreferences.Editor editor = save.edit();
		
		editor.putString("Message", message);
		editor.commit();
	}
	
}
