package com.itheima.mobileguard.services;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;

import com.itheima.mobileguard.activities.EnterPwdActivity;
import com.itheima.mobileguard.db.dao.LockedAppDao;
/**
 * ��ѯ��ǰ����ջ����activity �ж��Ƿ���˳�����
 * 
 * @author Administrator
 * 
 */
public class WatchDagService extends Service {

	private LockedAppDao dao;// ���ݿ��ѯ
	private ActivityManager am;// ���̹���
	private RunningTaskInfo taskInfo;// ����ջ��Ϣ
	private List<String> packageNames;// �������İ�������
	private String packageName;
	private Intent intent;
	private String tempStopProtectPackageName;//��ʱȡ��������APP
	private WatchDogReceiver receiver;//���Ź�����������Ϣ�Ľ�����
	private boolean flag;//�Ƿ���ѯ
	private String mySelf;//��ס�Լ��İ���
	private ContentResolver resolver;
	private LockAppDBObserver observer;
	
	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {
		resolver = getContentResolver();
		observer = new LockAppDBObserver(new Handler());
		resolver.registerContentObserver(Uri.parse("content://com.itheima.mobile.change"),true,observer);
		dao = new LockedAppDao(this);
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		intent = new Intent(this, EnterPwdActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// �����п���activity
														// ָ�����Լ���������ջ��Ϣ
		//ע��㲥
		receiver = new WatchDogReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.itheima.mobileguard.stop");
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(receiver, filter);
		
		//��ѯ���ݿ�
		packageNames = dao.findAll();
		/**
		 * ���Ź������߼� ��ѯ����ջ
		 */
		startWatchDog();

		super.onCreate();
	}

	private void startWatchDog() {
		new Thread() {
			public void run() {
				/*
				 * ����activityManager��ȡ��ǰϵͳ��������Ϊվ��Ϣ,Ϊʲôֻȡһ������Ϊֻ��Ҫ��ȡ����������ջ����Լ�ڴ�
				 * ��toActivity��ȡ����������ջջ����activity��Ȼ���ȡ�������
				 * �жϸ�activity����Application�Ƿ���˳����� Return a list of the tasks
				 * that are currently running, with the most recent being first
				 * and older ones after in order.���ʹ�õ�����ջ��������
				 * 
				 */
				flag = true;
				while (flag) {
						packageName = am.getRunningTasks(1).get(0).topActivity
								.getPackageName();
						if (packageNames.contains(packageName)) {// ��Ҫ����
							if(!( packageName.equals(tempStopProtectPackageName) || packageName.equals(mySelf))){
								//������������Ľ��� ���ڱ��������ĸ�����
								intent.putExtra("packname", packageName);
								WatchDagService.this.startActivity(intent);
							}
						}
						try {
							Thread.sleep(100);//����Ҫ�ÿ��Ź���Ϣһ�� ����cpuռ���� �ᱬ��
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		}.start();
	}
	
	/**
	 * �������� �ͷ���Դ
	 */
	public void onDestroy() {
		unregisterReceiver(receiver);
		receiver = null;//���������Դ
		flag = false;
		resolver.unregisterContentObserver(observer);
		observer = null;
		super.onDestroy();
	}

	
	/**
	 * �㲥������ ���ڴ�����Ϣ �� ʡ���Ż�
	 * @author Administrator
	 *
	 */
	private class WatchDogReceiver extends BroadcastReceiver{
		public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if("com.itheima.mobileguard.stop".equals(action)){
					String name = intent.getStringExtra("packname");
					if("com.itheima.mobileguard".equals(name)){
						mySelf = getPackageName();
					}else{
						tempStopProtectPackageName = name;
					}
				}else if(Intent.ACTION_SCREEN_OFF.equals(action)){
					//�ر�����Ļ ����ֹͣ��ѯ�� ������֮ǰ���õ���ʱֹͣ��������Ϊ��
					tempStopProtectPackageName = null;
					mySelf = null;
					flag = false;
				}else if(Intent.ACTION_SCREEN_ON.equals(action)){//�ִ�����Ļ �ֿ��Կ�ʼ��ѯ��
					startWatchDog();
				}
		}
	}
	
	/**
	 * ���ݹ۲��� ���� ���������ݿ�ı仯 ����б仯�͸��¼����е�����
	 * @author Administrator
	 *
	 */
	private class LockAppDBObserver extends ContentObserver{

		public LockAppDBObserver(Handler handler) {
			super(handler);
		}
		//���ݿⷢ�ͱ仯 ���¼���
		public void onChange(boolean selfChange) {
			packageNames = dao.findAll();
			super.onChange(selfChange);
		}
		
	}
}
