package com.itheima.mobileguard.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.R.layout;
import com.itheima.mobileguard.db.dao.NumberAddressDao;

public class ShowLoacationService extends Service {

	protected static final String TAG = "ShowLoacationService";
	private int[] itemsImageId = {R.drawable.call_locate_white,R.drawable.call_locate_orange,R.drawable.call_locate_blue
			,R.drawable.call_locate_gray,R.drawable.call_locate_green};
	private SharedPreferences sp;
	private TelephonyManager tm;// ͨ������
	private MyPhoneStateListener listener;// ������
	private OutCallingReceiver receiver;// ����绰������
	private View view;
	// �������������
	private WindowManager windowManager;
	// ���ڹ��������view��Ҫ�Ĳ��ֵĲ���
	private WindowManager.LayoutParams params;

	public IBinder onBind(Intent intent) {

		return null;
	}

	/**
	 * ��������ע��㲥
	 */
	@Override
	public void onCreate() {
		receiver = new OutCallingReceiver();
		// �õ�ͨ������
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		// �õ����������
		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		// ����ע��㲥
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
		filter.setPriority(Integer.MAX_VALUE);
		registerReceiver(receiver, filter);
		listener = new MyPhoneStateListener();
		// �����绰��ͨ��״̬
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

		super.onCreate();
	}

	/**
	 * ��������ĵ绰
	 * 
	 * @author Administrator
	 * 
	 */
	private class OutCallingReceiver extends BroadcastReceiver {

		public void onReceive(Context context, Intent intent) {
			String number = getResultData();
			// Toast.makeText(context,
			// NumberAddressDao.query(number, context),
			// Toast.LENGTH_LONG).show();
			showCustomToast(number);
		}

	}

	/**
	 * ��ʾ�Զ�����˾
	 * 
	 * @param msg
	 */
	private void showCustomToast(String msg) {
		view = View.inflate(this, R.layout.toast_showaddress, null);
		view.setBackgroundResource(itemsImageId[sp.getInt("item", 0)]);
		TextView tv = (TextView) view.findViewById(R.id.tv_toast_address);
		tv.setText(NumberAddressDao.query(msg, this));
		params = new LayoutParams();
//		��ȡ��˾��λ�� Ϊ�˷������ ������˾�Ķ��뷽���� ����Ļ�����Ͻ�(Ĭ�ϵĶ��뷽ʽ����Ļ���м�)
		params.gravity = Gravity.LEFT + Gravity.TOP;
		
		params.x = sp.getInt("startX", windowManager.getDefaultDisplay().getWidth()>>1);//��˾��0 ����Ļ���м�
		params.y = sp.getInt("startY",  windowManager.getDefaultDisplay().getHeight()>>1);
		
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE    ��Ҫ������
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		params.format = PixelFormat.TRANSLUCENT;
		// params.windowAnimations =
		// com.android.internal.R.style.Animation_Toast; ���䶯��
		// params.type = WindowManager.LayoutParams.TYPE_TOAST; ��˾������������Ӧ�����¼�
		/**
		 * Window type: priority phone UI, which needs to be displayed even if
		 * the keyguard is active. These windows must not take input focus, or
		 * they will interfere with the keyguard. In multiuser systems shows on
		 * all users' windows. 
		 * ���ͣ����ȵ绰���û����棬����Ҫ��ʾ��ʹ
		 * �������ǻ�ġ���Щ�������벻������
		 * ���㣬�������ǻ������������
		 * �ڶ��û�ϵͳ����ʾ�����û���Windows��
		 */
//		��ҪȨ�� android.permission.SYSTEM_ALERT_WINDOW
		params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;// ֻ���������ִ������Ͳ��ܱ�ʹ��
		// �����Ķ���ϵͳ�õ������ûᱨ��
		
		windowManager.addView(view, params);

		view.setOnTouchListener(new OnTouchListener() {
			int startX = 0;
			int startY = 0;
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:// ��ָ����
					startX = (int) event.getRawX();// �ֻ����¼�סx y λ��
					startY = (int) event.getRawY();
					Log.i(TAG, "��ʼλ�� :x" + startX + ":Y" + startY);
					break;
				case MotionEvent.ACTION_MOVE:// ��ָ�ƶ�
					System.out.println("AAAAAAAAAAAAAAAAAAAAA"+view);
					System.out.println("AAAAAAAAAAAAAAAAAAAAA"+v);
					// �ƶ����λ��
					int newX = (int) event.getRawX();
					int newY = (int) event.getRawY();
					Log.i(TAG, "�µ�λ�� :x" + newX + ":Y" + newY);
					// ������ƶ��ľ���
					int dx = newX - startX;
					int dy = newY - startY;
					Log.i(TAG, "�ƶ��ľ���λ�� :x" + dx + ":Y" + dy);
					// ������˾��λ��  ��ǰλ��+ �ƶ��ľ��� = �µ�λ��
					params.x += dx;
					params.y += dy;
//					��ֹ��˾��λ�ó�����Ļ
					if(params.x <=0){
						params.x = 0;
					}
					if(params.y <=0){
						params.y =0;
					}
					if(params.x >= windowManager.getDefaultDisplay().getWidth()-view.getWidth()){
						params.x = windowManager.getDefaultDisplay().getWidth()-view.getWidth();
					}
					if(params.y >= windowManager.getDefaultDisplay().getHeight() - view.getHeight()){
						params.y = windowManager.getDefaultDisplay().getHeight() - view.getHeight();
					}
					
					 windowManager.updateViewLayout(view, params);
					 //������˾���ڵ�λ��
					 startX = (int) event.getRawX();// �ֻ����¼�סx y λ��
					 startY = (int) event.getRawY();
					 Log.i(TAG, "�µ���ʼλ�� :x" + startX + ":Y" + startY);
					break;
				case MotionEvent.ACTION_UP:// �ֻ��뿪
//					��ָ�뿪��ס��˾��λ�� 
					sp.edit().putInt("startX", startX).putInt("startY", startY).commit();
					break;

				default:
					break;
				}
				return false;
			}
		});
	}

	/**
	 * �绰ͨ��״̬������
	 */
	public class MyPhoneStateListener extends PhoneStateListener {
		// �绰״̬�仯�����
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:// ����״̬
				if (view != null) {
					windowManager.removeView(view);
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:// ��ͨ״̬
				break;
			case TelephonyManager.CALL_STATE_RINGING:// ����״̬
				showCustomToast(incomingNumber);
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
		receiver = null;
		super.onDestroy();
	}
}
