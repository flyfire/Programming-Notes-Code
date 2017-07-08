package com.itheima.mobileguard.engine;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.domain.ProcessInfo;

public class ProcessInfoParser {

	
	
	
	/**
	 * ��ȡϵͳ�������еĽ��̵���Ϣ
	 * @param context
	 * @return
	 */
	public static List<ProcessInfo> getRunningProcess(Context context){
		//��ȡ���̹�����
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		PackageManager pm = context.getPackageManager();
		List<RunningAppProcessInfo> runningAppProcessInfos =  am.getRunningAppProcesses();
		List<ProcessInfo> infos = new ArrayList<ProcessInfo>();//���� �洢javabean
		ProcessInfo info = null;
		for(RunningAppProcessInfo processInfo : runningAppProcessInfos){
				info = new ProcessInfo();
				String packageName = processInfo.processName;//�õ�����
				info.setPackageName(packageName);//���ð���
				try {
					//�õ�Ӧ���� �еĳ���û��appName ����ָΪ����
					String appName = pm.getApplicationLabel(pm.getApplicationInfo(packageName, 0)).toString();
					info.setAppName(appName);//����Ӧ����
				} catch (NameNotFoundException e1) {
					e1.printStackTrace();
					info.setAppName(packageName);
				}
				try {
					//�еĳ���û��ͼ�������Ϊ������ͼ��
					Drawable icon = pm.getApplicationIcon(packageName);
					info.setIcon(icon); //����ͼ��
				} catch (NameNotFoundException e) {
					e.printStackTrace();//����ΪĬ��ͼƬ
					info.setIcon(context.getResources().getDrawable(R.drawable.default_ic_launcher));
				}
				
				//��ȡһ������ռ�õ��ڴ�
				MemoryInfo[] memoryInfos = am.getProcessMemoryInfo(new int[]{processInfo.pid});
				info.setOccupyMemory(memoryInfos[0].getTotalPrivateDirty()*1024);//����ռ���ڴ�
				//�Ƿ����û�����
				try {
					if((pm.getApplicationInfo(packageName, 0).flags & ApplicationInfo.FLAG_SYSTEM) != 0){
						info.setUserApp(false);
					}else{
						info.setUserApp(true);
					}
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}
				infos.add(info);
				info = null;
		}
		return infos;
	}
}
