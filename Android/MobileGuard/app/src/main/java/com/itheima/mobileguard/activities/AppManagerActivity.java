package com.itheima.mobileguard.activities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.text.format.Formatter;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.domain.AppInfo;
import com.itheima.mobileguard.engine.AppInfoParser;
import com.itheima.mobileguard.utils.DensityUtil;
import com.stericson.RootTools.RootTools;

public class AppManagerActivity extends Activity implements OnClickListener {

	private TextView tv_appmanager_systeminfo;
	private TextView tv_appmanager_sdinfo;
	private ListView lv_appinfo;
	private LinearLayout ll_appManager_loading;
	private TextView tv_appmanager_appcount;
	private PopupWindow popupWindow;
	private PackageUninstallReceiver receiver;
	private AppInfoAdapter adapter;
	/**
	 * Liveview �� ���������Ŀ������������
	 */
	private AppInfo appInfo;
	/**
	 * ϵͳӦ��
	 */
	private List<AppInfo> systemAppInfos;
	/**
	 * �û���Ӧ��
	 */
	private List<AppInfo> userAppInfos;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appmanager);
		tv_appmanager_sdinfo = (TextView) findViewById(R.id.tv_appmanager_sdinfo);
		tv_appmanager_systeminfo = (TextView) findViewById(R.id.tv_appmanager_systeminfo);
		lv_appinfo = (ListView) findViewById(R.id.lv_appinfos);
		ll_appManager_loading = (LinearLayout) findViewById(R.id.ll_appmanamger_loading);
		tv_appmanager_appcount = (TextView) findViewById(R.id.tv_appmanager_appcount);

		// ע��㲥
		receiver = new PackageUninstallReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addDataScheme("package");
		registerReceiver(receiver, filter);

		fillInfo();
		loadAppInfos();
		lv_appinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �õ����ص�ListView�е�item�İ����Ķ���
				Object obj = lv_appinfo.getItemAtPosition(position);
				appInfo = (AppInfo) obj;
				if (obj != null && obj instanceof AppInfo) {

					View contentView = View.inflate(AppManagerActivity.this,
							R.layout.item_appmanager_popup, null);
					LinearLayout ll_uninstall = (LinearLayout) contentView
							.findViewById(R.id.ll_uninstall);
					LinearLayout ll_start = (LinearLayout) contentView
							.findViewById(R.id.ll_start);
					LinearLayout ll_share = (LinearLayout) contentView
							.findViewById(R.id.ll_share);
					LinearLayout ll_setting = (LinearLayout) contentView
							.findViewById(R.id.ll_setting);
					ll_uninstall.setOnClickListener(AppManagerActivity.this);
					ll_share.setOnClickListener(AppManagerActivity.this);
					ll_start.setOnClickListener(AppManagerActivity.this);
					ll_setting.setOnClickListener(AppManagerActivity.this);
					// ��ȡ��ǰ�������View����Ļ�ϵ�λ��
					int[] location = new int[2];
					view.getLocationInWindow(location);

					dismissPopupWindow();
					// -2 -2 ʱ��������
					popupWindow = new PopupWindow(contentView, -2, -2);
					// PopupWindowĬ����û�б���ɫ�� ����Ҫ���Ŷ�������Ҫ�б���ɫ ��������һ��͸���ı���ɫ
					popupWindow.setBackgroundDrawable(new ColorDrawable(
							Color.TRANSPARENT));
					// ���ö���
					AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
					aa.setDuration(200);
					aa.setRepeatCount(0);
					ScaleAnimation sa = new ScaleAnimation(0.5f, 1f, 0.5f, 1f,
							Animation.RELATIVE_TO_SELF, 0,
							Animation.RELATIVE_TO_SELF, 1);
					sa.setDuration(200);
					sa.setRepeatCount(0);
					AnimationSet as = new AnimationSet(false);
					as.addAnimation(aa);
					as.addAnimation(sa);
					popupWindow.showAtLocation(parent, Gravity.LEFT
							+ Gravity.TOP, 10, location[1] -( view.getHeight() + DensityUtil.px2dip(AppManagerActivity.this,12f)));
					contentView.startAnimation(as);
				}
			}
		});

		lv_appinfo.setOnScrollListener(new OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				dismissPopupWindow();// �϶�ʱ����popup

				if (userAppInfos != null && systemAppInfos != null) {
					/*
					 * if(firstVisibleItem <= userAppInfos.size()){
					 * tv_appmanager_appcount
					 * .setText("�û�����"+userAppInfos.size()+"��"); }else
					 * if(view.getFirstVisiblePosition() >= userAppInfos.size()
					 * -1 ){
					 * tv_appmanager_appcount.setText("ϵͳ����"+systemAppInfos
					 * .size()+"��"); }
					 */
					if (firstVisibleItem >= (userAppInfos.size() + 1)) {
						tv_appmanager_appcount.setText("ϵͳ����"
								+ systemAppInfos.size() + "��");
					} else {
						tv_appmanager_appcount.setText("�û�����"
								+ userAppInfos.size() + "��");
					}
				}
			}
		});
	}

	/**
	 * ��ȡϵͳ��sd���Ŀ��ÿռ� ����ֵ����Ӧ�ؼ�
	 */
	private void fillInfo() {
		File sdFile = Environment.getExternalStorageDirectory();
		long sdFreeSpace = sdFile.getFreeSpace();
		File systemFile = Environment.getRootDirectory();
		long systemFreeSpace = systemFile.getFreeSpace();
	
		System.out.println("usable:"+	Formatter.formatFileSize(this, systemFile.getUsableSpace())+",total:"+systemFile.getTotalSpace());
//		StatFs sf = new StatFs(systemFile.getPath());
//		long blockSize = sf.getBlockSize();
//		long blockCount = sf.getBlockCount();
//		long availCount = sf.getAvailableBlocks();
//		System.out.println(blockSize);
//		System.out.println(blockCount);
//		System.out.println(availCount);
		// ��ʽ��
		String sd = Formatter.formatFileSize(this, sdFreeSpace);
		String system = Formatter.formatFileSize(this, systemFreeSpace);
		tv_appmanager_sdinfo.setText("SD�����ÿռ�" + sd);
		tv_appmanager_systeminfo.setText("ϵͳ���ÿռ�" + system);
	}

	/**
	 * ��Ϣ������
	 */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ll_appManager_loading.removeAllViews();
			ll_appManager_loading.setVisibility(View.INVISIBLE);
			
			if (adapter == null) {
				adapter = new AppInfoAdapter();
				lv_appinfo.setAdapter(adapter);
			} else {
				adapter.notifyDataSetChanged();
			}

			System.out.println("����");
		}
	};

	/**
	 * ����������
	 */
	private class AppInfoAdapter extends BaseAdapter {
		public int getCount() {
			return systemAppInfos.size() + userAppInfos.size();
		}

		public Object getItem(int position) {
			AppInfo appInfo = null;
			if (position == 0) {
				return null;
			} else if (position == userAppInfos.size() + 1) {
				return null;
			}
			if (position <= userAppInfos.size()) {
				appInfo = userAppInfos.get(position - 1);
				return appInfo;
			} else if (position >= userAppInfos.size() + 1 + 1) {
				int location = position - userAppInfos.size() - 1 - 1;
				appInfo = systemAppInfos.get(location);
			}

			return appInfo;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if (position == 0) {
				TextView tv = new TextView(getApplicationContext());
				tv.setBackgroundColor(Color.GRAY);
				tv.setText("�û�����" + userAppInfos.size() + "��");
				tv.setTextColor(Color.WHITE);
				return tv;
			} else if (position == userAppInfos.size() + 1) {
				TextView tv = new TextView(getApplicationContext());
				tv.setBackgroundColor(Color.GRAY);
				tv.setText("ϵͳ����" + systemAppInfos.size() + "��");
				tv.setTextColor(Color.WHITE);
				return tv;
			}
			AppInfo appInfo = null;
			if (position <= userAppInfos.size()) {
				appInfo = userAppInfos.get(position - 1);
			} else if (position >= userAppInfos.size() + 1 + 1) {
				int location = position - userAppInfos.size() - 1 - 1;
				appInfo = systemAppInfos.get(location);
			}

			ViewHolder holder = null;
			if (convertView != null && convertView instanceof LinearLayout) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(AppManagerActivity.this,
						R.layout.item_appinfo, null);
				holder = new ViewHolder();
				holder.iv_appinfo_icon = (ImageView) view
						.findViewById(R.id.iv_appinfo_icon);
				holder.tv_appname = (TextView) view
						.findViewById(R.id.tv_appname);
				holder.tv_appcategory = (TextView) view
						.findViewById(R.id.tv_appcategory);
				holder.tv_appsize = (TextView) view
						.findViewById(R.id.tv_appsize);
				view.setTag(holder);
			}
			holder.iv_appinfo_icon.setImageDrawable(appInfo.getAppIcon());
			holder.tv_appname.setText(appInfo.getAppName());
			holder.tv_appsize.setText(Formatter.formatFileSize(
					AppManagerActivity.this, appInfo.getAppSize()));
			if (appInfo.isInRom()) {
				holder.tv_appcategory.setText("�ֻ��ڴ�");
			} else {
				holder.tv_appcategory.setText("SD��");
			}
			return view;
		}

	}

	/**
	 * View������ ������ס�ؼ����ӿؼ�
	 * 
	 * @author Administrator
	 * 
	 */
	private class ViewHolder {
		ImageView iv_appinfo_icon;
		TextView tv_appname;
		TextView tv_appcategory;
		TextView tv_appsize;
	}

	/**
	 * ����APP����Ϣ
	 */
	public void loadAppInfos() {
		Animation a = AnimationUtils.loadAnimation(this, R.anim.loading);
		ll_appManager_loading.startAnimation(a);
		ll_appManager_loading.setVisibility(View.VISIBLE);
		systemAppInfos = new ArrayList<AppInfo>();
		userAppInfos = new ArrayList<AppInfo>();
		new Thread() {
			public void run() {
				List<AppInfo> infos = AppInfoParser
						.getAllAppinfo(AppManagerActivity.this);
				for (AppInfo info : infos) {
					if (info.isUserApp()) {
						userAppInfos.add(info);
					} else {
						systemAppInfos.add(info);
					}
				}
				handler.sendEmptyMessage(0);
			}
		}.start();
	}

	private void dismissPopupWindow() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			popupWindow = null;
		}
	}

	@Override
	protected void onDestroy() {
		dismissPopupWindow();
		// ��ע��㲥
		unregisterReceiver(receiver);
		receiver = null;
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.ll_start:// ����
			startApplication();
			break;
		case R.id.ll_share:// ����
			shareApp();
			System.out.println("lai le a ");
			break;
		case R.id.ll_uninstall:// ж��
			System.out.println("lai le a ");
			uninstallApplication();
			break;
		case R.id.ll_setting:// ��ϸ����
			showAppSetting();
			System.out.println("lai le a ");
			break;
		}
		dismissPopupWindow();
	}

	private void showAppSetting() {
		Intent intent = new Intent();
		intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setData(Uri.parse("package:" + appInfo.getPackageName()));
		startActivity(intent);
	}

	/**
	 * ɾ��һ������ ж�سɹ�����Ҫˢ�½��� ע��㲥
	 */
	private void uninstallApplication() {

		boolean b = appInfo.isUserApp();
		if (b) {
			// �û����� ����ж��
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setAction(Intent.ACTION_DELETE);
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.setData(Uri.parse("package:" + appInfo.getPackageName()));
			startActivity(intent);
		} else {
			if (!RootTools.isRootAvailable()) {// �ֻ�û��root
				Toast.makeText(this, "ж��ϵͳӦ����Ҫ�ңϣϣ�Ȩ��", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			try {
				if (!RootTools.isAccessGiven()) {
					Toast.makeText(this, "���ȸ���Ӧ�÷���rootȨ��", Toast.LENGTH_LONG)
							.show();
					return;
				}
				/*
				 * RootTools.sendShell("mount -a remount ,rw /system", 30000);
				 * RootTools.sendShell("rm -r "+appInfo.getApkPath(), 30000);
				 * RootTools.sendShell("mount -a remount ,r /system", 30000);
				 */
				RootTools.sendShell("mount -o remount ,rw /system", 3000);
				RootTools.sendShell("rm -r " + appInfo.getApkPath(), 30000);
				RootTools.sendShell("mount -o remount ,r /system", 3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ͨ�����ŷ����Ӧ��
	 */
	private void shareApp() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.putExtra(Intent.EXTRA_TEXT,
				"����һ��Ӧ�ø��㣬���Ӧ�úܲ���:" + appInfo.getAppName()
						+ "����·����https://play.google.com/store/apps/details?id="
						+ appInfo.getPackageName());
		intent.setType("text/plain");
		startActivity(intent);
	}

	/**
	 * ����һ��application
	 */
	public void startApplication() {
		PackageManager pm = getPackageManager();
		// ͨ������������������� ����ȡ�����˰���Ӧ��application����ͼ
		Intent intent = pm.getLaunchIntentForPackage(appInfo.getPackageName());
		// �е�applicationû�н��� �����п���intentΪnull
		if (intent == null) {
			Toast.makeText(this, "�ó���û�н���", Toast.LENGTH_SHORT).show();
		} else {
			startActivity(intent);
		}
	}

	private class PackageUninstallReceiver extends BroadcastReceiver {

		public void onReceive(Context context, Intent intent) {
			// ֻҪ��Ӧ�ñ�ж���� ��ˢ�½���
			System.out.println("ddsssssssssssssssssssssssssssssss"
					+ intent.getAction() + intent.getDataString());
			loadAppInfos();

		}
	}
	

}
