package com.itheima.mobileguard.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * ֻҪ�ֻ���SIM�仯�� �ͻ����� ��ʱ�ж�ԭ�е�sim�������к��Ƿ�Ϳ������simһ������һ����˵��sim
 * ������� ����Ӧ�÷��Ͷ��Ÿ��û����õİ�ȫ����
 * @author Administrator
 *
 */
public class BootCompletedReceiver extends BroadcastReceiver {
	private SharedPreferences sp;
	private TelephonyManager tm;
	public void onReceive(Context context, Intent intent) {
		//��ȡƫ������
			sp = context.getSharedPreferences("lostFindConfig", Context.MODE_PRIVATE);
			tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			//���û��Ƿ������ֻ�����
			boolean isOpen = sp.getBoolean("protecting", false);
			if(isOpen){
				//˵���û��Ѿ������˷��� 
				//�û�֮ǰ����sim�����к� �� ���ڵ����кűȽ� �����һ�� �ͷ��;���
				
				if(!sp.getString("sim", "").equals(tm.getSimSerialNumber()+"S")){
					//���Ͷ���
					//��ȡ֮ǰ���õİ�ȫ����
					String number = sp.getString("safeContact", "");
					SmsManager sm = SmsManager.getDefault();
					sm.sendTextMessage(number, null, "SIM card is changed !!!", null, null);
				}
			}
	}
}
