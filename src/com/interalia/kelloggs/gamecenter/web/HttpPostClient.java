package com.interalia.kelloggs.gamecenter.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.client.HttpClient;

import com.interalia.kelloggs.gamecenter.infrastructure.Sys;
import com.interalia.kelloggs.gamecenter.utilities.RequestSigning;

import android.content.Context;
import android.util.Base64;

public class HttpPostClient {
	URL urlPost = null;
	String dataPost = "";
	boolean first = true;
	BufferedReader reader = null;
	HttpURLConnection conn;
	HttpClient httpClient = null;
	private int waitTime = 20000;
	String text = "";
	private RequestSigning signRequest = null;

	public HttpPostClient(String url) {
		try {
			urlPost = new URL(url);
			Sys.log(url.toString());
		} catch (MalformedURLException e) {
			Sys.log("Url mal creada");
		}
	}

	public void postData(String id, String value) {
		try {
			if (first)
				dataPost = URLEncoder.encode(id, "UTF-8") + "="
						+ URLEncoder.encode(value, "UTF-8");
			else
				dataPost += "&" + URLEncoder.encode(id, "UTF-8") + "="
						+ URLEncoder.encode(value, "UTF-8");
			first = false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String postExecute(Context context) {
		String text = "";
		String signature = "";
		try {
			signRequest = new RequestSigning();
			signature = signRequest.signRequest(urlPost.toString());
			conn = (HttpURLConnection) urlPost.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(waitTime);
			
			byte[] secret = "dc479d12-32d3-4f12-a0ff-9bb37190e347".getBytes("UTF-8");
			String secretBase64 = Base64.encodeToString(secret, 2);
			conn.setRequestProperty("Authorization", secretBase64);
			conn.setRequestProperty("signature", signature);
			
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8");
			Sys.log(dataPost);
			wr.write(dataPost);
			wr.flush();

			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String line = null;

			// Read Server Response
			while ((line = reader.readLine()) != null) {
				// Append server response in string
				sb.append(line);
			}

			text = URLDecoder.decode(sb.toString(), "UTF-8");

			Sys.log(text);
		} catch (java.net.SocketTimeoutException e) {
			text = "TimeOut";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Sys.log(e.toString());
		}
		conn.disconnect();

		return text;
	}

	public String getExecute(Context context) {
		String text = "";
		try {
			conn = (HttpURLConnection) urlPost.openConnection();
			conn.setRequestMethod("GET");

			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String line = null;

			// Read Server Response
			while ((line = reader.readLine()) != null) {
				// Append server response in string
				sb.append(line);
			}

			text = sb.toString();
			Sys.log(text);
		} catch (java.net.SocketTimeoutException e) {
			text = "TimeOut";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// Log.i("LogIron", "error " + e.toString());
			text = "TimeOut";
			Sys.log(e.toString());
		}

		return text;

	}

}
