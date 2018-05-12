package com.itheima.mobileguard.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.utils.MD5Utils;

public class EnterPwdActivity extends Activity {

	private SharedPreferences sp;
	private TextView et_enterpwd;
	private PackageManager pm;
	private TextView tv_enter_name;
	private ImageView iv_enter_icon;
	private String packname;
	private Intent intent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enterpwd);
		pm = getPackageManager();
		sp = getSharedPreferences("config", MODE_PRIVATE);
		tv_enter_name = (TextView) findViewById(R.id.tv_enter_name);
		iv_enter_icon = (ImageView) findViewById(R.id.iv_enter_icon);
		et_enterpwd = (TextView) findViewById(R.id.et_enterpwd);

		intent = new Intent();
		intent.setAction("com.itheima.mobileguard.stop");
		packname = getIntent().getStringExtra("packname");
		try {
			ApplicationInfo info = pm.getApplicationInfo(packname, 0);
			iv_enter_icon.setImageDrawable(info.loadIcon(pm));
			tv_enter_name.setText(info.loadLabel(pm));
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void enter(View v) {
		String psw = et_enterpwd.getText().toString();
		if(TextUtils.isEmpty(psw)){
			Toast.makeText(this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
			return;
		}
		if (sp.getString("applockPws","").equals(MD5Utils.MD5Encode(psw))) {
			// ���������ȷ�͸��߿��Ź�����Ҫ����ʳȡ���Ե��װ�����ı���
			intent.putExtra("packname", packname);// ������ȷ������  �ѵ�ǰ�������ݸ����ź� ��ʱȡ������
			sendBroadcast(intent);
			new Thread() {
				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					finish();
				}
			}.start();

		} else {
			et_enterpwd.startAnimation(AnimationUtils.loadAnimation(this,
					R.anim.shake));
			Toast.makeText(this, "�������", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * �����ؼ��ͻ�����
	 */
	@Override
	public void onBackPressed() {
		/*
		 * <action android:name="android.intent.action.MAIN" /> <category
		 * android:name="android.intent.category.HOME" /> <category
		 * android:name="android.intent.category.DEFAULT" /> <category
		 * android:name="android.intent.category.MONKEY"/>
		 */
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addCategory("android.intent.category.HOME");
		intent.addCategory("android.intent.category.MONKEY");
		startActivity(intent);
		super.onBackPressed();
	}
}
