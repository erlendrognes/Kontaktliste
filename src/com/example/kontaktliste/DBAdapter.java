package com.example.kontaktliste;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBAdapter {
	Context context;
	static final String TAG="DbHelper";
	static final String DB_NAME="contacts.db";
	static final String TABLE="persons";
	static final String ID=BaseColumns._ID;
	static final String FIRSTNAME="firstname";
	static final String LASTNAME="lastname";
	static final String PHONE="phone";
	static final int DB_VERSION=2;
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	
	public DBAdapter(Context ctx){
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper{
		
		DatabaseHelper(Context context){
			super(context, DB_NAME, null, DB_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql="create table " + TABLE + " ("
			+ ID + " integer primary key autoincrement, " 
			+ FIRSTNAME + " text, " + LASTNAME + " text, "
			+ PHONE + " text);";
			Log.d(TAG, "oncreate sql:" + sql);
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + TABLE);
			Log.d(TAG, "Oppdatert");
			onCreate(db);
		}
	}
}
