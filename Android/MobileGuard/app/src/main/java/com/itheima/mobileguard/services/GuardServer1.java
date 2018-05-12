package com.itheima.mobileguard.services;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.activities.HomeActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class GuardServer1 extends Service {

	private NotificationManager nm;
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	@Override
	public void onCreate() {
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notify = new Notification(R.drawable.ic_launcher,"С��ʿ�������İ�ȫ",System.currentTimeMillis());
		Intent contentIntent = new Intent(this,HomeActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, contentIntent,0 );
		notify.setLatestEventInfo(this, "�ػ�ģʽ", "С��ʿΪ��վ��", pendingIntent);
		startForeground(100, notify);
		super.onCreate();
	}
	
	public void onDestroy() {
		Intent intent = new Intent(this,GuardServer2.class);
		startService(intent);
		super.onDestroy();
	}
}
