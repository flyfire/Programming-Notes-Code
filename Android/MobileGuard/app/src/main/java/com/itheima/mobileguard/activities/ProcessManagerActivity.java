package com.itheima.mobileguard.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.domain.ProcessInfo;
import com.itheima.mobileguard.engine.ProcessInfoParser;
import com.itheima.mobileguard.utils.SystemInfoUtils;

public class ProcessManagerActivity extends Activity {

	private SharedPreferences sp;
	private TextView tv_processmanager_memory;//�������õ��ڴ�
	private TextView tv_processmanager_runing;//��ǰ���еĽ�����
	private ListView lv_processinfo;//ListView��ʾ����
	private LinearLayout ll_processmanamger_loading;//���ض���
	private ProcessAdapter adapter;
	private ActivityManager am;
	private List<ProcessInfo> totalInfos;
	private List<ProcessInfo> userInfos;
	private List<ProcessInfo> systemInfos;
	private String totalMemory;
	private int runnintProrcess;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process_managedr);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		tv_processmanager_memory = (TextView) findViewById(R.id.tv_processmanager_memory);
		tv_processmanager_runing = (TextView) findViewById(R.id.tv_processmanager_runing);
		lv_processinfo = (ListView) findViewById(R.id.lv_processinfo);
		ll_processmanamger_loading = (LinearLayout) findViewById(R.id.ll_processmanamger_loading);

		totalMemory = Formatter.formatFileSize(this,
				SystemInfoUtils.getTotalMemory(this));
		runnintProrcess = SystemInfoUtils.getRunningProcessCount(this);
		tv_processmanager_runing.setText("�����н���:"
				+ runnintProrcess);

		tv_processmanager_memory.setText("�����ڴ�/���ڴ�:"
				+ Formatter.formatFileSize(this,
						SystemInfoUtils.getAvailableMemory(this)) + "/"
				+ totalMemory);
		fillData();// ��������  ����onstart��

		/**
		 * ��Ŀ����¼�
		 */
		lv_processinfo.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Object obj = parent.getItemAtPosition(position);
				if (obj != null) {
					ProcessInfo info = (ProcessInfo) obj;
					ViewHolder holder = (ViewHolder) view.getTag();
					if (info.isChecked()) {
						holder.cb.setChecked(false);
						info.setChecked(false);
					} else {
						holder.cb.setChecked(true);
						info.setChecked(true);
					}
					if (adapter != null) {
						adapter.notifyDataSetChanged();
					}
				}
			}
		});
	}
/**
 * ��������
 */
	@Override
	protected void onStart() {
	
	 if(adapter != null){
			adapter.notifyDataSetChanged();
		}
		super.onStart();
	}
	
	/**
	 * ��������
	 */
	private void fillData() {
		ll_processmanamger_loading.setVisibility(View.VISIBLE);
		ll_processmanamger_loading.startAnimation(AnimationUtils.loadAnimation(
				this, R.anim.loading));
		// �����Ǻ�ʱ�Ĳ���
		new Thread() {
			public void run() {
				totalInfos = ProcessInfoParser
						.getRunningProcess(ProcessManagerActivity.this);
				systemInfos = new ArrayList<ProcessInfo>();
				userInfos = new ArrayList<ProcessInfo>();// ��ʼ������
				for (ProcessInfo info : totalInfos) {// �ֱ�洢
					if (info.isUserApp()) {
						userInfos.add(info);
					} else {
						systemInfos.add(info);
					}
					// �洢��Ͽ��Ը���UI��
					runOnUiThread(new Runnable() {
						public void run() {
							ll_processmanamger_loading
									.setVisibility(View.INVISIBLE);
							ll_processmanamger_loading.removeAllViews();
							adapter = new ProcessAdapter();
							lv_processinfo.setAdapter(adapter);
						}
					});
				}
			}
		}.start();
	}
	

