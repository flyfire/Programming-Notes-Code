package com.itheima.mobileguard.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.db.dao.AntiVirusDao;
import com.itheima.mobileguard.utils.StreamUtils;
import com.itheima.mobileguard.utils.UiUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SplashActivity extends Activity {
	private SharedPreferences sp;
	private TextView tv_splash_versionname;
	private ProgressBar pb_splash_download;
	private int serverVersionCode;// �������İ汾��
	private int clientVersionCode;// �ͻ��˵İ汾��
	private String newVersionDesc;// �°汾������
	private String dowmloadUrl;// �°汾�����ص�ַ
	private AlphaAnimation animation;// ����
	private RelativeLayout rl_splash_root;// ���ڵ�
	private ImageView iv_splash;

	// ��ʼ��
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		// ����򿪾Ϳ�ʼ����
		iv_splash = (ImageView) findViewById(R.id.iv_splash);
		animation = new AlphaAnimation(0.5f, 1.0f);// ͸����
		animation.setDuration(1000);
		animation.setRepeatMode(Animation.REVERSE);
		animation.setRepeatCount(5);
		rl_splash_root = (RelativeLayout) findViewById(R.id.rl_splash_root);
		rl_splash_root.startAnimation(animation);
		Animation ivAnim = AnimationUtils.loadAnimation(this, R.anim.loading);
		iv_splash.startAnimation(ivAnim);
		// �ҵ����
		tv_splash_versionname = (TextView) findViewById(R.id.tv_splash_versionname);
		pb_splash_download = (ProgressBar) findViewById(R.id.pb_splash_download);
		// �����ʲ��ļ���Ӧ��Ŀ¼
		copyDB("address.db");
		//�����������ݿ⵽Ӧ��Ŀ¼
		copyDB("antivirus.db");
		System.out.println("����������");
		// �������ͼ��
		createSHORTCUT();
		
		//������ݿ����
		updateVirus();
		
		try {
			// ���ð汾��
			// flagһ��д0�Ϳ�����
			PackageInfo info = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			String versionName = info.versionName;
			tv_splash_versionname.setText(versionName);
			clientVersionCode = info.versionCode;
			// ���ӷ�������ȡ�汾��
			// ����Ƿ������Զ�����
			sp = getSharedPreferences("config", MODE_PRIVATE);
			System.out.println(sp.getBoolean("autoUpdate", false));

			if (sp.getBoolean("autoUpdate", false)) {
				checkUpdate();
			} else {
				new Thread() {
					public void run() {
						try {
							Thread.sleep(2000);
							loadMainUI();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		} catch (NameNotFoundException e) {
			// ���ᷢ�����쳣
			e.printStackTrace();
		}

		// ����ͼ���
		/*
		 * final PackageManager pm = getPackageManager(); new Thread(){ public
		 * void run(){ SystemClock.sleep(5000);
		 * pm.setComponentEnabledSetting(getComponentName(), 2, 1); } }.start();
		 */

	}

	private void updateVirus() {
			new Thread(){
				public void run(){
					//���жϲ������ݿ��Ƿ����
					if(AntiVirusDao.isVirusDBExists(getApplicationContext())){
						HttpUtils utils = new HttpUtils();
						final String version = AntiVirusDao.getVirusVersion(getApplicationContext());
						//��������ַ
						String uri = "http://192.168.1.199:8080/FileUploadForAndroid/servlet/VirusVersionCheck?version="+version;
						utils.send(HttpMethod.GET, uri, new RequestCallBack<String>(){
							public void onFailure(HttpException arg0,
									String arg1) {
							}
							public void onSuccess(ResponseInfo arg0) {
								String result = arg0.result.toString();
								if(result == null){//˵������Ҫ����
									return;
								}
								try {
									JSONObject obj = new JSONObject(result);
									String md5 = obj.getString("md5");
									String desc = obj.getString("desc");
									//�������ݿ� �� �汾
									AntiVirusDao.updateVirusVersoin(getApplicationContext(), String.valueOf(Integer.parseInt(version)+1));
									AntiVirusDao.updateVirusAPI(getApplicationContext(), md5,desc);
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						});
					}
			}
			}.start();
	}

	/**
	 * �������ͼ��
	 */
	public void createSHORTCUT() {
		if (sp.getBoolean("created", false)) {
			System.out.println(sp.getBoolean("created", false));
			return;
		}
		sp.edit().putBoolean("created", true).commit();
		/*
		 * boolean isCreatedShortcut = sp.getBoolean("isCreatedShortcut",
		 * false); if(isCreatedShortcut){ Toast.makeText(this, "�Ѿ�������ݷ�ʽ",
		 * Toast.LENGTH_LONG).show(); return; } //
		 * com.android.launcher.action.INSTALL_SHORTCUT //��ݷ�ʽ����ʹ����ʽ��ͼ Intent
		 * doWhat = new Intent(); doWhat.addCategory(Intent.CATEGORY_DEFAULT);
		 * doWhat.setAction("com.itheima.mobileguard.activities.HomeActivity");
		 * 
		 * Intent intent = new Intent();
		 * intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		 * //����ʲô���Ŀ�ݷ�ʽ intent.putExtra("duplicate", false);//ֻ������һ�����ͼ��
		 * intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "����ȫ��ʿ");
		 * intent.putExtra(Intent.EXTRA_SHORTCUT_ICON,
		 * BitmapFactory.decodeResource(getResources(), R.drawable.luncher_bg));
		 * intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, doWhat); //�����㲥
		 * ���Ҵ���һ����ݷ�ʽ sendBroadcast(intent);
		 * sp.edit().putBoolean("isCreatedShortcut", true);
		 */
		Intent intent = new Intent();
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		// ��ʲô�£���ʲô������ʲô��
		Intent shortcutIntent = new Intent();
		intent.putExtra("duplicate", false);// ֻ����һ�����ͼ��
		shortcutIntent.setAction("aaa.bbb.ccc");// ��ʽ��ͼ��action
		shortcutIntent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);// ��ݷ�ʽ��ʲô��
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "������");// ��ݷ�ʽ������
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON,
				BitmapFactory.decodeResource(getResources(), R.drawable.app));
		// ��ݷ�ʽ��ͼ��
		sendBroadcast(intent);// ���͹㲥�����Ҵ���һ����ݷ�ʽ
	}

	/**
	 * �������ݿ���Դ�ļ���Ӧ��Ŀ¼
	 * 
	 * @param dbName
	 */
	private void copyDB(final String dbName) {
		new Thread() {
			InputStream in = null;
			FileOutputStream out = null;

			public void run() {
				File file = new File(getFilesDir(), dbName);
				if (file.exists() && file.length() > 0) {
					System.out.println("��Դ�ļ��Ѿ�����");
					return;
				}
				try {
					in = getAssets().open(dbName);
					out = openFileOutput(dbName, MODE_PRIVATE);
					byte[] buf = new byte[1024];
					int len = 0;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (out != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();

	}

	private int Load_MAINUI = 1;// ���ص�������
	private int SHOW_DiALOF = 2;// �����Ի���
	// ��Ϣ������
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			int what = msg.what;
			switch (what) {
			case 1:// ���ص�������
				loadMainUI();
				break;
			case 2:
				// ��ʾ�Ի���
				showUpdateDialog();
				break;
			}
		}
	};

	// ���ص�������
	private void loadMainUI() {
		Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();// �û����������� ���ؼ���Ӧ�÷���log���� ����Ӧ��ֱ���˳�
	}

	private AlertDialog dialog;

	// ��ʾ���¶Ի���
	protected void showUpdateDialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("������ʾ").setMessage(newVersionDesc)
				.setPositiveButton("����", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// ���ط������ϵ��°汾
						downloadNewVersion();
					}
				}).setNegativeButton("�ݲ�����", new OnClickListener() {// ���Ҳ�����
							// ��ֱ�ӽ���������
							public void onClick(DialogInterface dialog,
									int which) {
								loadMainUI();
							}
						}).setOnCancelListener(new OnCancelListener() {// ֱ��ȡ���Ի���ʱ
																		// Ҳ�ǽ��뵽������
							public void onCancel(DialogInterface dialog) {
								loadMainUI();
							}
						});
		// ��ʾ�Ի���
		dialog = builder.show();
	}

	// ������������µİ汾 ��ʾ�û���װ
	protected void downloadNewVersion() {
		// �ж��û���sd���Ƿ�׼������
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			UiUtils.showToast(SplashActivity.this, "����sd��û��׼����,��ϸʧ��");
			loadMainUI();
			return;
		}
		// ��ʱ���ж�sd���ռ��Ƿ��㹻
		// ���ÿ�Դ�Ŀ��������
		HttpUtils utils = new HttpUtils();
		utils.download(dowmloadUrl,
				new File(Environment.getExternalStorageDirectory(), "temp.apk")
						.getAbsolutePath(), new RequestCallBack<File>() {
					public void onFailure(HttpException arg0, String arg1) {
						System.out.println("������� 10006������ϵ�ͷ�");
						UiUtils.showToast(SplashActivity.this,
								"������� 10006������ϵ�ͷ�");
					}

					// ���û�ѡ��װ ������ϵͳ�Դ���apk��װ�� ͨ����ͼ������
					/*
					 * <action android:name="android.intent.action.VIEW" />
					 * <action
					 * android:name="android.intent.action.INSTALL_PACKAGE" />
					 * <category android:name="android.intent.category.DEFAULT"
					 * /> <data android:scheme="content" /> <data
					 * android:scheme="file" /> <data
					 * android:mimeType="application/vnd.android.package-archive"
					 * />
					 */
					public void onSuccess(ResponseInfo<File> arg0) {
						pb_splash_download.setVisibility(ProgressBar.GONE);// ���سɹ���������ʧ
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_VIEW);
						intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
						intent.addCategory(Intent.CATEGORY_DEFAULT);
						intent.setDataAndType(Uri.fromFile(arg0.result),
								"application/vnd.android.package-archive");
						startActivityForResult(intent, 0);
						// ΪʲôҪfor result ��Ϊ����û��е��ȡ����װ ����Ҫ���뵽������
					}

					public void onLoading(long total, long current,
							boolean isUploading) {
						super.onLoading(total, current, isUploading);
						pb_splash_download.setVisibility(ProgressBar.VISIBLE);
						pb_splash_download.setMax((int) total);
						pb_splash_download.setProgress((int) current);
					}
				});
	}

	// �н������
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// ���뵽������
		loadMainUI();
		super.onActivityResult(requestCode, resultCode, data);
	}

	private long startTime;
	private long intervalTime;

	// ���ӷ����� ��ȡ�µİ汾�ź�������Ϣ
	private void checkUpdate() {
		startTime = System.currentTimeMillis();// ��ס��ʼ��ʱ��
		new Thread() {
			public void run() {
				// ��Ϣ����
				Message message = Message.obtain();
				try {
					URL url = new URL(getResources().getString(
							R.string.serviceurl));
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);// �������ӳ�ʱʱ��
					int code = conn.getResponseCode();
					if (code == 200) {
						InputStream in = conn.getInputStream();
						String content = StreamUtils.getString(in);
						JSONObject json = new JSONObject(content);
						serverVersionCode = json.getInt("versioncode");
						newVersionDesc = json.getString("desc");
						dowmloadUrl = json.getString("apkpath");
						System.out.println(serverVersionCode);
						if (serverVersionCode > clientVersionCode) {
							message.what = SHOW_DiALOF;
						} else {
							// ֱ�ӽ���������
							message.what = Load_MAINUI;
						}
					} else {
						message.what = Load_MAINUI;
						System.out.println("������� 10005������ϵ�ͷ�");
						UiUtils.showToast(SplashActivity.this,
								"������� 10005������ϵ�ͷ�");
					}
				} catch (MalformedURLException e) {
					message.what = Load_MAINUI;
					System.out.println("������� 10001������ϵ�ͷ�");
					UiUtils.showToast(SplashActivity.this, "������� 10001������ϵ�ͷ�");
					e.printStackTrace();
				} catch (NotFoundException e) {
					message.what = Load_MAINUI;
					UiUtils.showToast(SplashActivity.this, "������� 10002������ϵ�ͷ�");
					System.out.println("������� 10002������ϵ�ͷ�");
					e.printStackTrace();
				} catch (IOException e) {
					message.what = Load_MAINUI;
					UiUtils.showToast(SplashActivity.this, "������� 10003������ϵ�ͷ�");
					System.out.println("������� 10003������ϵ�ͷ�");
					e.printStackTrace();
				} catch (JSONException e) {
					message.what = Load_MAINUI;
					UiUtils.showToast(SplashActivity.this, "������� 10004������ϵ�ͷ�");
					System.out.println("������� 10004������ϵ�ͷ�");
					e.printStackTrace();
				} finally {
					intervalTime = System.currentTimeMillis() - startTime;// ������������е�ʱ��
					// ���ʱ���������� �����߳�˯������
					if (intervalTime < 2000) {
						try {
							Thread.sleep(2000 - intervalTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					// ������Ϣ
					handler.sendMessage(message);
				}
			}
		}.start();
	}
}
