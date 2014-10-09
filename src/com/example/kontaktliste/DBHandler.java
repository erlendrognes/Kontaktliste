package com.example.kontaktliste;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

	static String TABLE_CONTACTS = "Contacts";
	static String KEY_ID = "_ID";
	static String KEY_FIRSTNAME = "Firstname";
	static String KEY_LASTNAME = "Lastname";
	static String KEY_PHONE = "Phone";
	static String KEY_BIRTHDAY = "Birthday";
	static int DATABASE_VERSION = 3;
	static String DATABASE_NAME = "ContactList";
	
	
	public DBHandler(Context context, String name, CursorFactory factory, int version){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public DBHandler(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db){
		Log.d("Inne", "Inne i onCreate");
		String CREATE_TABLE = " CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " " 
							+ " INTEGER PRIMARY KEY, " + 
							" KEY_FIRSTNAME " + " TEXT, " + KEY_LASTNAME + " TEXT, " + 
							" KEY_PHONE " + " INTEGER " + ")";
		Log.d("SQL", CREATE_TABLE);
		db.execSQL(CREATE_TABLE);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		
		onCreate(db);
	}
	
	public void addContacts(Contact c){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues v = new ContentValues();
		v.put(KEY_FIRSTNAME, c.getFirstname());
		v.put(KEY_LASTNAME, c.getLastname());
		v.put(KEY_PHONE, c.getPhone());
		v.put(KEY_BIRTHDAY, c.getBirthday());
		db.close();
		Log.d("KUKK", "Lagt inn");
		}
}
