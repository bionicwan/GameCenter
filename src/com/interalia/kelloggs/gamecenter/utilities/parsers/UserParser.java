package com.interalia.kelloggs.gamecenter.utilities.parsers;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.interalia.kelloggs.gamecenter.pojo.CountryPojo;
import com.interalia.kelloggs.gamecenter.pojo.CountryPojo;
import com.interalia.kelloggs.gamecenter.pojo.UserAvatarPojo;
import com.interalia.kelloggs.gamecenter.pojo.UserAvatarPojo;
import com.interalia.kelloggs.gamecenter.pojo.UserPojo;

public class UserParser {
	
	private static final String KEY_USERNAME = "UserName";
	private static final String KEY_PASSWORD = "Password";
	private static final String KEY_NICKNAME = "NickName";
	private static final String KEY_TOKEN = "Token";
	private static final String KEY_EMAIL = "Email";
	private static final String KEY_COUNTRY = "Country";
	private static final String KEY_COUNTRY_ID = "CountryId";
	private static final String KEY_COUNTRY_DESC = "Description";
	private static final String KEY_GENDER = "Gender";
	private static final String KEY_BIRTHDAY = "DateOfBirth";
	private static final String KEY_AVATAR = "UserAvatar";
	private static final String KEY_AVATAR_ID = "UserAvatarId";
	private static final String KEY_AVATAR_PIC = "Picture";
	
	public UserPojo parseUserItem(String jsonResult) throws JSONException, ParseException, UnsupportedEncodingException{
		
		JSONObject object = new JSONObject(jsonResult);
		UserPojo userPojo = new UserPojo();
		userPojo.setUsername(object.optString("KEY_USERNAME"));
		userPojo.setPassword(object.optString(KEY_PASSWORD));
		userPojo.setNickname(object.optString(KEY_NICKNAME));
		userPojo.setEmail(object.optString(KEY_EMAIL));
		userPojo.setToken(object.optString(KEY_TOKEN));
		userPojo.setCountryName(object.optString(KEY_COUNTRY));
		userPojo.setGender(object.optString(KEY_GENDER));
		userPojo.setDateOfBirth(parseDate(object.optString(KEY_BIRTHDAY)));
		userPojo.setPicture(object.optString(KEY_AVATAR).getBytes());
		
		return userPojo;
	}

	private Date parseDate(String date) throws ParseException {
		if(date.equals(""))
		{
			return null;
		}
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    cal.setTime(sdf.parse(date));
	    Date dob = cal.getTime();
	    return dob;
	}
}
