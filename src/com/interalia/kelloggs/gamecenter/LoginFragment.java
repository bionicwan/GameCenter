package com.interalia.kelloggs.gamecenter;

import java.util.ArrayList;

import android.app.Activity;
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

public class LoginFragment extends Fragment implements OnClickListener, ResponseInterface {
	
	private Button btnLogin;
	private EditText txtUsername;
	private EditText txtPassword;
	private ResponseInterface response;
	private UserPojo user;
	private ListenerInterface listener;
	
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
	    txtUsername = (EditText)getView().findViewById(R.id.txtUsername);
	    txtPassword = (EditText)getView().findViewById(R.id.txtPassword);
	    btnLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String username = txtUsername.getText().toString();
		String password = txtPassword.getText().toString();
		LoginTask task = new LoginTask(this);
		task.execute(username, password);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (activity instanceof ListenerInterface) {
	        listener = (ListenerInterface) activity;
	      } else {
	        throw new ClassCastException(activity.toString()
	            + " must implemenet LoginFragment.ListenerInterface");
	      }
	}

	@Override
	public void onResultResponse(Object obj) {
		// TODO Auto-generated method stub
		listener.onDataPass(obj);
	}

}
