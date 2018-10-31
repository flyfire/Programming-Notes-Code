package com.itheima.mobileguard.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SystemInfoUtils {
	/**
	 * �ж�һ�������Ƿ�������״̬
	 * @param context ������
	 * @return
	 */
	public static boolean isServiceRunning(Context context,String className){
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> infos = am.getRunningServices(200);
		for(RunningServiceInfo info:infos){
			String serviceClassName = info.service.getClassName();
			if(className.equals(serviceClassName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ��ȡ�ܵ��ڴ�
	 * @param context
	 * @return
	 */
	public static long getTotalMemory(Context context){
		try {
			FileInputStream fis = new FileInputStream("/proc/meminfo");
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String info = br.readLine();
			br.close();
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ;i<info.length();i++){
				if(info.charAt(i) <= '9' && info.charAt(i) >= '0'){
					sb.append(info.charAt(i));
				}
			}
			long total = Long.parseLong(sb.toString()) * 1024;
			return total;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0l;
	}
	
	/**
	 * ��ȡϵͳ��ǰʣ���ڴ�
	 * @param context
	 * @return
	 */
	public static long getAvailableMemory(Context context){
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(outInfo);
		//System.out.println(outInfo.totalMem); ��׿4.0��֧�ֵ�API
		return outInfo.availMem;
	}
	/**
	 * ��ȡϵͳ�������еĽ�������
	 * @param context
	 * @return
	 */
	public static int getRunningProcessCount(Context context){
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		int count = am.getRunningAppProcesses().size();
		return count;
	}
}
