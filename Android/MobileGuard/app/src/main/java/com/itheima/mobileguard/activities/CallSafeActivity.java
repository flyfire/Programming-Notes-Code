package com.itheima.mobileguard.activities;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.db.dao.BlackNumberDao;
import com.itheima.mobileguard.domain.BlackInfo;

public class CallSafeActivity extends Activity {
	private ListView lv_callsms_safe;// ��ʾ���ݵ�ListView
	private MyBaseAdapter adapter;// ������
	private List<BlackInfo> infos;// �洢������
	private BlackNumberDao dao;// ����������
	private LinearLayout ll_add_number_tips;// û������ʱ����ʾ
	private LinearLayout ll_loading;// ��ȡ���ݽ�����
	private int maxSize = 20;// �����ض���������
	private int startIndex = 0;// �������ݵ���ʼλ��
	private MyOnScrollListener listener;// ListView����������
	private int totalRecord;// �ܵü�¼����

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();// �����ʼ��
		fillData(startIndex, maxSize);// ���ݳ�ʼ��
	}

	// ���һ��������
	public void add(View v) {
		AlertDialog.Builder builder = new Builder(this);
		final View view = View
				.inflate(this, R.layout.dialog_callsafe_add, null);
		final AlertDialog dialog = builder.create();
		dialog.setView(view, 0, 0, 0, 0);// �����������ҵĿؼ�Ϊ
		Button bt_ok = (Button) view.findViewById(R.id.bt_callsafe_ok);
		Button bt_cancel = (Button) view.findViewById(R.id.bt_callsafe_cancel);
		bt_ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText et = (EditText) view
						.findViewById(R.id.et_callsafe_number);
				String number = et.getText().toString().trim();
				if (TextUtils.isEmpty(number)) {
					Toast.makeText(CallSafeActivity.this, "���������",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// ��Ӻ��� ��ȡģʽ
				CheckBox cb_phone = (CheckBox) view
						.findViewById(R.id.cb_callsafe_phone);
				CheckBox cb_sms = (CheckBox) view
						.findViewById(R.id.cb_callsafe_sms);
				String mode = "0";
				if (!cb_phone.isChecked() && !cb_sms.isChecked()) {
					Toast.makeText(CallSafeActivity.this, "��ѡ��ģʽ",
							Toast.LENGTH_LONG).show();
						return;
				} else if (cb_phone.isChecked() && cb_sms.isChecked()) {
					mode = "1";
				} else if (cb_phone.isChecked()) {
					mode = "3";
				} else if (cb_sms.isChecked()) {
					mode = "2";
				}
				if (dao.insert(number, mode)) {
					ll_add_number_tips.setVisibility(View.INVISIBLE);
					startIndex++;
				}
				// ��������ӵ������� ��ʾ����
				BlackInfo info = new BlackInfo(number, mode);
				infos.add(0, info);
				// ֪ͨ������ ���ݷ����˱仯 Ҫ��ˢ����
				if (adapter == null) {
					adapter = new MyBaseAdapter();
					adapter.notifyDataSetChanged();
				} else {
					adapter.notifyDataSetChanged();
				}
				dialog.dismiss();
			}
		});
		bt_cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();

	}

	/**
	 * ��Ϣ������
	 */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ll_loading.setVisibility(View.INVISIBLE);
			if (infos.size() == 0) {
				ll_add_number_tips.setVisibility(View.VISIBLE);
			} else {
				if (adapter == null) {// ���adapter == null ˵���ǵ�һ�μ������� ��new����
					adapter = new MyBaseAdapter();
					lv_callsms_safe.setAdapter(adapter);
				} else {// ���ص����µ����ݾ�֪ͨ ���ݸ�����
					adapter.notifyDataSetChanged();// ֪ͨ�����Ѿ������� Ӧ��ˢ��
				}
			}
		}
	};

	/**
	 * ��ʾ��������
	 */
	private void fillData(final int startIndex, final int maxSize) {
		dao = new BlackNumberDao(CallSafeActivity.this);
		// �����ѯ�����ݺܶ�Ļ� �Ứ��һ����ʱ�� ����Ӧ�������߳���ȥ��ѯ
		ll_loading.setVisibility(View.VISIBLE);
		new Thread() {
			public void run() {
				totalRecord = dao.getTotalRecord();// ��ȡ�ܵ���������
				if (infos == null) {// ���û�����ݾ�ֱ�Ӹ�ֵ
					infos = dao.findpart2(startIndex, maxSize);
					handler.sendEmptyMessage(0);
				} else {// ��������� �Ͱ�������ӵ�ԭ���ļ�����ȥ
					// �����Ѿ���ѯ�� ������Ϣ�����߳���Ϣ���� ����UI
					addNewDataAndSendMessage();
				}
			}
		}.start();
	}

	/**
	 * ������Ϣ ����ͬ��
	 */
	public void addNewDataAndSendMessage() {
		synchronized (infos) {
			infos.addAll(dao.findpart2(startIndex, maxSize));
			// �����Ѿ���ѯ�� ������Ϣ�����߳���Ϣ���� ����UI
			handler.sendEmptyMessage(0);
		}
	}

	/**
	 * ����ͽ����ʼ��
	 */
	private void init() {
		setContentView(R.layout.activity_callsafe);
		lv_callsms_safe = (ListView) findViewById(R.id.lv_callsms_safe);
		ll_add_number_tips = (LinearLayout) findViewById(R.id.ll_add_number_tips);
		ll_loading = (LinearLayout) findViewById(R.id.ll_loading);
		listener = new MyOnScrollListener();
		lv_callsms_safe.setOnScrollListener(listener);

		lv_callsms_safe.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (infos != null && adapter != null) {
					final TextView tv = (TextView) view
					.findViewById(R.id.tv_callsafe_mode);
					final String number = infos.get(position).getNumber();
					AlertDialog dialog;
					AlertDialog.Builder builder = new Builder(
							CallSafeActivity.this);
					builder.setTitle("����ģʽ");
					String[] items = { "�绰����", "��������" };
					final boolean[] checkedItems = { false, false };
					builder.setMultiChoiceItems(items, checkedItems,
							new DialogInterface.OnMultiChoiceClickListener() {
								public void onClick(DialogInterface dialog,
										int which, boolean isChecked) {
									if (which == 0) {
										checkedItems[0] = isChecked;
									} else if (which == 1) {
										checkedItems[1] = isChecked;
									}
								}
							})
							.setNegativeButton("ȡ��",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									})
							.setPositiveButton("ȷ��",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											boolean phone = checkedItems[0];
											boolean sms = checkedItems[1];
											if (phone && sms) {
													if(dao.update(number, 1+"")){
														tv.setText("ȫ������");
														dialog.dismiss();
													} 
											}else if( phone){
												dao.update(number, 3+"");
												tv.setText("�绰����");
												dialog.dismiss();
											}else if(sms){
												dao.update(number, 2+"");
												tv.setText("��������");
												dialog.dismiss();
											}
										}
									});
					dialog = builder.show();
				}
			}
		});
	}

	/**
	 * ListView����������
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyOnScrollListener implements OnScrollListener {
		/*
		 * OnScrollListener.SCROLL_STATE_IDLE ��������ֹ
		 * OnScrollListener.SCROLL_STATE_FLING ���Ի���
		 * OnScrollListener.SCROLL_STATE_TOUCH_SCROLL ��������������
		 */
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// ��������ֹ��ʱ���ж��Ƿ��Ѿ����ص���ǰ���ݵ����
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
				if (view.getLastVisiblePosition() >= totalRecord - 1) {
					Toast.makeText(CallSafeActivity.this, "û�и����������",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (view.getLastVisiblePosition() == infos.size() - 1) {// -1
																		// ��Ϊlistview����ʼλ����0
					// ��Ҫ������һ������
					startIndex += maxSize;
					// ��������
					fillData(startIndex, maxSize);
				}
			}
		}

		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
		}
	}

	/**
	 * ������
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyBaseAdapter extends BaseAdapter {
		public int getCount() {
			return infos.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// �ж��ٸ����ݾͻ��������������ٴ�
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = null;
			ViewHolder holder = null;
			if (convertView == null) {
				view = View.inflate(CallSafeActivity.this,
						R.layout.item_callsafe_listblacknumber, null);
				holder = new ViewHolder();
				holder.tv_mode = (TextView) view
						.findViewById(R.id.tv_callsafe_mode);
				holder.tv_number = (TextView) view
						.findViewById(R.id.tv_callsafe_number);
				holder.iv = (ImageView) view.findViewById(R.id.iv_callsafe);
				view.setTag(holder);
			} else {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			}
			final String number = infos.get(position).getNumber();
			holder.tv_number.setText(number);
			String mode = infos.get(position).getMode();
			if ("1".equals(mode)) {
				holder.tv_mode.setText("ȫ������");
			} else if ("2".equals(mode)) {
				holder.tv_mode.setText("��������");
			} else if ("3".equals(mode)) {
				holder.tv_mode.setText("�绰����");
			}
			holder.iv.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					boolean result = dao.delete(number);
					if (result) {
						infos.remove(position);
						adapter.notifyDataSetChanged();
						if (infos.size() == 0) {
							ll_add_number_tips.setVisibility(View.VISIBLE);
						}
					}
				}
			});
			return view;
		}

		// findviewbyid ��ʵ�ǱȽ��˷���Դ�� û����һ����Ŀ����ͻ�ִ�����Σ��������һ�������ס�Ѿ��ҵ�
		// ��View �Ͳ���Ҫ����ִ�������Ĵ����� �����˵�ǱȽϽ�Լ��Դ��
		/**
		 * ��ͥ�� �൱��View���������
		 * 
		 * @author Administrator
		 * 
		 */
		private class ViewHolder {
			TextView tv_number;
			TextView tv_mode;
			ImageView iv;
		}
	}

}
