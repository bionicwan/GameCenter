package com.interalia.kelloggs.gamecenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.interalia.kelloggs.gamecenter.pojo.UserPojo;
import com.interalia.kelloggs.gamecenter.web.ListenerInterface;
import com.interalia.kelloggs.gamecenter.web.ResponseInterface;
import com.interalia.kelloggs.gamecenter.web.task.LoginTask;

public class LoginFragment extends Fragment implements OnClickListener {
	
	private Button btnLogin;
	private Button btnRegister;
	private EditText txtUsername;
	private EditText txtPassword;
	private ResponseInterface response;
	private UserPojo user;
	private ListenerInterface listener;
	
	public static LoginFragment newInstance(ListenerInterface listener){
		LoginFragment login = new LoginFragment();
		login.listener = listener;
		return login;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.login_fragment, 
	            container, false);
	    return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	    
	    btnLogin = (Button)getView().findViewById(R.id.btnLogin);
	    btnRegister = (Button)getView().findViewById(R.id.btnRegister);
	    txtUsername = (EditText)getView().findViewById(R.id.txtUsername);
	    txtPassword = (EditText)getView().findViewById(R.id.txtPassword);
	    btnLogin.setOnClickListener(this);
	    btnRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		switch(view.getId()){
		case R.id.btnLogin:
			String username = txtUsername.getText().toString();
			String password = txtPassword.getText().toString();
			LoginTask task = new LoginTask(new ResponseInterface() {
				@Override
				public void onResultResponse(Object obj) {
					// TODO Auto-generated method stub
					listener.onDataPass(obj);
				}
			});
			task.execute(username, password);
			
		case R.id.btnRegister:
			Intent i = new Intent(getActivity(), RegistrationActivity.class);
			startActivity(i);
		}
			
			
	}
}
