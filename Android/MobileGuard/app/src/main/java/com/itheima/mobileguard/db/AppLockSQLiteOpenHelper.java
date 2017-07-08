package com.itheima.mobileguard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AppLockSQLiteOpenHelper extends SQLiteOpenHelper {

	
	public AppLockSQLiteOpenHelper(Context context) {
		super(context, "lockedapp.db", null, 1);
	}
	
	public void onCreate(SQLiteDatabase db) {
		//����һ�ű� ���ڴ洢�Ѿ������ı��
				db.execSQL("create table infos(_id integer primary key autoincrement,packagename varchar(20))");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
