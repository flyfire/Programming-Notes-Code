package com.itheima.mobileguard.services;

import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Formatter;
import android.widget.RemoteViews;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.receivers.MyWidgetReceiver;
import com.itheima.mobileguard.utils.SystemInfoUtils;

public class UpdataWidgetService extends Service {
	
	/**
	 * ��ȡwidget��״̬������
	 */
	private AppWidgetManager widgetManager;
	private Timer timer;
	private TimerTask task;
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	/**
	 * ���񱻴���
	 */
	public void onCreate() {
		widgetManager = AppWidgetManager.getInstance(this);//��ȡwidget������
		
		final ComponentName provider = new ComponentName(this,MyWidgetReceiver.class);
	//�Ѱ����� id��������Ӧ��
		final RemoteViews views = new RemoteViews(getPackageName(),R.layout.process_widget);
		timer = new Timer();
		task = new TimerTask() {
			public void run() {
				/**
				 * ContentViews��
				 * Զ��View��������Ϣ ��Զ�̵�����Ӧ�ø���Views��������Ϣ��View��new����
				 *  ������һ������ ����ui�� ����ui
				 */
				views.setTextViewText(R.id.process_count, "�������еĽ���"+SystemInfoUtils.getRunningProcessCount(UpdataWidgetService.this));
				views.setTextViewText(R.id.process_memory, "�����ڴ�"+Formatter.formatFileSize(UpdataWidgetService.this, SystemInfoUtils.getAvailableMemory(UpdataWidgetService.this)));
				Intent intent = new Intent();
				intent.setAction("com.itheima.clearall");
				PendingIntent   pendingIntent = PendingIntent.getBroadcast(UpdataWidgetService.this, 0, intent, 0);
				views.setOnClickPendingIntent(R.id.btn_clear, pendingIntent);
				widgetManager.updateAppWidget(provider, views);
			}
		};
		timer.schedule(task, 0, 5000);
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		if(timer != null && task != null){
			timer.cancel();
			task.cancel();
			timer = null;
			task = null;
		}
		super.onDestroy();
	}
	
}




