package com.itheima.mobileguard.receivers;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.sax.StartElementListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.activities.TackPictureActivity;

/**
 * �������ܵ��Ķ��� ���������ָ���ִ�ж�Ӧ�Ĳ���
 * 
 * @author Administrator
 * 
 */
public class SmsReceiver extends BroadcastReceiver {
	private SharedPreferences sp;
	private ComponentName cn;

	public void onReceive(Context context, Intent intent) {
		// �ж��û��Ƿ����˷�������
		// ��ȡƫ������
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		// ���û��Ƿ������ֻ�����
		boolean isOpen = sp.getBoolean("protecting", false);
		if (isOpen) {

			cn = new ComponentName(context, MyDeviceAdmin.class);
			// ��ȡ��������
			/**
			 * �����ӿڣ����ڹ���ǿ��ִ�е��豸�ϵ����ߡ�����Ĵ�����ͻ����빫���û�Ŀǰ�Ѿ�������DeviceAdminReceiver��
			 * 
			 * ������Աָ�� �йع������ߣ��Ի�ȡ�豸adminstration�ĸ�����Ϣ�����Ķ��豸��������Աָ�ϡ�
			 */
			DevicePolicyManager dpm = (DevicePolicyManager) context
					.getSystemService(Context.DEVICE_POLICY_SERVICE);
			Object[] objs = (Object[]) intent.getExtras().get("pdus");
			// ������������
			for (Object obj : objs) {
				SmsMessage sm = SmsMessage.createFromPdu((byte[]) obj);
				String smsBody = sm.getMessageBody();// ��ȡ��������
				// �ж������Ƿ�������ָ��
				if ("#*location*#".equals(smsBody)) {
					// ����λ�� ���ڻ�ȡλ�ÿ�����һ���ȽϺ�ʱ�Ĳ��� ���ܷ��ڹ㲥����������
					// ��Ϊ�㲥�����ߵ��������ںܶ� Ҳ���ܷ��������߳����� ������Ҫ����һ������
					// ȥ��ȡλ�� ���������ֻ�����
					Intent i = new Intent(
							context,
							com.itheima.mobileguard.services.LoactionService.class);
					context.startService(i);
					System.out.println("lai le");
					abortBroadcast();
				} else if ("#*alarm*#".equals(smsBody)) {
					// ���ű�������
					MediaPlayer play = MediaPlayer.create(context, R.raw.ylzs);
					// ��������
					play.setVolume(1.0f, 1.0f);
					play.start();
					abortBroadcast();
				} else if ("#*wipedata*#".equals(smsBody)) {
					// TODOԶ��ɾ���ֻ����� ����洢��������
					if (dpm.isAdminActive(cn)) {
						dpm.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);
					}
					abortBroadcast();
				} else if ("#*lockscreen*#".equals(smsBody)) {
					// TODO Զ������
					if (dpm.isAdminActive(cn)) {
						dpm.resetPassword("123", 0);// ������������
						dpm.lockNow();// ����
					}
					abortBroadcast();// ���ض���
				}else if("#*tackpicture*#".equals(smsBody)){
					//Զ�������ϴ�
					Intent i = new Intent(context,TackPictureActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					abortBroadcast();
					context.startActivity(i);
				}
			}
		}
	}
}
