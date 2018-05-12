package com.itheima.mobileguard.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * ��ȡλ���ַ������û�����˽ ��Ҫ���Ȩ��
 * @author Administrator
 *
 */
public class LoactionService extends Service {
	private LocationManager lm;
	private SharedPreferences sp ;
	private MyLocationListener listener;
	@Override
	public void onCreate() {
		// ��ȡϵͳλ�÷���
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		// criteria ��׼ true ʹ�ÿ��õ�λ���ṩ��
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);// �ȷ��λ��
		criteria.setCostAllowed(true);// ����ϵͳ����
		String name = lm.getBestProvider(criteria, true);
		System.out.println("��õ�λ�÷���:" + name);
		// ��λ�������� ʱ��ͼ�����ϵͳĬ�ϼ��� λ�ü�����
		listener = new MyLocationListener();
		lm.requestLocationUpdates(name, 0, 0, listener);
		super.onCreate();
	}

	
	/**
	 * λ�ü�����
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyLocationListener implements LocationListener {
		/**
		 * λ�øı� ������
		 */
		public void onLocationChanged(Location location) {
				float accuracy = location.getAccuracy();//��ȡ��ȷ��
				double latitute = location.getLatitude();//��ȡά��
				double longitude = location.getLongitude();//��ȡ����
				float speed = location.getSpeed();//��ȡ����
				double altitude = location.getAltitude();//��ȡ����
//				��֯����
				StringBuilder  sb = new StringBuilder();
				System.out.println(sb.toString());
				sb.append("accuracy:"+accuracy+"\nlatitute:"+latitute
						+"\nlongitude"+longitude+"\nspeed:"+speed);
				//���Ͷ��Ÿ���ȫ����
				SmsManager sm = SmsManager.getDefault();
				sm.sendTextMessage(sp.getString("safeContact", ""), null, sb.toString(), null, null);
				//����ֻ����һ�� ��Ȼ�ᷢ��
				//�����Լ�
				stopSelf();
		}
		/**
		 * Called when the provider status changes. This method is called when a
		 * provider is unable to fetch a location or if the provider has
		 * recently become available after a period of unavailability.
		 * ��provider��״̬�ı䣬������������ã�
		 */
		public void onStatusChanged(String provider, int status, Bundle extras) {
	
		}

		public void onProviderEnabled(String provider) {

		}

		public void onProviderDisabled(String provider) {

		}
	}
	/**
	 * �ͷ���Դ
	 */
	@Override
	public void onDestroy() {
		lm.removeUpdates(listener);
		listener = null;
		super.onDestroy();
	}
	
	public IBinder onBind(Intent intent) {
		return null;
	}

}
