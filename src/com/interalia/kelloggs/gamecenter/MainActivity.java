package com.interalia.kelloggs.gamecenter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.interalia.kelloggs.gamecenter.pojo.UserPojo;
import com.interalia.kelloggs.gamecenter.web.ListenerInterface;

public class MainActivity extends FragmentActivity implements ListenerInterface{
	
	private LoginFragment loginFragment;
	private WelcomeFragment welcomeFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SharedPreferences settings = getSharedPreferences("AppSettings", 0);
        String token = settings.getString("AccessToken", "");
        
        FragmentManager fm = getSupportFragmentManager();
        loginFragment = LoginFragment.newInstance(this);
        welcomeFragment = new WelcomeFragment();
        
        FragmentTransaction transaction = fm.beginTransaction();
        
        if(token != "")
        {
        	transaction.add(R.id.main_layout, welcomeFragment, "welcome");
        }
        else
        {
        	transaction.add(R.id.main_layout, loginFragment, "login");
        }
        
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onDataPass(Object o) {
		// TODO Auto-generated method stub
		UserPojo user = (UserPojo) o;
		String token = user.getToken();
		FragmentManager fm = getSupportFragmentManager();
	    FragmentTransaction transaction = fm.beginTransaction();
	    
		if(token != ""){
			SharedPreferences settings = getSharedPreferences("AppSettings", 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putString("AccessToken", token);
		    editor.commit();
		    
		    transaction.replace(R.id.main_layout, welcomeFragment);
		    transaction.commit();
		}

	}
    
}
