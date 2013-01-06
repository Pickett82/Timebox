package com.example.myfirstapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.text.DateFormat;

import android.os.Bundle;
import android.os.PowerManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {
	
	TextView tv1;
	TextView tv2;
	int mins;
	int secs;
	int mlsc;
	String leadingZeros;
	DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	private PowerManager.WakeLock mWakeLock;
	private PowerManager pm;
	Calendar cal;
	SharedPreferences sharedPref;
	
	int numberOfSeconds;
	
	CountDownTimer timer;
	boolean timerRunning = false;
	
	Timer startTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        
        tv1 = (TextView)findViewById(R.id.textView01);
        tv2 = (TextView)findViewById(R.id.textView02);
        
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        
        
        cal = Calendar.getInstance();
        
         getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
         
         //TODO: schedule starttimer task for time in preferences
         //Date startTime = new Date();
        		 //sharedPref.getString("pref_time", "9:45"); 
         
         //startTimer = new Timer();
         //startTimer.schedule(task, when)
         
        if(!timerRunning){
        
         startTimer();
         
        }
         
    }
    
    public void startTimer() {
    	timerRunning = true;
        
    	numberOfSeconds = Integer.parseInt(sharedPref.getString("pref_duration", "900000"))*1000;

       timer = new CountDownTimer(numberOfSeconds, 1000) {

           public void onTick(long millisUntilFinished) {
           
           	mins = (int)millisUntilFinished/60000;
           	secs = (int)millisUntilFinished/1000 - (mins * 60);
           	mlsc = (int)millisUntilFinished - (secs*1000);
           	cal = Calendar.getInstance();

           	
				tv1.setText(dateFormat.format(cal.getTime()));
				tv2.setText(String.format("%02d",mins) + ":" + String.format("%02d",secs));
           }

           public void onFinish() {
        	   timerRunning = false;
               tv1.setText("done!");
               getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
           }
        }.start();

    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	//Toast toast = Toast.makeText(getApplicationContext(), item.getItemId(), Toast.LENGTH_SHORT);
    	//toast.show();
    	
    	startActivity(new Intent(this, Preferences.class));
    	
    	return true;
    }
    
  
    
}
