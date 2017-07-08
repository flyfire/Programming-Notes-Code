package com.itheima.mobileguard.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.TrafficStats;

import com.itheima.mobileguard.domain.AppInfo;

public class AppInfoParser {

		public static List<AppInfo> getAllAppinfo(Context context){
			PackageManager pm = context.getPackageManager();
			ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			
			List<PackageInfo> infos = pm.getInstalledPackages(0);
			List<AppInfo> appInfos = new ArrayList<AppInfo>(); 
			
			AppInfo appInfo = null;
			for(PackageInfo pi:infos){
				
			
				appInfo = new AppInfo();
				appInfo.setPackageName(pi.packageName);//����
				/*
				 * applicationInfo.sourceDir ��Ӧ�ó���İ�װ·��
				 */
				appInfo.setAppSize(new File(pi.applicationInfo.sourceDir).length());
				appInfo.setApkPath(pi.applicationInfo.sourceDir);
				appInfo.setAppIcon(pi.applicationInfo.loadIcon(pm));//Ӧ�õ�ͼ��
				appInfo.setAppName(pi.applicationInfo.loadLabel(pm).toString());//Ӧ����
				
			
				/*
				 * ��������ӳ�䣬��һ��������������������Ϣ�����Դ��ݶ����Ϣ
				 * ��ֹ��Ϊ��Ϣ�������ɴ����߳�
				 * 
				 */
				if((ApplicationInfo.FLAG_EXTERNAL_STORAGE & pi.applicationInfo.flags)	!= 0){
					appInfo.setInRom(false);//��SD����
				}else{
					appInfo.setInRom(true);//���ֻ��ڴ�
				}
				if((ApplicationInfo.FLAG_SYSTEM & pi.applicationInfo.flags) != 0){
					appInfo.setUserApp(false);//ϵͳӦ��
				}else{
					appInfo.setUserApp(true);//�û�Ӧ��
				}
				appInfos.add(appInfo);
				appInfo = null;
			}
			return appInfos;
		}
}
