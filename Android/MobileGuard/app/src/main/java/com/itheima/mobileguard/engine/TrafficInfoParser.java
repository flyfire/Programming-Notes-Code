package com.itheima.mobileguard.engine;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itheima.mobileguard.domain.TrafficInfo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.net.TrafficStats;

/**
 * ���ڻ�ȡ������������Ϣ
 * @author Administrator
 *
 */
public class TrafficInfoParser {

		//��ȡ���Կ���������APP
	public static List<TrafficInfo> findLauncherTrafficInfo(Context context){
		Intent intent = new Intent();
		PackageManager pm = context.getPackageManager();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		//resolverInfo :�൱��һ��IntentFilter ��¼����Ϣ
		List<TrafficInfo> traffiicinfos = new ArrayList<TrafficInfo>();
		List<ResolveInfo> infos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);//ֻƥ��Ĭ�ϵ�
		TrafficInfo info = null;
		for(ResolveInfo resolverInfo : infos){
			info = new TrafficInfo();
			info.setAppName(resolverInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
			info.setIcon(resolverInfo.activityInfo.applicationInfo.loadIcon(pm));
			info.setPackageName(resolverInfo.activityInfo.packageName);
			info.setUid(resolverInfo.activityInfo.applicationInfo.uid);
			traffiicinfos.add(info);
			info = null;
		}
		return traffiicinfos;
	}
	
	/**
	 * ��ȡ���п���������app  ������internetȨ�޵�APP
	 */
	public static List<TrafficInfo> findInternetApp(Context context){
		PackageManager pm = context.getPackageManager();
		//��ȡһ��PackageInfo�ļ���  ��ѯ����û�б�ж�صĺ� ����Ҫ��ȡ��Ȩ��
		List<PackageInfo>  packageInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_PERMISSIONS);
		TrafficInfo trafficInfo = null;
		List<TrafficInfo> traffiicinfos = new ArrayList<TrafficInfo>();
		for(PackageInfo info: packageInfos){
			String[]  ps = info.requestedPermissions;
			if(ps != null)//�е�Ӧ�ÿ���û��Ȩ��
			for(String permission: ps){
				if(permission.equals(Manifest.permission.INTERNET)){
					trafficInfo = new TrafficInfo();
					trafficInfo.setAppName(info.applicationInfo.loadLabel(pm).toString());
					trafficInfo.setPackageName(info.packageName);
					trafficInfo.setIcon(info.applicationInfo.loadIcon(pm));
					trafficInfo.setUid(info.applicationInfo.uid);
					trafficInfo.setDownTraffic(TrafficStats.getUidRxBytes(info.applicationInfo.uid));
					trafficInfo.setUpTraffic(TrafficStats.getUidTxBytes(info.applicationInfo.uid));
					traffiicinfos.add(trafficInfo);
					trafficInfo = null;
			}
			}
		}
		return traffiicinfos;
	}
}
