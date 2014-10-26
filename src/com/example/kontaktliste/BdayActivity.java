package com.example.kontaktliste;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class BdayActivity extends DialogFragment {

	private DialogClickListener callback;
	
	public interface DialogClickListener{
		public void onSendClick();
		public void onCancelClick();
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		try{
			callback = (DialogClickListener) getActivity();
		}
		catch(ClassCastException e){
			throw new ClassCastException("Finner ikke dialoginterface");
		}
	}
	
	public static BdayActivity newInstance(int title, int message, String s){
		BdayActivity frag = new BdayActivity();
		Bundle args = new Bundle();
		args.putInt("Tittel", title);
		args.putInt("message", message);
		args.putString("word", s);
		frag.setArguments(args);
		return frag;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState){
		int title = getArguments().getInt("Tittel");
		int message = getArguments().getInt("message");
		String bdayPerson = getArguments().getString("word");
		String outMessage = getString(message) + " " + bdayPerson;
		return new AlertDialog.Builder(getActivity())
		.setTitle(title)
		.setMessage(outMessage)
		.setPositiveButton(R.string.send, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.onSendClick();
				}
			}
		)
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				callback.onCancelClick();
				}
			}
		)
		.create();
	}
	
}
