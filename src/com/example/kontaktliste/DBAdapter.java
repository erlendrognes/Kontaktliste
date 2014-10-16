package com.example.kontaktliste;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;


public class DBAdapter extends ContentProvider{
	Context context;
	static final String TAG="DbHelper";
	private static final String DB_NAME="contacts.db";
	private static final String TABLE="persons";
	public static final String ID=BaseColumns._ID;
	public static final String FIRSTNAME="firstname";
	public static final String LASTNAME="lastname";
	public static final String PHONE="phone";
	public static final String BIRTHDAY="birthday";
	private static final int DB_VERSION=8;
	public static final String PROVIDER = "com.example.kontaktliste"; 
	private static final int CONTACT=1;
	private static final int MCONTACT=2;
	
	private DatabaseHelper DBHelper;
	private static SQLiteDatabase db;
	
	public static final Uri CONTENT_URI = 
			Uri.parse("content://" + PROVIDER + "/contact"); 
	 
	
	private static final UriMatcher uriMatcher;
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER, "contact", MCONTACT);
		uriMatcher.addURI(PROVIDER, "contact/#", CONTACT);
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
			+ PHONE + " text " + BIRTHDAY + " text);";
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
	
	@Override
	public boolean onCreate() {
	 	DBHelper=new DatabaseHelper(getContext());
	 	db=DBHelper.getWritableDatabase();
	 	return true;	
		
	}

	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cur = null; 
		if(uriMatcher.match(uri)==CONTACT)
		{
			cur=db.query(TABLE, projection, ID + " = " + uri.getPathSegments().get(1) ,selectionArgs, null, null, sortOrder);
		 	return cur;
		}
	 	else{
	 		cur=db.query(TABLE,null, null, null, null, null, null);
		 	return cur; 
		}
	}

	@Override
	public String getType(Uri uri) {
		switch(uriMatcher.match(uri)){
		case MCONTACT:return
				"vnd.android.cursor.dir/vnd.example.kontaktliste";
		case CONTACT:return
				"vnd.android.cursor.dir/vnd.example.kontaktliste";
		default:throw new
		IllegalArgumentException("Ugyldig URI" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = DBHelper.getWritableDatabase();
		db.insert(TABLE, null, values);
		
		Cursor c = db.query(TABLE, null, null, null, null, null, null);
		c.moveToLast();
		long minid = c.getLong(0);
		getContext().getContentResolver().notifyChange(uri, null);
		
		return ContentUris.withAppendedId(uri, minid);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if(uriMatcher.match(uri)==CONTACT){
			db.delete(TABLE, ID + " = " + uri.getPathSegments().get(1),selectionArgs);
			getContext().getContentResolver().notifyChange(uri, null);
			
			return 1;
		}
		if(uriMatcher.match(uri)==MCONTACT){
			db.delete(TABLE, null, null);
			getContext().getContentResolver().notifyChange(uri, null);
			
			return 2;
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		if(uriMatcher.match(uri)==CONTACT){
			db.update(TABLE, values, ID + " = " + uri.getPathSegments().get(1), null);
			getContext().getContentResolver().notifyChange(uri, null);
			return 1;
		}
		if(uriMatcher.match(uri)==MCONTACT){
			db.update(TABLE, null, null, null);
			getContext().getContentResolver().notifyChange(uri, null);
			return 2;
		}
		return 0;
	}
	
	public static Contact getContact(long id){
		String[] projection = {DBAdapter.ID,DBAdapter.FIRSTNAME, DBAdapter.LASTNAME, DBAdapter.PHONE };
		
		Cursor cursor = db.query(TABLE,projection,ID + " = " + 
				id,null, null,null, null);
		    cursor.moveToFirst();
		    
		    Contact contact = new Contact(id);
		    contact.setFirstname(cursor.getString(1));
		    contact.setLastname(cursor.getString(2));
		    contact.setPhone(cursor.getInt(3));
		    return contact;
		 
	}
}
