package com.example.kontaktliste;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DatePicker extends Activity{
	
	static final int DATE_DIALOG_ID = 0;
	private int pickedMonth, pickedDay, pickedYear, currentYear, currentMonth, currentDay;
	
	private String[] months = {"01", "02", "03", "04", "05", "06",
								"07", "08", "09", "10", "11", "12"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		showDatePickerDialog();
	}
	
	@SuppressWarnings("deprecation")
	public void showDatePickerDialog(){
		Calendar today = Calendar.getInstance();
		currentYear = today.get(Calendar.YEAR);
		currentMonth = today.get(Calendar.MONTH);
		currentDay = today.get(Calendar.DAY_OF_MONTH);
		showDialog(DATE_DIALOG_ID);
	}
	
	protected Dialog onCreateDialog(int id){
		switch(id){
		case DATE_DIALOG_ID: return new DatePickerDialog(this, dateSetListener, currentYear, currentMonth, currentDay);
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(android.widget.DatePicker view, int year,
				int month, int dayOfMonth) {
				pickedMonth = month;
				pickedDay = dayOfMonth;
				pickedYear = year;
				String day = "";
				String selectedDate = "";
				if(String.valueOf(pickedDay).length() == 1){
					day = "0" + pickedDay;
					selectedDate = day + "/" + months[pickedMonth] + "-" + pickedYear;
				}
				else{
					selectedDate = pickedDay + "/" + months[pickedMonth] + "-" + pickedYear;
				}
				
				Bundle b = new Bundle();
				b.putString("selectedDate", selectedDate);
				
				
				Intent intent = new Intent();
				intent.putExtras(b);
				setResult(RESULT_OK,intent);
				Log.v("Dato i DatePicker",selectedDate);
				finish();
		}
	};
	

}
