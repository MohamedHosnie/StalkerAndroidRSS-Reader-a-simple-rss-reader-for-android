package com.fcis.stalker.task1;

import com.fcis.stalker.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class User_manual extends Activity{
	
	TextView display;
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_manual);
		display = (TextView) findViewById (R.id.manualtextview);
	}
}