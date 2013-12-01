package com.interalia.kelloggs.gamecenter;

import android.app.Activity;
import android.os.Bundle;

import com.interalia.kelloggs.gamecenter.web.ResponseInterface;

public class RegistrationActivity extends Activity implements ResponseInterface {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_activity);
	}

	@Override
	public void onResultResponse(Object obj) {
		// TODO Auto-generated method stub

	}

}
