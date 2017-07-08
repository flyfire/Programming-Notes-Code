package com.itheima.mobileguard.domain;

import android.graphics.drawable.Drawable;

public class ProcessInfo {

		private String packageName;//����
		private String appName;//����
		private boolean isUserApp;//�Ƿ����û�����
		private boolean isChecked;//�Ƿ񱻹�ѡ
		private Drawable icon;//ͼ��
		private long occupyMemory;//ռ�õ��ڴ�
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		public String getAppName() {
			return appName;
		}
		public void setAppName(String appName) {
			this.appName = appName;
		}
		public boolean isUserApp() {
			return isUserApp;
		}
		public void setUserApp(boolean isUserApp) {
			this.isUserApp = isUserApp;
		}
		public boolean isChecked() {
			return isChecked;
		}
		public void setChecked(boolean isChecked) {
			this.isChecked = isChecked;
		}
		public Drawable getIcon() {
			return icon;
		}
		public void setIcon(Drawable icon) {
			this.icon = icon;
		}
		public long getOccupyMemory() {
			return occupyMemory;
		}
		public void setOccupyMemory(long occupyMemory) {
			this.occupyMemory = occupyMemory;
		}
		@Override
		public String toString() {
			return "ProcessInfo [packageName=" + packageName + ", appName="
					+ appName + ", isUserApp=" + isUserApp + ", isChecked="
					+ isChecked + ", occupyMemory=" + occupyMemory + "]";
		}
		
	
}
