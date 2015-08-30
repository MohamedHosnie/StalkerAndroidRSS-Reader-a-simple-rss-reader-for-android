package com.fcis.stalker.splash.animation;

import com.fcis.stalker.R;
import com.fcis.stalker.task1.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	private static final int TIME = 2 * 1000;// 4 seconds

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(Splash.this,
						MainActivity.class);
				startActivity(intent);

				Splash.this.finish();

				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

			}
		}, TIME);
		
		new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
			         } 
			    }, TIME);
	}

	
	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
}