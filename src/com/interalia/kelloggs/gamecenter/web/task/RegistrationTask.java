package com.interalia.kelloggs.gamecenter.web.task;

import android.os.AsyncTask;

import com.interalia.kelloggs.gamecenter.pojo.ResultPojo;
import com.interalia.kelloggs.gamecenter.pojo.UserPojo;
import com.interalia.kelloggs.gamecenter.web.HttpPostClient;
import com.interalia.kelloggs.gamecenter.web.ResponseInterface;
import com.interalia.kelloggs.gamecenter.web.resultparsers.ResultParser;

public class RegistrationTask extends AsyncTask<String, Void, ResultPojo> {
	
	private ResponseInterface response = null;
	private ResultPojo resultPojo;
	private UserPojo userPojo;
	private ResultParser resultParser;
	private HttpPostClient httpClient = null;

	public RegistrationTask(ResponseInterface response){
		this.response = response;
		resultPojo = new ResultPojo();
		resultParser = new ResultParser();
		userPojo = new UserPojo();
	}
	
	@Override
	protected ResultPojo doInBackground(String... params) {
		// TODO Auto-generated method stub
		String url = "http://mgp-services.interalia.net/GameCenterService.svc/RegisterUser/?countryId=" 
				+ params[0]
				+ "&dateOfBirth=" + params[1]
				+ "&email=" + params[2]
				+ "&gender=" + params[3]
				+ "&nickname=" + params[4]
				+ "&password=" + params[5]
				+ "&username=" + params[6];
		
		httpClient = new HttpPostClient(url);
		String postResult = httpClient.postExecute(null);
		resultPojo = resultParser.parseJson(postResult);
		
		return resultPojo;
	}
	
	@Override
	protected void onPostExecute(ResultPojo resultPojo) {
		super.onPostExecute(resultPojo);
		response.onResultResponse(resultPojo);
	}

}
