package com.interalia.kelloggs.gamecenter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.interalia.kelloggs.gamecenter.pojo.CountryPojo;
import com.interalia.kelloggs.gamecenter.pojo.ResultPojo;
import com.interalia.kelloggs.gamecenter.pojo.UserPojo;
import com.interalia.kelloggs.gamecenter.web.ResponseInterface;
import com.interalia.kelloggs.gamecenter.web.task.RegistrationTask;

public class RegistrationActivity extends Activity implements ResponseInterface, OnClickListener {

	private Spinner items;
	private Button btnSignUp;
	private EditText txtUsername;
	private EditText txtPassword;
	private EditText txtEmail;
	private EditText txtBirthday;
	private EditText txtNickname;
	private CheckBox chkMale;
	private ResultPojo resultPojo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_activity);
		
		btnSignUp = (Button)findViewById(R.id.btnSignUp);
		txtUsername = (EditText)findViewById(R.id.txtUsername);
		txtPassword = (EditText)findViewById(R.id.txtPassword);
		txtEmail = (EditText)findViewById(R.id.txtEmail);
		txtBirthday = (EditText)findViewById(R.id.txtBirthday);
		txtNickname = (EditText)findViewById(R.id.txtNickname);
		chkMale = (CheckBox)findViewById(R.id.chkMale);
		
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
		if(resultPojo.isSuccess()){
			Context context = getApplicationContext();
			CharSequence text = "Succesfully registered!";
			int duration = Toast.LENGTH_SHORT;

			Toast.makeText(context, text, duration).show();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String username = txtUsername.getText().toString();
		String password = txtPassword.getText().toString();
		String email = txtEmail.getText().toString();
		String nickname = txtNickname.getText().toString();
		String birthday = txtBirthday.getText().toString();
		String gender;
		if(chkMale.isChecked()){
			gender = "m";
		}
		else{
			gender = "f";
		}
		String countryId = "1";
/*		userPojo.setUsername(txtUsername.getText().toString());
		userPojo.setPassword(txtPassword.getText().toString());
		userPojo.setEmail(txtEmail.getText().toString());
		userPojo.setNickname(txtNickname.getText().toString());
		
		String birthday = txtBirthday.getText().toString();
		
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	    try {
			cal.setTime(sdf.parse(birthday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();;
			Context context = getApplicationContext();
			CharSequence text = "Invalid date format";
			int duration = Toast.LENGTH_SHORT;

			Toast.makeText(context, text, duration).show();
		}
	    Date dob = cal.getTime();
	    
	    userPojo.setDateOfBirth(dob);
	    CountryPojo country = new CountryPojo();
	    country.setCountryId(1);
	    userPojo.setCountry(country);*/
	    
	    RegistrationTask registrationTask = new RegistrationTask(this);
	    registrationTask.execute(countryId, birthday, email, gender, nickname, password, username);
	}

}
