package com.interalia.kelloggs.gamecenter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.interalia.kelloggs.gamecenter.infrastructure.ListenerInterface;
import com.interalia.kelloggs.gamecenter.pojo.UserPojo;
import com.interalia.kelloggs.gamecenter.utilities.ActivitySplitAnimationUtil;

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
        transaction.add(R.id.main_layout, loginFragment, "login");
        if(token != "")
        {
        	transaction.replace(R.id.main_layout, welcomeFragment);
        }
        
        transaction.commit();
    }

    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		ActivitySplitAnimationUtil.prepareAnimation(this);
		// Animation duration of 1 second
        ActivitySplitAnimationUtil.animate(this, 2000);
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
	    
		if(token != "" && token != null){
			SharedPreferences settings = getSharedPreferences("AppSettings", 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putString("AccessToken", token);
		    editor.commit();
		    
		    transaction.replace(R.id.main_layout, welcomeFragment);
		    transaction.commit();
		}

	}
    
}
