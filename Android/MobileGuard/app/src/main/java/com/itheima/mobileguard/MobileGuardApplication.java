package com.itheima.mobileguard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Date;

import android.app.Application;
import android.os.Build;
import android.os.Environment;

/**
 * һ��Ӧ�ó���ֻ���ʼ��һ��application���󣬸ö�������Ӧ�ó���
 * ά�����ǵ�ǰӦ�ó����ȫ��״̬
 * ��һʵ�� ��̨ģʽ
 * 
 * �嵥�ļ���Ҫ����
 * 
 * @author Administrator
 *
 */
public class MobileGuardApplication extends Application {

/**
 * Ӧ�ó����ڵ�һ�α�������ʱ����õķ��������κ�����󴴽�֮ǰִ�е��߼�
 */
	public void onCreate() {
		Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
		super.onCreate();
	}
	
	private class MyExceptionHandler implements UncaughtExceptionHandler{
		/**
		 * ��������δ�����쳣ʱ��������ֹ��������Ƴ���ֻ����������˳�֮ǰ��exception��Ϣ��¼����
		 * �������Ա�޸�bug
		 */
		public void uncaughtException(Thread thread, Throwable ex) {
			//��һ����Ϣ���浽����
			try {
				Date date = new Date();
				File file = new File(Environment.getExternalStorageDirectory(),"exception.log");
				PrintWriter pw = new PrintWriter(new FileWriter(file,true),true);
				System.out.println("�������쳣�����Ǳ��粶����");
				//���ֳ��̵�build����ʵ�ֿ��ܲ�һ�����е��ֶο���û�У�����ͨ��������е��ֶζ���¼����
				//build�����û�ʹ���ֻ��ĸ�����Ϣ ���豸�ĸ�����Ϣ
				Field[] fields = Build.class.getDeclaredFields();
				StringBuilder sb = new StringBuilder();
				pw.println(date.toLocaleString());
				for(Field field  : fields){
					System.out.println(field.getName()+":::"+field.get(null));
					pw.println(field.getName()+":::"+field.get(null));
				}
				ex.printStackTrace(pw);
				pw.close();
				//�����糬�� ɱ���Լ��Ľ��� ϵͳ����Ϊ��ʱ������ֹ ���������ý���
				android.os.Process.killProcess(android.os.Process.myPid());
				//ԭ�ظ���
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class MyExecptionHandler implements UncaughtExceptionHandler{
		//���̳߳�����δ������쳣ִ�еķ�����
		//������ֹjava������˳���ֻ����jvm�˳�֮ǰ�� ����һ��ʱ�䣬 ��һ������
		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				System.out.println("�������쳣�����Ǳ���������ˡ�");
				Field[] fileds = Build.class.getDeclaredFields();
				for(Field filed:fileds){
					System.out.println(filed.getName()+"--"+filed.get(null));
					sw.write(filed.getName()+"--"+filed.get(null)+"\n");
				}
				ex.printStackTrace(pw);
				File file = new File(Environment.getExternalStorageDirectory(),"log.txt");
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(sw.toString().getBytes());
				fos.close();	
				pw.close();
				sw.close();
				//�����糬��
				android.os.Process.killProcess(android.os.Process.myPid());
				//ԭ�ظ���
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	/**
	 * �ڴ治��ʱ���õķ���
	 */
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
	
		
}
