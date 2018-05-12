package com.itheima.mobileguard.domain;

import android.graphics.drawable.Drawable;

public class AppInfo {
	
	

	/**
	 * ��װ·��
	 */
	private String apkPath;
	
	/**
	 * Ӧ�ó����ͼ��
	 */
	
	private Drawable appIcon;
	/**
	 * Ӧ�ó�������
	 */
	private String appName ;
	/**
	 * Ӧ�ó���װ��λ�ã�true�ֻ��ڴ� ��false�ⲿ�洢��
	 */
	private boolean isInRom;
	/**
	 * Ӧ�ó���Ĵ�С
	 */
	private long appSize;
	/**
	 * �Ƿ����û�����  true �û����� false ϵͳ����
	 */
	private boolean isUserApp;
	/**
	 * Ӧ�ó���İ���
	 */
	private String packageName;
	public Drawable getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public boolean isInRom() {
		return isInRom;
	}
	public void setInRom(boolean isInRom) {
		this.isInRom = isInRom;
	}
	public long getAppSize() {
		return appSize;
	}
	public void setAppSize(long appSize) {
		this.appSize = appSize;
	}
	public boolean isUserApp() {
		return isUserApp;
	}
	public void setUserApp(boolean isUserApp) {
		this.isUserApp = isUserApp;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getApkPath() {
		return apkPath;
	}
	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}
	@Override
	public String toString() {
		return "AppInfo [appName=" + appName + ", isInRom=" + isInRom
				+ ", appSize=" + appSize + ", isUserApp=" + isUserApp
				+ ", packageName=" + packageName + "]";
	}
	 
	
}
