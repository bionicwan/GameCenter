package com.interalia.kelloggs.gamecenter.web.resultparsers;

import org.json.JSONObject;

import com.interalia.kelloggs.gamecenter.pojo.ResultPojo;

public class ResultParser {
	private static String KEY_SUCCESS = "Success";
	private static String KEY_MESSAGE = "Message";
	private static String KEY_CONTENT = "Content";
	
	public ResultPojo parseJson(String json) {
		ResultPojo pojo = new ResultPojo();
				
		try {
			JSONObject jsonObj = new JSONObject(json);
			String operation = json.substring(json.indexOf("\"") + 1, json.indexOf("Result")) + "Result";
			JSONObject inner = jsonObj.optJSONObject(operation);
			pojo.setSuccess(inner.optBoolean(KEY_SUCCESS));
			pojo.setMessage(inner.optString(KEY_MESSAGE));
			pojo.setContent(inner.optString(KEY_CONTENT));
		} catch (Exception ex) {
			pojo.setSuccess(false);
			pojo.setMessage("Falló parseo");
		}
		return pojo;
	}
}
