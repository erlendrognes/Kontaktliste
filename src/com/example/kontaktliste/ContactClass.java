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

public class ContactClass extends Fragment implements LoaderCallbacks<Cursor>{
	
	LoaderManager loadermanager;
	CursorLoader cursorloader;
	SimpleCursorAdapter mAdapter;
	String TAG="Loder";
	
	
	//TODO: Gjøre så databasen listes istedenfor kontaktene i telefonkatalogen
	
	public ContactClass(){}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.list, container, false);
	}
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		loadermanager = getActivity().getLoaderManager();
		String[] uiBindFrom = {ContactsContract.Contacts.DISPLAY_NAME};
		int[] uiBindTo = {android.R.id.text1};
		
		mAdapter = new SimpleCursorAdapter(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, null, uiBindFrom, uiBindTo, 0);
		ListView l = (ListView) getActivity().findViewById(R.id.listview);
		l.setAdapter(mAdapter);
		
		l.setOnItemClickListener(new OnItemClickListener(){
			
			
			//TODO: Åpne info om kontakten ved klikk her
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
				Toast.makeText(getActivity().getBaseContext(), arg2 + " KLIKKA!! ", Toast.LENGTH_SHORT).show();
			}
		});
		
		loadermanager.initLoader(0, null, this);
	}
	
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1){
		String[] projection = {
				ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME
		};
		
		cursorloader = new CursorLoader(getActivity().getBaseContext(), ContactsContract.Contacts.CONTENT_URI, projection,null,null,null);
		
		return cursorloader;
	}
	
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor){
		if(mAdapter != null && cursor != null)
			mAdapter.swapCursor(cursor);
		else
			Log.v(TAG, "OnLoadFinished:mAdapter is null");
	}
	
	public void onLoaderReset(Loader<Cursor> arg0){
		if(mAdapter != null)
			mAdapter.swapCursor(null);
		else
			Log.v(TAG, "OnLoadFinished:mAdapter is null");
	}
	
	
}
