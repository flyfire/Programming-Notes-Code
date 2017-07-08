package com.itheima.mobileguard.receivers;

import com.itheima.mobileguard.services.UpdataWidgetService;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/**
 * ����Ĺ㲥������
 * @author Administrator
 *
 */
public class MyWidgetReceiver extends AppWidgetProvider {
	
	/**
	 * �ȵ�������������ݺ��� �в��ɿ� ���Զ���һ����������ʱ����
	 *��������ǵ�һ�δ���widget�ǵ��õ�
	 */
	public void onEnabled(Context context) {
		//���������ڸ���
		Intent intent  = new Intent(context,UpdataWidgetService.class);
		context.startService(intent);
		super.onEnabled(context);
	}
	
	/**
	 * �����һ��widget���Ƴ�ʱ���� 
	 * ���رշ���
	 */
	public void onDisabled(Context context) {
		Intent intent  = new Intent(context,UpdataWidgetService.class);
		context.stopService(intent);
		super.onDisabled(context);
	}
}
