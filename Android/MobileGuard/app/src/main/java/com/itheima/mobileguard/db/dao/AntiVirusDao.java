package com.itheima.mobileguard.db.dao;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AntiVirusDao {

	/**
	 * �жϲ������ݿ��Ƿ����
	 * @param context
	 * @return
	 */
	public static boolean isVirusDBExists(Context context) {
		File file = new File(context.getFilesDir(), "antivirus.db");
		return file.exists();
	}
	/**
	 * ��ȡ������İ汾
	 * @param context
	 * @return ��ȡʧ�ܷ���-1
	 */
	public static String  getVirusVersion(Context context){
		 SQLiteDatabase db = SQLiteDatabase.openDatabase( new File(context.getFilesDir(), "antivirus.db").getAbsolutePath()
				, null, SQLiteDatabase.OPEN_READONLY);
		 Cursor cursor = db.rawQuery("select subcnt from version", null);
		 String result = "0";
		 if(cursor.moveToNext()){//�н�� ��ȡ��� 
			 result =  cursor.getString(0);
		 }
		 db.close();
		 cursor.close();
		 return result;
	}
	/**
	 * �������ݿ�汾
	 * @param context ������
	 * @param version �汾
	 * @return
	 */
	public static void updateVirusVersoin(Context context,String version){
		SQLiteDatabase db = SQLiteDatabase.openDatabase( new File(context.getFilesDir(), "antivirus.db").getAbsolutePath()
				, null, SQLiteDatabase.OPEN_READWRITE);
		ContentValues values = new ContentValues();
		values.put("subcnt", version);
		db.insert("version", null, values);
		db.close();
	}
	/**
	 * �������ݿ�API
	 * @param context ������
	 * @param md5Code md5��
	 * @return
	 */
	public static void updateVirusAPI(Context context,String md5Code,String desc){
		SQLiteDatabase db = SQLiteDatabase.openDatabase( new File(context.getFilesDir(), "antivirus.db").getAbsolutePath()
				, null, SQLiteDatabase.OPEN_READWRITE);
		ContentValues values = new ContentValues();
		values.put("md5", md5Code);
		values.put("name", "xx����");
		values.put("type",6);
		values.put("desc", desc);
		db.insert("datable", null, values);
		db.close();
	}
	
	/**
	 * ���Ӧ�ó����Ƿ��ǲ���
	 * @param md5Code
	 * @return �ǲ������ز������� ���򷵻�null
	 */
	public static String check(Context context,String md5Code){
		 SQLiteDatabase db = SQLiteDatabase.openDatabase( new File(context.getFilesDir(), "antivirus.db").getAbsolutePath()
					, null, SQLiteDatabase.OPEN_READONLY);
			 Cursor cursor = db.rawQuery("select desc from datable where md5=?", new String[]{md5Code});
			 String desc = null;
			 if(cursor.moveToNext()){
				 desc = cursor.getString(0);
			 }
			 db.close();
			 cursor.close();
			 return desc;
	}
	
}
