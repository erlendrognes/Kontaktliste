package com.example.kontaktliste;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ApplicationObject extends Application{
	
	
	private static ApplicationObject ao;
	private String message;
	private Contact contact;
	
	public ApplicationObject getInstance(){
		return ao;
	}
	
	public void onCreate(){
		super.onCreate();
		ao = this;
		
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
		SharedPreferences save = PreferenceManager.getDefaultSharedPreferences(ApplicationObject.this);
		SharedPreferences.Editor editor = save.edit();
		
		editor.putString("Message", message);
		editor.commit();
	}
	
}
