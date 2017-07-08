package com.itheima.mobileguard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Ӧ�ô����ݿ�İ�����
 * @author Administrator
 *
 */
public class CallSafeSQLiteOpenHelper extends SQLiteOpenHelper {

	public CallSafeSQLiteOpenHelper(Context context) {
		super(context, "callsafe.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		
		 // �������������
			db.execSQL("create table blackinfo(_id integer primary key autoincrement,number varchar(20),mode varchar(2))");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
