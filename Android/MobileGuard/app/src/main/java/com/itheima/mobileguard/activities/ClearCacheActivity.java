package com.itheima.mobileguard.activities;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.mobileguard.R;

public class ClearCacheActivity extends Activity {
	protected static final int BEGIN = 1;
	protected static final int SCANING = 2;
	protected static final int FINISH =3;
	private LinearLayout ll_clear_loading;
	private TextView tv_clearing;
	private LinearLayout ll_clear_details;
	private PackageManager pm;
	private PackageSizeInfo packageSizeInfo;
	private List<ScanInfo> scanInfos;
	private Button bt_clear;
	private Method method;
	private boolean flag = false;
	private ImageView iv_logo;
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.acitivity_clear_cache);
			pm = getPackageManager();
			scanInfos = new ArrayList<ScanInfo>();
			ll_clear_loading = (LinearLayout) findViewById(R.id.ll_clear_loading);
			ll_clear_details = (LinearLayout) findViewById(R.id.ll_clear_details);
			tv_clearing = (TextView) findViewById(R.id.tv_clearing);
			bt_clear = (Button) findViewById(R.id.bt_clear);
			iv_logo = (ImageView) findViewById(R.id.iv_logo);
			
			try {
				method = PackageManager.class.getDeclaredMethod("getPackageSizeInfo", String.class,IPackageStatsObserver.class);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
			protected void onDestroy() {
				flag = true;
				super.onDestroy();
			}
		
		Handler handler  = new Handler(){
			public void handleMessage(android.os.Message msg) {
					switch (msg.what) {
					case BEGIN:
						//ʲô��������
						break;
					case SCANING:
						//��ʱ���½���
						PackageInfo info = (PackageInfo) msg.obj;
						tv_clearing .setText("����ɨ��"+info.applicationInfo.loadLabel(pm).toString());
						break;
					case FINISH:
						//ɨ�����
						ll_clear_loading.setVisibility(View.INVISIBLE);
						if(scanInfos.size() > 0){
							View view = null;
							for(ScanInfo scanInfo : scanInfos){
								view = View.inflate(getApplicationContext(), R.layout.item_cache_info, null);
								ImageView iv = (ImageView) view.findViewById(R.id.iv_cache_icon);
								TextView tv_appName = (TextView) view.findViewById(R.id.tv_cache_appname);
								TextView tv_cache_size = (TextView) view.findViewById(R.id.tv_cache_size);
								iv.setImageDrawable(scanInfo.icon);
								tv_appName.setText(scanInfo.appName);
								tv_cache_size.setText(Formatter.formatFileSize(getApplicationContext(), scanInfo.cacheSize));
								ll_clear_details.addView(view);
							}
							bt_clear.setClickable(true);
						}else{
							TextView tv = new TextView(getApplicationContext());
							tv.setText("��ϲ���������ֻ��ܸɾ�,û�м�鵽����");
							tv.setTextScaleX(30);
							Toast.makeText(getApplicationContext(), "��ϲ���������ֻ��ܸɾ�,û�м�鵽����", Toast.LENGTH_SHORT).show();
						}
						break;
					}
			}
		};
		/**
		 * ��ʼɨ��
		 * @param view
		 */
		public void startScan(View view){
			iv_logo.setVisibility(View.INVISIBLE);
			ll_clear_loading.setVisibility(View.VISIBLE);
			scanInfos.clear();
			ll_clear_details.removeAllViews();
			new Thread(){	
				public void run(){
					try {
						//��ʼɨ��
						Message msg =Message.obtain();
						msg.what  = BEGIN;
						handler.sendMessage(msg);
						//���÷��� ��ȡ����Ĵ�С
						List<PackageInfo> infos = pm.getInstalledPackages(0);
						//ɨ���� ��ʱ����״̬
						for(PackageInfo info : infos){
							if(flag){//�˳�ʱ ֹͣɨ��
								return;
							}
							msg =Message.obtain();
							msg.what = SCANING;
							msg.obj = info;
							handler.sendMessage(msg);
							packageSizeInfo = new PackageSizeInfo(info);
							
							method.invoke(pm, info.packageName,packageSizeInfo);
							
							Thread.sleep(400);
						}
						//ɨ�����
						msg =Message.obtain();
						msg.what = FINISH;
						handler.sendMessage(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		/**
		 * ɨ������Ϣbean
		 * @author Administrator
		 *
		 */
		private class ScanInfo{
			Drawable icon;
			String packageName;
			String appName;
			long cacheSize;
		}
		/**
		 * �ӿ�ʵ���� �ص�
		 * @author Administrator
		 *
		 */
		private class PackageSizeInfo extends IPackageStatsObserver.Stub{
			private PackageInfo info;
			public PackageSizeInfo(PackageInfo info) {
				super();
				this.info = info;
			}
			/**
			 * �ص�����
			 */
			public void onGetStatsCompleted(PackageStats pStats,
					boolean succeeded) throws RemoteException {
				long cacheSize = pStats.cacheSize;
				ScanInfo scanInfo = null;
				if(cacheSize > 0){
					scanInfo = new ScanInfo();
					scanInfo.appName = info.applicationInfo.loadLabel(pm).toString();
					scanInfo.cacheSize = cacheSize;
					scanInfo.icon  = info.applicationInfo.loadIcon(pm);
					scanInfo.packageName = info.packageName;
					scanInfos.add(scanInfo);
					scanInfo = null;
				}
			}
		}
		/**
		 * �������Cache ��ҪȨ�� 
		 * android.permission.CLEAR_APP_CACHE

		 * @param v
		 */
		public void clearAllCache(View v){
			if(scanInfos.size() > 0){
				try {
					Method method = PackageManager.class.getDeclaredMethod("freeStorageAndNotify", long.class,IPackageDataObserver.class	);
					method.invoke(pm, Integer.MAX_VALUE,new ClearCacheObserver());
					long totalSize = 0;
					for(ScanInfo info : scanInfos){
						totalSize += info.cacheSize;
					}
					scanInfos.clear();
					ll_clear_details.removeAllViews();
					Toast.makeText(getApplicationContext(), "��ϲ�����ͷ���"+Formatter.formatFileSize(getApplicationContext(), totalSize)+"�Ŀռ�", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				Toast.makeText(getApplicationContext(), "�����ֻ��ǳ��ɾ�����������", Toast.LENGTH_LONG).show();
			}
		}
		private class ClearCacheObserver extends IPackageDataObserver.Stub{
			public void onRemoveCompleted(String packageName, boolean succeeded)
					throws RemoteException {
			}
		}
		
}
