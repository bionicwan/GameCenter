package com.interalia.kelloggs.gamecenter.infrastructure;


import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Sys {
	

	public static final boolean DEPURANDO = true;
	private static final boolean LOG_TO_FILE = false;
	public static String IMEI = null;
	public static final int TIMEOUT = 30;
	
    
	public static void log(String texto){
		Sys.log("titulo", texto);
	}
	
	
	public static void log(String tag, String texto){
		if (DEPURANDO)
			Log.i(tag, texto);
	}
	
	
	public static void log(Throwable e){
		if (DEPURANDO){
			e.printStackTrace();
			
			if (LOG_TO_FILE){
				logToFile(e);
			}
		}
	}
	
	
	public static String getIMEI(Context ctx){
		if (IMEI == null){
			String imei = null;
			
			try{
				imei = ((TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
				
				if (imei == null){
					imei = "";
				}
			}catch(Exception e){
				imei = "";
				Sys.log(e);
			}
			
			IMEI = imei;
		}
		
		Sys.log("IMEI: " + IMEI);
		return IMEI;
	}
	
	public static void logToFile(Throwable e){
		try{
			StringBuffer sb = new StringBuffer(e.toString() + "\n");
			StackTraceElement[] stElements = e.getStackTrace();
			String newLine = "";
			
			for (StackTraceElement stElement : stElements){
				sb.append(newLine);
				sb.append("\tat ");
				sb.append(stElement.toString());
				newLine = "\n";
			}
			
		}catch(Exception ee){
			e.printStackTrace();
		}
	}
}