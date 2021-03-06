package com.example.kontaktliste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Main extends Activity {

	private static boolean serviceRun = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(!serviceRun){
        	Log.v("dsofjsdfijsdof", "Kjører service");
        	doStartService();
        	serviceRun = true;
        }
       
        Button btnAdd = (Button) findViewById(R.id.add);
        btnAdd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,AddContact.class);
                startActivity(intent);
            }
        });
        
        Button btnList = (Button) findViewById(R.id.btnList);
        btnList.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View v){
	        	Intent i = new Intent(Main.this, ListContacts.class);
	        	startActivity(i);
        	}
        });
    }
    
    private void doStartService(){
    	Intent service = new Intent();
    	service.setAction("com.example.kontaktliste.trigger");
    	sendBroadcast(service);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.add) {
        	Intent i = new Intent(Main.this, AddContact.class);
        	startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
