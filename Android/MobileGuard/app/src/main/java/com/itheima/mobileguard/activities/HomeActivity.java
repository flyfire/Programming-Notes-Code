package com.itheima.mobileguard.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.services.GuardServer1;
import com.itheima.mobileguard.services.KeepLifeService;
import com.itheima.mobileguard.utils.MD5Utils;
import com.itheima.mobileguard.utils.UiUtils;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

public class HomeActivity extends Activity {
	
	private GridView gv_home_function;
	private SharedPreferences sp;
	// �Ŵ���
	private String[] items = { "�ֻ�����", "ͨѶ��ʿ", "����ܼ�", "���̹���", "����ͳ��", "�ֻ�ɱ��",
			"��������", "�߼�����", "��������" };
	// �Ŵ���ͼƬid
	private int[] icons = { R.drawable.safe, R.drawable.callmsgsafe,
			R.drawable.app_selector, R.drawable.taskmanager,
			R.drawable.netmanager, R.drawable.trojan, R.drawable.sysoptimize,
			R.drawable.atools, R.drawable.settings };

	
	@Override
	public void onBackPressed() {
		StartAppAd saa = new StartAppAd(this);
		saa.onBackPressed();
	    super.onBackPressed();
	}
	@Override
	protected void onResume() {
		StartAppAd saa = new StartAppAd(this);
		saa.onResume();
		super.onResume();
	}

	
	// ��ʼ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		StartAppSDK.init(this, "105756043", "208401311", true);
		
		setContentView(R.layout.activity_home);
	
		
		
	//������å�ػ�
		Intent intent = new Intent(this,GuardServer1.class);
		startService(intent);
		//������å�ػ�
		Intent keep= new Intent(this,KeepLifeService.class);
		startService(keep);
		
		// ��ȡSharedPreference
		sp = getSharedPreferences("config", MODE_PRIVATE);
		// ��ʾ������Ŀ
		gv_home_function = (GridView) findViewById(R.id.gv_home_function);
		// ע�Ṧ�ܵ���¼�
		gv_home_function.setOnItemClickListener(new MyOnItemClickListener());
	}

	/**
	 * �޸ķ������ܵ����� ��ʱ��Ч
	 */
	@Override
	protected void onStart() {
		gv_home_function.setAdapter(new GVBaseAdapterea());
		super.onStart();
	}

	// �������¼�
	private class MyOnItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent;
			switch (position) {
			case 0:
				// 0 λ�����ֻ����� ������Ӧ�ĵ���¼�
				// �ж��û��Ƿ�����������
				String passowrd = sp.getString("password", "");
				// û����������
				if (TextUtils.isEmpty(passowrd)) {
					showSetupPasswordDialog();// ������������Ի���
				} else {// ���ù�����
					showEnterPasswordDialog(passowrd);
				}
				break;
			case 1 :
				intent = new Intent(HomeActivity.this,CallSafeActivity.class);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(HomeActivity.this,AppManagerActivity.class);
				startActivity(intent);
				break;
			case 3:
				intent = new Intent(HomeActivity.this,ProcessManagerActivity.class);
				startActivity(intent);
				break;
			case 4:
				intent = new Intent(HomeActivity.this,TrafficManagerActivity.class);
				startActivity(intent);
				break;
			case 5:
				intent = new Intent(HomeActivity.this,AntilVirusActivity.class);
				startActivity(intent);
				break;
			case 6:
				intent = new Intent(HomeActivity.this,ClearCacheActivity.class);
				startActivity(intent);
				break;
			case 7 :
				intent = new Intent(HomeActivity.this,AtoolsActivity.class);
				startActivity(intent);
				break;
			case 8://������������
				//������������
				intent	 = new Intent(HomeActivity.this,SettingActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	}

	private AlertDialog dialog;// ��Ԫģʽ
	private EditText et_home_setupPassowrd;
	private EditText et_home_confirmPassowrd;
	private EditText et_home_enterPassowrd;
	private Button bt_home_ok;
	private Button bt_home_cancel;

	// ������������ĶԻ���
	public void showSetupPasswordDialog() {
		AlertDialog.Builder builder = new Builder(this);
		View view = View
				.inflate(this, R.layout.dialog_home_setuppassword, null);
		dialog = builder.setView(view).show();
		et_home_setupPassowrd = (EditText) view
				.findViewById(R.id.et_home_setuppassword);
		et_home_confirmPassowrd = (EditText) view
				.findViewById(R.id.et_home_confirmpassword);
		bt_home_ok = (Button) view.findViewById(R.id.bt_home_ok);
		bt_home_cancel = (Button) view.findViewById(R.id.bt_home_cancel);
		// ����ťע�����¼�
		bt_home_ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// �ֻ�ȡ�û�������
				String newPassword = et_home_setupPassowrd.getText().toString();
				String confirmPassowrd = et_home_confirmPassowrd.getText()
						.toString();
				if (TextUtils.isEmpty(newPassword)
						|| TextUtils.isEmpty(confirmPassowrd)) {
					UiUtils.showToast(HomeActivity.this, "�����ȷ�����붼��������");
					return;
				}
				if (!newPassword.equals(confirmPassowrd)) {
					UiUtils.showToast(HomeActivity.this, "�������벻һ��");
					return;
				}
				Editor editor = sp.edit();
				editor.putString("password", MD5Utils.MD5Encode(newPassword));
				editor.commit();
				loadLostFindActivity();
				dialog.dismiss();
			}
		});
		bt_home_cancel.setOnClickListener(new OnClickListener() {// ȡ��������
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	}

	// ������������ĶԻ���
	private void showEnterPasswordDialog(final String password) {
		AlertDialog.Builder builder = new Builder(this);
		View view = View.inflate(this, R.layout.dialog_home_enterpssword, null);
		dialog = builder.setView(view).show();
		et_home_enterPassowrd = (EditText) view
				.findViewById(R.id.et_home_enterpassword);
		bt_home_ok = (Button) view.findViewById(R.id.bt_home_ok);
		bt_home_cancel = (Button) view.findViewById(R.id.bt_home_cancel);
		// ����ťע�����¼�
		bt_home_ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// �ֻ�ȡ�û�������
				String enterPassword = et_home_enterPassowrd.getText()
						.toString();
				if (TextUtils.isEmpty(enterPassword)) {
					UiUtils.showToast(HomeActivity.this, "����������");
					return;
				} else {// У������
					if (password.equals(MD5Utils.MD5Encode(enterPassword))) {
						loadLostFindActivity();
						dialog.dismiss();
					} else {
						UiUtils.showToast(HomeActivity.this, "�������");
					}
				}
			}
		});
		bt_home_cancel.setOnClickListener(new OnClickListener() {// ȡ��������
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	}

	private void loadLostFindActivity() {
		Intent intent = new Intent(this, LostFindActivity.class);
		this.startActivity(intent);
	}

	// �Զ���������
	private class GVBaseAdapterea extends BaseAdapter {
		public int getCount() {
			return items.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = View.inflate(HomeActivity.this, R.layout.item_home_grid,
						null);
			} else {
				view = convertView;
			}
			ImageView iv = (ImageView) view.findViewById(R.id.iv_home_itme);
			TextView tv = (TextView) view.findViewById(R.id.tv_home_itme);
			iv.setImageResource(icons[position]);
			tv.setText(items[position]);
			String newName = sp.getString("newName", "");
			if (!TextUtils.isEmpty(newName) && position == 0) {
				tv.setText(newName);
			}
			return view;
		}
	}
	
	

}