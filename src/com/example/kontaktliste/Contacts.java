package com.example.kontaktliste;

import android.app.Fragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Contacts extends Fragment implements LoaderCallbacks<Cursor>{
	DBAdapter db;	
	LoaderManager lm;
	CursorLoader cl;
	SimpleCursorAdapter mAdapter;
	String TAG="Loader";
	
	public Contacts(){}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.list, container, false);
	}
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		lm = getActivity().getLoaderManager();
		String[] uiBindFrom = 
			{DBAdapter.FIRSTNAME, DBAdapter.LASTNAME};
		int[] uiBindTo = {android.R.id.text1,android.R.id.text2};
		
		mAdapter = new SimpleCursorAdapter(getActivity().getBaseContext(),
				android.R.layout.simple_list_item_2, null, uiBindFrom, uiBindTo, 0);
		
		ListView l = (ListView) getActivity().findViewById(R.id.listview);
		l.setAdapter(mAdapter);
		l.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
				
				//Toast er en liten tekst som dukker opp nederst på skjermen og forklarer
				// kjekt å ha til "lagret"
				Toast.makeText(getActivity().getBaseContext(), DBAdapter.FIRSTNAME + " Klikket", Toast.LENGTH_SHORT).show();
			}
		});
		 
		lm.initLoader(0, null, this);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1){
		String [] projection = {DBAdapter.ID, DBAdapter.FIRSTNAME,DBAdapter.LASTNAME};
		
		cl = new CursorLoader(getActivity().getBaseContext(), DBAdapter.CONTENT_URI, projection, null, null, null);
		
		return cl;
	}
	
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor){
		if(mAdapter != null && cursor != null)
			mAdapter.swapCursor(cursor);
		else
			Log.v(TAG, "OnLoadFinished: mAdapter is null");
	}
	
	public void onLoaderReset(Loader<Cursor> arg0){
		if(mAdapter != null)
			mAdapter.swapCursor(null);
		else
			Log.v(TAG, "OnLoadFinished: mAdapter is null");
	}
}