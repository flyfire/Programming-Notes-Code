package com.itheima.mobileguard.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import com.itheima.mobileguard.db.CallSafeSQLiteOpenHelper;
import com.itheima.mobileguard.domain.BlackInfo;

/**
 * ʵ�ֶԺ���������curd
 * @author Administrator
 *
 */
public class BlackNumberDao {

		private CallSafeSQLiteOpenHelper helper;
		/**
		 * ��ʼ��helper��
		 * @param context
		 */
		public BlackNumberDao(Context context) {
			helper = new CallSafeSQLiteOpenHelper(context);
		}
		/**
		 * ���һ��������
		 * @param number ����������
		 * @param mode ģʽ
		 * @return ��ӳɹ����� true ����Ϊfalse
		 */
		public boolean insert(String number,String mode){
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues values  = new ContentValues();
			values.put("number", number);
			values.put("mode", mode);
			long result = db.insert("blackinfo", null, values);
			db.close();
			if(result == -1){
				return false;
			}else{
				return true;
			}
		}
		/**
		 * ���ĺ���������ģʽ
		 * @param number ����
		 * @param mode ģʽ
		 * @return ��ӳɹ����� true ����Ϊfalse
		 */
		public boolean update(String number,String mode){
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues values  = new ContentValues();
			values.put("mode", mode);
			int result = db.update("blackinfo", values, "number=?", new String[]{number});
			db.close();
			if(result == 0){
				return false;
			}
			else{
				return true;
			}
		}
		/**
		 * ɾ������������
		 * @param number ����
		 * @return ��ӳɹ����� true ����Ϊfalse
		 */
		public boolean delete(String number){
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues values  = new ContentValues();
			int result = db.delete("blackinfo", "number=?", new String[]{number});
			db.close();
			if(result == 0){
				return false;
			}
			else{
				return true;
			}
		}
		
		/**
		 * ���ҳ����еĺ�������Ϣ
		 * @return ���еĺ�������Ϣ
		 */
		public List<BlackInfo> findAll(){
			SQLiteDatabase db  = helper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select number,mode from blackinfo", null);
			List<BlackInfo> infos = new ArrayList<BlackInfo>();
			while(cursor.moveToNext()	){
				String number = cursor.getString(0);
				String mode = cursor.getString(1);
				infos.add(new BlackInfo(number,mode ));
			}
			cursor.close();
			db.close();
			SystemClock.sleep(3000);
			return infos;
		}
		
		/**
		 * 
		 * @param pageNumber ��ѯ��ҳ�� ���㿪ʼ
		 * @param pageSize 	ÿҳ��ʾ������
		 * @return
		 */
		public List<BlackInfo> findpart(int pageNumber,int pageSize){
			SQLiteDatabase db  = helper.getReadableDatabase();
			// ��ѯ����  ƫ�ƶ���
			Cursor cursor = db.rawQuery("select number,mode from blackinfo limit ? offset ? ", new String[]{
					String.valueOf(pageSize),String.valueOf(pageNumber*pageSize)
			});
			List<BlackInfo> infos = new ArrayList<BlackInfo>();
			while(cursor.moveToNext()	){
				String number = cursor.getString(0);
				String mode = cursor.getString(1);
				infos.add(new BlackInfo(number,mode ));
			}
			cursor.close();
			db.close();
			SystemClock.sleep(500);
			return infos;
		}
		/**
		 * ������������
		 * @param startIndex ��ѯ��ҳ�� ���㿪ʼ
		 * @param maxSize 	ÿҳ��ʾ������
		 * @return
		 */
		public List<BlackInfo> findpart2(int startIndex,int maxSize){
			SQLiteDatabase db  = helper.getReadableDatabase();
			// ��ѯ����  ƫ�ƶ��� Ӧ������ ��ӵ����ݲŻ���ʾ����ǰ�� limit�������
			Cursor cursor = db.rawQuery("select number,mode from blackinfo order by _id desc limit ? offset ? ", new String[]{
					String.valueOf(maxSize),String.valueOf(startIndex)
			});
			List<BlackInfo> infos = new ArrayList<BlackInfo>();
			while(cursor.moveToNext()	){
				String number = cursor.getString(0);
				String mode = cursor.getString(1);
				infos.add(new BlackInfo(number,mode ));
			}
			cursor.close();
			db.close();
			return infos;
		}
		
		
		/**
		 * ���ݺ����ѯ������ģʽ
		 * @param number
		 * @return
		 */
		public String findModeByNumber(String number){
			SQLiteDatabase db  = helper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select mode from blackinfo where number=?", new String[]{number});
			String mode = "0";
			if(cursor.moveToNext()){
				mode = cursor.getString(0);
			}
			db.close();
			cursor.close();
			return mode;
		}
		
		/**
		 * ��ѯ����¼������
		 * @return
		 */
		public int getTotalRecord(){
			SQLiteDatabase db  = helper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select count(*) from blackinfo", null);
			int page = 0;
			if(cursor.moveToNext()){
				page = cursor.getInt(0);
			}
			return page;
		}
}













