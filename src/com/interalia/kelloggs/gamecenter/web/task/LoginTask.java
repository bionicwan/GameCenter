package com.interalia.kelloggs.gamecenter.web.task;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONException;

import android.os.AsyncTask;

import com.interalia.kelloggs.gamecenter.pojo.ResultPojo;
import com.interalia.kelloggs.gamecenter.pojo.UserPojo;
import com.interalia.kelloggs.gamecenter.web.HttpPostClient;
import com.interalia.kelloggs.gamecenter.web.ResponseInterface;
import com.interalia.kelloggs.gamecenter.web.resultparsers.ResultParser;
import com.interalia.kelloggs.gamecenter.web.resultparsers.UserParser;

public class LoginTask extends AsyncTask<String, Void, UserPojo> {

	private HttpPostClient httpClient = null;
	private ResponseInterface response = null;
	private ResultPojo resultPojo;
	private UserPojo userPojo;
	private ResultParser resultParser;
	private UserParser userParser;
	//private String url = "http://mgp-services.interalia.net/GameCenterService.svc/UserLogin/?email=jccm_899@hotmail.com&password=12345678";
	
	public LoginTask(ResponseInterface response){
		this.response = response;
		resultPojo = new ResultPojo();
		resultParser = new ResultParser();
		userPojo = new UserPojo();
		userParser = new UserParser();
	}
	
	@Override
	protected UserPojo doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String url = "http://mgp-services.interalia.net/GameCenterService.svc/UserLogin/?email=" + arg0[0] + "&password=" + arg0[1];
		httpClient = new HttpPostClient(url);
		String postResult = httpClient.postExecute(null);
		resultPojo = resultParser.parseJson(postResult);
		try {
			userPojo = userParser.parseUserItem(resultPojo.getContent());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return userPojo;
	}
	
	@Override
	protected void onPostExecute(UserPojo resultUser) {
		super.onPostExecute(resultUser);
		response.onResultResponse(resultUser);
	}

}
