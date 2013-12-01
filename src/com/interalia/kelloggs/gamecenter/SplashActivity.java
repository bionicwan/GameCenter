package com.interalia.kelloggs.gamecenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.interalia.kelloggs.gamecenter.utilities.ActivitySplitAnimationUtil;

public class SplashActivity extends Activity {
	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				ActivitySplitAnimationUtil.startActivity(SplashActivity.this, new Intent(SplashActivity.this, MainActivity.class));

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
}
