package com.interalia.kelloggs.gamecenter.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

import android.util.Base64;

public class RequestSigning {
	private static String API_KEY = "d334c1e7-d2f9-44b8-9390-9f64d53885d3";
	private static String APP_SECRET = "dc479d12-32d3-4f12-a0ff-9bb37190e347";
	private byte[] secretBytes;
	private byte[] unsignedSignature;

	public String signRequest(String url) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeyException {
		
		//String encodedUrl = URLEncoder.encode(url, "UTF-8");
		String baseString = url + API_KEY;
		String type = "HmacSHA1";
		
		secretBytes = APP_SECRET.getBytes("UTF-8");
		Mac mac = Mac.getInstance(type);
		SecretKeySpec secret = new SecretKeySpec(APP_SECRET.getBytes("UTF-8"), mac.getAlgorithm());
		mac.init(secret);
		byte[] signatureBytes = mac.doFinal(baseString.getBytes("UTF-8"));
		String signature = Base64.encodeToString(signatureBytes, Base64.NO_WRAP);
		
		return signature;
	}
}