/**
 * �������ý���
 * @param v
 */
	public void setting(View v){
		Intent intent = new Intent(this,ProcessManagerSettingActivity.class	);
		startActivity(intent);
	}
	/**
	 * �������
	 * 
	 * @param view
	 */
	public void clear(View view) {
		/**
		 * ��Ϊ��Щϵͳ����ǲ����Ա�ɱ���ģ������û����ᶮ����������������ϵ����������������ƭ�û���
		 * ��û����İ����н��̶�ɱ����Ҳ�ǲ�����ʵ��ɱ���Ľ�������ʾ���ݣ����ǴӼ�����ɾ���û�ѡ���
		 * ���ݣ��ﵽһ������û������Ч������Ҫע����ǣ��ڱ������н���ɾ����ʱ�򣬲�Ҫһ�߱�����һ��
		 * �Լ��ϵ�Ԫ�ؽ����޸ģ�����ɲ����޸��쳣��(��ȻҲ����ѡ��listIterator)����ѡ��һ��������������
		 * ��ס�û�ѡ��Ľ��̣�Ȼ������ɾ��
		 */
		if (userInfos.size() == 1 && systemInfos.size() == 0) {
			Toast.makeText(this, "�Ѵﵽ���״̬", Toast.LENGTH_LONG).show();
			return;
		}
		List<ProcessInfo> tempInfos = new ArrayList<ProcessInfo>();
		int count = 0;
		long clearedMem = 0;
		for (ProcessInfo info : userInfos) {
			if (info.getPackageName().equals(getPackageName())) {
				continue;
			}
			if (info.isChecked()) {
				tempInfos.add(info);
				count++;
				am.killBackgroundProcesses(info.getPackageName());
				clearedMem += info.getOccupyMemory();
			}
		}
		for (ProcessInfo info : systemInfos) {
			if (info.isChecked()) {
				tempInfos.add(info);
				am.killBackgroundProcesses(info.getPackageName());
				clearedMem += info.getOccupyMemory();
				count++;
			}
		}
		if (tempInfos.size() == 0) {
			Toast.makeText(this, "��ѡ����Ҫɱ���Ľ���", Toast.LENGTH_SHORT).show();
			return;
		}
		userInfos.removeAll(tempInfos);
		systemInfos.removeAll(tempInfos);
		adapter.notifyDataSetChanged();
		int i = runnintProrcess -= count;
		tv_processmanager_runing.setText("�����н���:" + (i<1?1:i));

		tv_processmanager_memory
				.setText("�����ڴ�/���ڴ�:"
						+ Formatter.formatFileSize(this,
								(SystemInfoUtils.getAvailableMemory(this)+clearedMem)) + "/" + totalMemory);

		Toast.makeText(
				this,
				"ɱ����" + count + "������,�ͷ���"
						+ Formatter.formatFileSize(this, clearedMem) + "���ڴ�",
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * ȫѡ����
	 * 
	 * @param v
	 */
	public void selectAll(View v) {
		for (ProcessInfo info : userInfos) {
			info.setChecked(true);
		}
		for (ProcessInfo info : systemInfos) {
			info.setChecked(true);
		}
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * ��ѡ����
	 * 
	 * @param v
	 */
	public void oppositeSelect(View v) {
		for (ProcessInfo info : userInfos) {
			info.setChecked(!info.isChecked());
		}
		for (ProcessInfo info : systemInfos) {
			info.setChecked(!info.isChecked());
		}
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * ����������
	 * 
	 * @author Administrator
	 * 
	 */
	private class ProcessAdapter extends BaseAdapter {
		public int getCount() {
			if(sp.getBoolean("showSystemProcess", true)){
				return systemInfos.size() + 1 + userInfos.size() + 1;
			}else{
				return userInfos.size() + 1;
			}
		}

		public Object getItem(int position) {
			ProcessInfo info = null;
			if (position == 0) {
				return null;
			} else if (position == userInfos.size() + 1) {
				return null;
			} else if (position <= userInfos.size()) {
				info = userInfos.get(position - 1);
			} else if (position >= userInfos.size() + 1 + 1) {
				info = systemInfos.get(position - userInfos.size() - 1 - 1);
			}
			return info;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ProcessInfo info = null;
			if (position == 0) {
				TextView tv = new TextView(ProcessManagerActivity.this);
				tv.setTextColor(Color.WHITE);
				tv.setBackgroundColor(0xBB000000);
				tv.setText("�û�����" + userInfos.size());
				return tv;
			} else if (position == userInfos.size() + 1) {
				TextView tv = new TextView(ProcessManagerActivity.this);
				tv.setTextColor(Color.WHITE);
				tv.setBackgroundColor(Color.BLACK);
				tv.setText("ϵͳ����" + systemInfos.size());
				return tv;
			} else if (position <= userInfos.size()) {
				info = userInfos.get(position - 1);
			} else if (position >= userInfos.size() + 1 + 1) {
				info = systemInfos.get(position - userInfos.size() - 1 - 1);
			}
			View view = null;
			ViewHolder holder = null;
			if (convertView != null && convertView instanceof RelativeLayout) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(getApplicationContext(),
						R.layout.item_process_info, null);
				holder = new ViewHolder();
				holder.icon = (ImageView) view
						.findViewById(R.id.iv_process_icon);
				holder.tv_name = (TextView) view
						.findViewById(R.id.tv_process_appname);
				holder.tv_meminfo = (TextView) view
						.findViewById(R.id.tv_process_memory);
				holder.cb = (CheckBox) view.findViewById(R.id.cb_process);
				view.setTag(holder);
			}
			if (info.getPackageName().equals(getPackageName())) {
				holder.cb.setVisibility(View.INVISIBLE);
			} else {
				holder.cb.setVisibility(View.VISIBLE);
			}
			holder.icon.setImageDrawable(info.getIcon());
			holder.tv_name.setText(info.getAppName());
			holder.tv_meminfo.setText(Formatter.formatFileSize(
					getApplicationContext(), info.getOccupyMemory()));
			holder.cb.setChecked(info.isChecked());
			return view;
		}
	}

	static class ViewHolder {
		ImageView icon;
		TextView tv_name;
		TextView tv_meminfo;
		CheckBox cb;
	}

}
