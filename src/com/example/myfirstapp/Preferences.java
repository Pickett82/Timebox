package com.example.myfirstapp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class Preferences extends PreferenceActivity
implements OnSharedPreferenceChangeListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    addPreferencesFromResource(R.xml.preferences);
	}

	  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
	    	Toast toast = Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT);
	    	toast.show();
	    }
}
