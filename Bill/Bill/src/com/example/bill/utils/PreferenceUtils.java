package com.example.bill.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.Preference;

public class PreferenceUtils {

	/**
	 * ����boolean
	 */

	public static void setBoolean(Context context, String key, boolean value) {

		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();

	}

	/**
	 * ȡ��boolean
	 * 
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {

		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		boolean result = sp.getBoolean(key, defValue);
		return result;

	}
	
	
	/**
	 * ����int
	 */
	
	public static void setInt(Context context, String key, int value) {
		
		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
		
	}
	
	/**
	 * ȡ��int
	 * 
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		
		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		int result = sp.getInt(key, defValue);
		return result;
		
	}
}