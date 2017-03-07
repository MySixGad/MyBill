package com.example.bill.db.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bill.db.MyHelper;

public class DataDao {

	/**
	 * 插入信息
	 */
	public static boolean insertInfo(Context context, String[] data) {
		MyHelper help = new MyHelper(context);
		SQLiteDatabase db = help.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("pay_date", data[0]);
		values.put("pay_person", data[1]);
		values.put("pay_type", data[2]);
		values.put("pay_detail", data[3]);
		values.put("pay_rmb", data[4]);
		values.put("pay_way", data[5]);
		values.put("other", data[6]);
		long id = db.insert("pay_table", null, values);
		return id != -1;
	}

	/**
	 * 查看
	 * 
	 */
	public static ArrayList<String> showInfo(Context context,String date_info) {
		ArrayList<String> list = new ArrayList<String>();
		MyHelper help = new MyHelper(context);
		SQLiteDatabase db = help.getReadableDatabase();
		
		System.out.println(date_info+"..........");
		String[] dates={date_info};
		Cursor cursor = db.rawQuery("select * from pay_table where pay_date= ? ",
				dates);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String date = cursor.getString(1);
			String person = cursor.getString(2);
			String type = cursor.getString(3);
			String detail = cursor.getString(4);
			String rmb = cursor.getString(5);
			String way = cursor.getString(6);
			String other = cursor.getString(7);
			String rem_person = cursor.getString(8);
			String rem_date = cursor.getString(9);
			list.add(date);
			list.add(person);
			list.add(type);
			list.add(detail);
			list.add(rmb);
			list.add(way);
			list.add(other);
			list.add(rem_person);
			list.add(rem_date);

		}

		return list;
	}

}
