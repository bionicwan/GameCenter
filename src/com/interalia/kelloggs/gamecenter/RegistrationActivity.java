package com.interalia.kelloggs.gamecenter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.interalia.kelloggs.gamecenter.infrastructure.ResponseInterface;
import com.interalia.kelloggs.gamecenter.pojo.ResultPojo;
import com.interalia.kelloggs.gamecenter.web.task.RegistrationTask;

public class RegistrationActivity extends Activity implements ResponseInterface, OnClickListener {

	private Spinner items;
	private Button btnSignUp;
	private EditText txtUsername;
	private EditText txtPassword;
	private EditText txtPasswordConfirmation;
	private EditText txtEmail;
	private EditText txtBirthday;
	private EditText txtNickname;
	private ResultPojo resultPojo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_activity);
		
		btnSignUp = (Button)findViewById(R.id.btnSignUp);
		txtUsername = (EditText)findViewById(R.id.txtUsername);
		txtPassword = (EditText)findViewById(R.id.txtPassword);
		txtPasswordConfirmation = (EditText)findViewById(R.id.txtConfirmPassword);
		txtEmail = (EditText)findViewById(R.id.txtEmail);
		txtBirthday = (EditText)findViewById(R.id.txtBirthday);
		txtNickname = (EditText)findViewById(R.id.txtNickname);
		
		this.fillCountriesSpinner();
		
		btnSignUp.setOnClickListener(this);
	}

	private void fillCountriesSpinner() {
		List<String> SpinnerArray = new ArrayList<String>();
		SpinnerArray.add("Mexico");
		SpinnerArray.add("United States");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, SpinnerArray);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		items = (Spinner) findViewById(R.id.spnCountry);
		items.setAdapter(adapter);
	}

	@Override
	public void onResultResponse(Object obj) {
		// TODO Auto-generated method stub
		resultPojo = (ResultPojo)obj;
		Context context = getApplicationContext();
		CharSequence text;
		int duration = Toast.LENGTH_SHORT;
		
		if(resultPojo.isSuccess()){
			text = "Succesfully registered!";
			Toast.makeText(context, text, duration).show();
			
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
		}
		else{
			text = "Something went wrong, please try again";
			Toast.makeText(context, text, duration).show();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String username = txtUsername.getText().toString();
		String password = txtPassword.getText().toString();
		String passwordConfirmation = txtPasswordConfirmation.getText().toString();
		
		if(!password.equals(passwordConfirmation)){
			Context context = getApplicationContext();
			CharSequence text;
			int duration = Toast.LENGTH_SHORT;
			text = "Something went wrong, please try again";
			Toast.makeText(context, text, duration).show();
			return;
		}
		
	 	String email = txtEmail.getText().toString();
		String nickname = txtNickname.getText().toString();
		String birthday = txtBirthday.getText().toString();
		String gender = "f";

		// Cambiar para que consulte catálogo.
		String countryId = "1";

	    RegistrationTask registrationTask = new RegistrationTask(this);
	    registrationTask.execute(countryId, birthday, email, gender, nickname, password, username);
	}

}
