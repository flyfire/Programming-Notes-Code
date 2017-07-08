package com.itheima.mobileguard.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Xml;

public class SmsUtils {
	/**
	 * ���ű��ݵĻص���������ִ�ж��ű���ʱ���������ڲ�������
	 * 
	 * @author Administrator
	 * 
	 */
	public interface SmsBackupCallBack {
		/**
		 * ��ʼ����ʱ���� �ṩ��Ҫ���ݵ�������
		 * 
		 * @param size
		 *            ���ŵ�����
		 */
		public void onStartBackup(int size);

		/**
		 * ���ݹ����е��� �ṩ��ǰ����
		 * 
		 * @param process
		 *            �ѱ��ݵ�����
		 */
		public void onBackuping(int process);
	}

	/**
	 * ���ű��ݿ��ܱȽϺ�ʱ, ��Ӧ�������߳��е��� ,�ڲ��Զ������ݽ����˼��ܣ����Է���ʹ��
	 * 
	 * @param context
	 *            ������
	 * @param callback
	 *            ���ݻص�
	 * @return �ɹ��򷵻�true
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static boolean backupSms(Context context, SmsBackupCallBack callback)
			throws IllegalArgumentException, IllegalStateException, IOException {
		// ����֮ǰ���ж�sd���Ƿ�׼����ϣ����ж�sd���Ĵ�С
		File sdCard = Environment.getExternalStorageDirectory();// sd����·��
		long usableSpace = sdCard.getFreeSpace();
		// sd�����ò��ҿռ����1M �ſɱ��� �����ܳ��쳣
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)
				&& usableSpace >= 1024l * 1024l) {
			// ���������ṩ�߻�ȡ��������
			ContentResolver resolver = context.getContentResolver();
			// ����uri
			Uri uri = Uri.parse("content://sms/");
			Cursor cursor = resolver.query(uri, new String[] { "address",
					"body", "date", "type" }, null, null, null);
			// ��ȥ���ŵ�����
			int count = cursor.getCount();
			callback.onStartBackup(count);

			// ����xml���л��� �Ѷ��˱��ݵ�xml�ļ���ȥ
			XmlSerializer serializer = Xml.newSerializer();
			FileOutputStream fos = new FileOutputStream(new File(sdCard,
					"smsbackup.xml"));
			serializer.setOutput(fos, "UTF-8");
			serializer.startDocument("UTF-8", true);
			serializer.startTag(null, "smss");
			serializer.attribute(null, "size", String.valueOf(count));// �������
																		// ���ŵ�����

			int process = 0;
			while (cursor.moveToNext()) {
				serializer.startTag(null, "sms");

				serializer.startTag(null, "address");
				serializer.text(cursor.getString(0));
				serializer.endTag(null, "address");

				// ���ܶ���
				serializer.startTag(null, "body");
				try {
					String cipherBody = Crypto.encrypt("zhantianyou12345",
							cursor.getString(1));
					serializer.text(cipherBody);
				} catch (Exception e) {
					e.printStackTrace();
					serializer.text("�ն���");
				}
				serializer.endTag(null, "body");

				serializer.startTag(null, "date");
				serializer.text(cursor.getString(2));
				serializer.endTag(null, "date");
				serializer.startTag(null, "type");
				serializer.text(cursor.getString(3));
				serializer.endTag(null, "type");

				serializer.endTag(null, "sms");

				SystemClock.sleep(500);

				process++;
				callback.onBackuping(process);
			}
			serializer.endTag(null, "smss");
			serializer.endDocument();
			fos.close();
			cursor.close();
			return true;
		} else {
			throw new IllegalStateException("sd�������Ի��߿ռ䲻��");
		}
	}

	/**
	 * ���ű��ݵĻص���������ִ�ж��ű���ʱ���������ڲ�������
	 * 
	 * @author Administrator
	 * 
	 */
	public interface SmsRestoreCallBack {
		/**
		 * ��ʼ��ԭʱ���� �ṩ��Ҫ��ԭ��������
		 * 
		 * @param size
		 *            ���ŵ�����
		 */
		public void onStartRestore(int size);

		/**
		 * ��ԭ�����е��� �ṩ��ǰ����
		 * 
		 * @param process
		 *            �ѻ�ԭ������
		 */
		public void onRestoreing(int process);
	}

	/**
	 * 
	 * @param context
	 * @param callback
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static boolean resotreSms(Context context,
			SmsRestoreCallBack callback) throws XmlPullParserException, IOException,IllegalStateException{
		// �ж� �Ƿ񱸷��ļ����� ��ȡsd���� �ļ�
			File smsFile = new File(Environment.getExternalStorageDirectory(),"smsbackup.xml");
			if(smsFile.exists()){
				// ����xml�ļ���
				// 1. ����pull������
				XmlPullParser parser = Xml.newPullParser();
				// 2.��ʼ��pull�����������ñ��� inputstream
				FileInputStream fis =  new FileInputStream(smsFile);
				parser.setInput(fis, "utf-8");
				int eventType = parser.getEventType();
				// 3.����xml�ļ� while(�ĵ�ĩβ��
				// {
				// ��ȡ���� size �ܸ�����. ���ýӿڵķ��� beforeSmsRestore
				// ÿ��ȡ��һ������ �Ͱ�������� body�����ܣ� address date type��ȡ����
				// ���������ṩ�� resolver.insert(Uri.parse("content://sms/"),contentValue);
				// ÿ��ԭ�� count++ ����onSmsRestore(count);
				// }
				//�õ����ݽ�����
				ContentResolver resolver = context.getContentResolver();
				Uri uri = Uri.parse("content://sms/");
				String address = null;
				String body = null;
				String date = null;
				String type = null;
				ContentValues values = null;
				int count = 0;
				while(eventType != XmlPullParser.END_DOCUMENT){//û�н�������β�Ͳ�ֹͣ
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if("smss".equals(parser.getName())){
							String size = parser.getAttributeValue(null, "size");
							callback.onStartRestore(Integer.parseInt(size));
						}else if("sms".equals(parser.getName())){
							
						}else if("address".equals(parser.getName())){
							address = parser.nextText();
						}else if("body".equals(parser.getName())){
							body = parser.nextText();
						}else if("date".equals(parser.getName())){
							date = parser.nextText();
						}else if("type".equals(parser.getName())){
							type = parser.nextText();
						}
						break;

					case XmlPullParser.END_TAG:
						 if("sms".equals(parser.getName())){
								 values = new ContentValues();
								 values.put("address", address);
								 
								 if("�ն���".equals(body)){
									 values.put("body", "");
								 }else{
									 try {
										values.put("body", Crypto.decrypt("zhantianyou12345", body));
									} catch (Exception e) {
										 values.put("body", "");
										e.printStackTrace();
									}
								 }
								
								 values.put("date", date);
								 values.put("type", type);
								 resolver.insert(uri, values);
								 count ++;
								 callback.onRestoreing(count);
							}
						break;
					}
					eventType = parser.next();
				}
				return true;
			}else{
				throw new IllegalStateException("�ļ�û���ҵ�");
			}
	}
	
	public  static void deleteSms(Context context){
		Uri uri = Uri.parse("content://sms/");
		ContentResolver resolver = context.getContentResolver();
		resolver.delete(uri, null, null);
	}
}
