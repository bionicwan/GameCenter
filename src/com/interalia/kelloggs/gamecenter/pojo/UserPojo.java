package com.interalia.kelloggs.gamecenter.pojo;

import java.util.Date;

public class UserPojo {
	private String username;
	private String password;
	private String nickname;
	private String token;
	private String email;
	private Date dateOfBirth;
	private String gender;
	private byte[] picture;
	private String countryName;
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	private CountryPojo country;
	private UserAvatarPojo avatar;

	public void setCountry(CountryPojo country) {
		this.country = country;
	}

	public void setAvatar(UserAvatarPojo avatar) {
		this.avatar = avatar;
	}

	public CountryPojo getCountry() {
		return country;
	}

	public UserAvatarPojo getAvatar() {
		return avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
