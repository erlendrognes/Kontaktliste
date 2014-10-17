package com.example.kontaktliste;

import android.app.Fragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Contacts extends Fragment implements LoaderCallbacks<Cursor>{
	
	LoaderManager lm;
	CursorLoader cl;
	SimpleCursorAdapter mAdapter;
	String TAG="Loader";
	
	public Contacts(){}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		lm = getActivity().getLoaderManager();
		String[] uiBindFrom = {DBAdapter.FIRSTNAME,DBAdapter.PHONE};
		int[] uiBindTo = {android.R.id.text1,android.R.id.text2};
		mAdapter = new SimpleCursorAdapter(getActivity().getBaseContext(), android.R.layout.simple_list_item_2, null, uiBindFrom, uiBindTo, 0);
		ListView l = (ListView) getActivity().findViewById(R.id.listview);
		l.setAdapter(mAdapter);
		l.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
				
				Contact c = DBAdapter.getContact(arg3);
				AppObject a = (AppObject) getActivity().getApplicationContext();
				a.setContact(c);
				
				//TODO fjern log når fungerer
				Log.v("NAVN", c.getFirstname() + " " + c.getPhone() + " " + c.getLastname());
				
				Intent i = new Intent(getActivity(), ContactDetails.class);
				startActivity(i);
			}
		});
		 
		lm.initLoader(0, null, this);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1){
		String [] projection = {DBAdapter.ID, DBAdapter.FIRSTNAME,DBAdapter.LASTNAME,DBAdapter.PHONE, DBAdapter.BIRTHDAY};
		
		cl = new CursorLoader(getActivity().getBaseContext(), DBAdapter.CONTENT_URI, projection, null, null, null);
		
		return cl;
	}
	
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor){
		if(mAdapter != null && cursor != null)
			mAdapter.swapCursor(cursor);
		else
			Log.v(TAG, "OnLoadFinished: mAdapter is null" + mAdapter);
	}
	
	public void onLoaderReset(Loader<Cursor> arg0){
		if(mAdapter != null)
			mAdapter.swapCursor(null);
		else
			Log.v(TAG, "OnLoadFinished: mAdapter is null");
	}
}
