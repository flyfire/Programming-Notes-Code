package com.itheima.mobileguard.services;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;
import com.itheima.mobileguard.db.dao.BlackNumberDao;

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
import android.telephony.PhoneStateListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;

public class CallsafeService extends Service {

	private TelephonyManager tm;// ͨ������
	private CallSafeReceiver receiver;// ���Ž�����
	private BlackNumberDao dao;
	private MyPhoneStateListener listener;// ������
	private CallsContentObserver observer;// ���ݹ۲���
	private ContentResolver resolver;// ���ݽ�����

	public IBinder onBind(Intent intent) {
		return null;
	}

	/**
	 * ��������ע��㲥
	 */
	@Override
	public void onCreate() {
		receiver = new CallSafeReceiver();
		dao = new BlackNumberDao(this);
		// �õ�ͨ������
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		// ����ע��㲥
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(Integer.MAX_VALUE);
		registerReceiver(receiver, filter);
		super.onCreate();

		listener = new MyPhoneStateListener();
		// �����绰��ͨ��״̬
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}

	/**
	 * �绰ͨ��״̬������
	 */
	public class MyPhoneStateListener extends PhoneStateListener {
		// �绰״̬�仯�����
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:// ����״̬

				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:// ��ͨ״̬

				break;
			case TelephonyManager.CALL_STATE_RINGING:// ����״̬ ���ʱ���жϵ绰�Ƿ��Ǻ����� ��
														// ����ģʽ
				String mode = dao.findModeByNumber(incomingNumber);
				if ("1".equals(mode) || "3".equals(mode)) {// ȫ������״̬�͵绰��������״̬
					// �Ҷϵ绰�ķ������ȸ�������� ��Ҫ�÷���ķ����� �����������
					System.out.println("�绰���� ");
					endCall(incomingNumber);
				}
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}
	}

	/**
	 * �ͷŹ㲥
	 */
	@Override
	public void onDestroy() {
		tm.listen(listener, PhoneStateListener.LISTEN_NONE);// ȡ������
		listener = null;
		unregisterReceiver(receiver);// ȡ���㲥
		if (resolver != null && observer != null) {
			resolver.unregisterContentObserver(observer);// �ͷ���Դ
			observer = null;
		}
		receiver = null;
		super.onDestroy();
	}

	/**
	 * �Ҷϵ绰
	 */
	public void endCall(String incomingNumber) {
		try {
			// android.permission.CALL_PHONE ��ҪȨ��
			Class clazz = Class.forName("android.os.ServiceManager");
			Method method = clazz.getDeclaredMethod("getService", String.class);
			IBinder ibinder = (IBinder) method.invoke(null, TELEPHONY_SERVICE);// ��ȡ�绰����
			ITelephony iTelephony = ITelephony.Stub.asInterface(ibinder);// Զ�̷���
			// Ibinder�� �����ṩ�Ľӿ�
			iTelephony.endCall();// �Ҷϵ绰
			// ��Ȼ�����˺��������� ���ǻ���ͨ����¼������δ�Ӽ�¼ ������Ҫɾ��ͨ����¼
			// android.permission.CALL_PHONE
			// ɾ��������ͨ����¼
			resolver = getContentResolver();
			Uri uri = Uri.parse("content://call_log/calls");// ��ѯȫ���ļ�¼
			observer = new CallsContentObserver(new Handler(), incomingNumber);
			resolver.registerContentObserver(uri, true, observer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class CallsContentObserver extends ContentObserver {
		private String incomingNumber;

		public CallsContentObserver(Handler handler, String incomingNumber) {
			super(handler);
			this.incomingNumber = incomingNumber;
		}

		@Override
		public void onChange(boolean selfChange) {
			// ֻ�����ݷ��ͱ仯�Ż�ִ��
			resolver.delete(Uri.parse("content://call_log/calls"), "number=?",
					new String[] { incomingNumber });
			super.onChange(selfChange);
		}

	}

	/**
	 * ͨ����ȫ�㲥������
	 * 
	 * @author Administrator
	 * 
	 */
	private class CallSafeReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			// �õ�����
			Object[] objs = (Object[]) intent.getExtras().get("pdus");
			for (Object obj : objs) {
				SmsMessage sm = SmsMessage.createFromPdu((byte[]) obj);
				String number = sm.getOriginatingAddress();// ��ȡ����
				// �����ݿ�
				String mode = dao.findModeByNumber(number);
				if ("1".equals(mode) || "2".equals(mode)) {// ȫ������״̬�Ͷ�������״̬
					abortBroadcast();// ���ض���
				}
			}
		}
	}
}
