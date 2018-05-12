package com.itheima.mobileguard.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.mobileguard.db.AppLockSQLiteOpenHelper;

public class LockedAppDao {
	//���ݿ������
	private AppLockSQLiteOpenHelper helper;

	public LockedAppDao(Context context) {
		helper = new AppLockSQLiteOpenHelper(context);
	}
	/**
	 * ��ѯĳ��APP�Ƿ��Ѿ�������
	 * @return
	 */
	public boolean isLocked(String packagename){
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select _id from infos where packagename=?", new String[]{packagename});
		int count = cursor.getCount();
		db.close();
		cursor.close();
		if(count != 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * ��ѯ���м����˵�app
	 * @return
	 */
	public List<String> findAll(){
		SQLiteDatabase db = helper.getReadableDatabase();
		List<String> names = new ArrayList<String>();
		Cursor cursor = db.rawQuery("select packagename from infos",null);
		while(cursor.moveToNext()){
			names.add(cursor.getString(0));
		}
		db.close();
		cursor.close();
		return names;
	}
	
	/**
	 * ���һ��������APP��Ϣ
	 * @param packagename
	 * @return
	 */
	public boolean add(String packagename){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new  ContentValues();
		values.put("packagename", packagename);
		long result = 	db.insert("infos", null, values);
		db.close();
		if(result != 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * ���������� ɾ����Ϣ
	 */
	public boolean delete(String packagename){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new  ContentValues();
		values.put("packagename", packagename);
		int result = db.delete("infos", "packagename=?", new String[]{packagename});
		db.close();
		if(result != 0){
			return true;
		}else{
			return false;
		}
	}
}
