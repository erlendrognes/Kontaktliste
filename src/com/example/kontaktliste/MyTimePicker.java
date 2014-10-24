/*package com.example.kontaktliste;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class MyTimePicker extends DialogFragment implements OnTimeSetListener{
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		return new TimePickerDialog(getActivity(), this, hour,minute, DateFormat.is24HourFormat(getActivity()));
	}
	
	public void onTimeSet(TimePicker view, int hourOfDay, int minute){
		SharedPreferences sf = PreferenceManager.getDefaultSharedPreferences(getActivity());
		SharedPreferences.Editor e = sf.edit();
		e.putInt("hour", hourOfDay);
		e.putInt("minute", minute);
		e.commit();
		getActivity().finish();
		
		startActivity(getActivity().getIntent());
	}

}
*/