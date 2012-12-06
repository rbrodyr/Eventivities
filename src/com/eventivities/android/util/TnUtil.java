package com.eventivities.android.util;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

public class TnUtil {

	
	public static int queNumero(String s){
		try{
			return Integer.parseInt(s);
		}catch( NumberFormatException e){
			return 0;
		}
	}
	
	public static void  vibrar(Context c){
		
		try{
			Vibrator v = (Vibrator) c.getSystemService(c.VIBRATOR_SERVICE);
			v.vibrate(30);
		}catch ( Exception e){
			escribeLog("VIBRAR","Error al intentar vibrar "+e.toString());
		}
	}
	
	public static void  suena(Context c){
		vibrar(c);
		/*
		try {
			android.media.ToneGenerator tg=new ToneGenerator(AudioManager.STREAM_NOTIFICATION,60);
			tg.startTone(ToneGenerator.TONE_DTMF_S);
			
		}catch( ExceptionInInitializerError e){
			DebugActivity.escribe("Error al intentar inicializar sonido "+e.toString());
		}catch (Exception e2){
			DebugActivity.escribe("Error al intentar sonar "+e2.toString());
		}
		*/
	}
	public static boolean queBoolean(String s){
		try{
			return Boolean.parseBoolean(s);
		}catch( NumberFormatException e){
			return false;
		}
	}
	
   public static void escribeLog(String Que,String txt){
	   
	   Log.d(Que,txt);
   }
   
   public static void escribeLog(String txt){
	   
	   escribeLog("Activities",txt);
   }
	

}

