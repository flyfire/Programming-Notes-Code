package com.itheima.mobileguard.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.db.dao.LockedAppDao;
import com.itheima.mobileguard.domain.AppInfo;
import com.itheima.mobileguard.engine.AppInfoParser;

public class LockedAppFragment extends Fragment {

	private ListView lv_lock_app;//ListView ������ʾ����
	private List<AppInfo> lockApps;//�洢 ������APP
	private LockedAppDao dao;// ��������app �����������ݿ� ��������ѯ
	private LockedAppAdapter adapter;// ������
	private TextView tv_lockedapp_count;//��ʾ����APP������
	private Animation a;//���󻬶��Ķ���
	private ContentResolver resolver;//����֪ͨ���Ź� ���ݷ����仯
	private Uri uri;//
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//��ʼ��
		resolver = getActivity().getContentResolver();
		uri  = Uri.parse("content://com.itheima.mobile.change");
		View view = inflater.inflate(R.layout.fragment_lock_app, null);
		a = AnimationUtils.loadAnimation(getActivity(), R.anim.to_left_unlock);
		lv_lock_app = (ListView) view.findViewById(R.id.lv_lock_app);
		tv_lockedapp_count = (TextView) view.findViewById(R.id.tv_locked_count);
		dao = new LockedAppDao(getActivity());
		lockApps = new ArrayList<AppInfo>();
		return view;
	}
	/**
	 * ������ݷ���onstart��,����appInfoParser��ȡȫ����appInfo �ж��Ƿ������ �����˾���ʾ
	 */
	public void onStart() {
		List<AppInfo> infos = AppInfoParser.getAllAppinfo(getActivity());
		// ���˵�δ��������
		lockApps.clear();
		for (AppInfo info : infos) {// ֻȡ������
			if (dao.isLocked(info.getPackageName())) {
				lockApps.add(info);
			} else {
			}
		}
		if (adapter == null) {
			adapter = new LockedAppAdapter();
		} else {
			adapter.notifyDataSetChanged();
		}
		// ����UI
		lv_lock_app.setAdapter(adapter);
		super.onStart();
	}
	/**
	 * ������
	 * @author Administrator
	 *
	 */
	private class LockedAppAdapter extends BaseAdapter {
		public int getCount() {
			tv_lockedapp_count.setText("�Ѽ�������:" + lockApps.size());
			return lockApps.size();
		}
		public Object getItem(int position) {
			return null;
		}
		public long getItemId(int position) {
			return 0;
		}
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final View view;
			ViewHolder holder = null;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				holder = new ViewHolder();
				view = View.inflate(getActivity(), R.layout.item_applocked,
						null);
				holder.iv = (ImageView) view.findViewById(R.id.iv_appinfo_icon);
				holder.iv_app_lock = (ImageView) view
						.findViewById(R.id.iv_app_lock);
				holder.tv = (TextView) view.findViewById(R.id.tv_appname);
				view.setTag(holder);
			}
			holder.tv.setText(lockApps.get(position).getAppName());
			holder.iv.setImageDrawable(lockApps.get(position).getAppIcon());
			// ��ͼ����ӵ�������¼�
			holder.iv_app_lock.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1.0f,
							Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
					ta.setDuration(300);
					view.startAnimation(ta);
					new Thread() {
						public void run() {
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							getActivity().runOnUiThread(new Runnable() {
								public void run() {
									dao.delete(lockApps.get(position).getPackageName());
									//��ɾ�����ݿ� �� ɾ����Ŀ �������Ǳ�Խ��
									lockApps.remove(position);// ɾ����Ŀ
									//���߿��Ź����ݿ�仯��  Ҫ����������
									resolver.notifyChange(uri, null);
									adapter.notifyDataSetChanged();// ����UI
								}
							});
						}
					}.start();
				}
			});
			return view;
		}
	}
	static class ViewHolder {
		ImageView iv;
		TextView tv;
		ImageView iv_app_lock;
	}
}