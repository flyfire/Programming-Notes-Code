package com.itheima.mobileguard.activities;

import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.db.dao.BlackNumberDao;
import com.itheima.mobileguard.domain.BlackInfo;

public class CallSafeActivity2 extends Activity {
	private ListView lv;
	private MyBaseAdapter adapter;
	private List<BlackInfo> infos;
	private BlackNumberDao dao;
	private LinearLayout tv_callsafe_tip;
	private LinearLayout ll;
	private int totalPage;
	private int pageSize = 20;//ҳ����ʾ����
	private int currentPage = 0;//��ǰҳ��
	private EditText et_callsafe_page;
	private TextView tv_pageinfo;
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		fillData(currentPage,pageSize);
	}

	/**
	 * ��Ϣ������
	 */
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			ll.setVisibility(View.INVISIBLE);//��ѯ������ѽ���������Ϊ ���ɼ�
			if (infos.size()==0) {
				tv_callsafe_tip.setVisibility(View.VISIBLE);//�����ѯ�������� 0 ����ʾ�û���û������κ�����
			}else{
				tv_pageinfo.setText(currentPage+"/"+totalPage);
				adapter = new MyBaseAdapter();
				lv.setAdapter(adapter);
			}
		}
	};
	
	/**
	 * ��ʾ��������
	 */
	private void fillData(final int pageNumber,final int pageSize) {
		//�����ѯ�����ݺܶ�Ļ� �Ứ��һ����ʱ�� ����Ӧ�������߳���ȥ��ѯ
		new Thread(){
			public void run(){
				dao = new BlackNumberDao(CallSafeActivity2.this);
				infos = dao.findpart(pageNumber, pageSize);
				// �ܵ�ҳ���� = �ܼ�¼��  /	ҳ�������ʾ����Ŀ��
				int total = dao.getTotalRecord();
				totalPage = total%pageSize==0?(total/pageSize)-1:total/pageSize;
				//�����Ѿ���ѯ�� ������Ϣ�����߳���Ϣ���� ����UI
				Message msg = Message.obtain();
				handler.sendMessage(msg);
			}
		}.start();
	}

	/**
	 * ����ͽ����ʼ��
	 */
	private void init() {
		setContentView(R.layout.activity_callsafe2);
		lv = (ListView) findViewById(R.id.lv_callsms_safe);
		tv_callsafe_tip = (LinearLayout) findViewById(R.id.ll_add_number_tips);
		ll = (LinearLayout) findViewById(R.id.ll_loading);
		et_callsafe_page = (EditText) findViewById(R.id.et_callsafe_page);
		tv_pageinfo = (TextView) findViewById(R.id.tv_callsafe_pageinfo);
	}
	
	/**
	 * ��һҳ
	 * @param v
	 */
	public void nextpage(View v){
		if(currentPage == totalPage){
			Toast.makeText(this, "�Ѿ������һҳ��", Toast.LENGTH_LONG).show();
		}else{
			currentPage +=1;
			fillData(currentPage, pageSize);
		}
	}
	/**
	 * ��һҳ
	 * @param v
	 */
	public void previouspage(View v){
		if(currentPage <= 0){
			Toast.makeText(this, "�Ѿ������һҳ��", Toast.LENGTH_LONG).show();
		}else{
			currentPage -=1;
			fillData(currentPage, pageSize);
		}
	}
	/**
	 * ��ת
	 * @param v
	 */
	public void jump(View v){
		String pageNumber = et_callsafe_page.getText().toString().trim();
		if(TextUtils.isEmpty(pageNumber)){
			Toast.makeText(this, "��������ȷ��ҳ��", Toast.LENGTH_SHORT).show();
			return;
		}
		int num = Integer.parseInt(pageNumber);
		if(num >= 0 && num <=totalPage){
			currentPage = num;
			fillData(currentPage, pageSize);
		}
	}
	
	/**
	 * ������
	 * @author Administrator
	 *
	 */
	private class MyBaseAdapter extends BaseAdapter{
		public int getCount() {
			return infos.size();
		}
		public Object getItem(int position) {
			return null;
		}
		public long getItemId(int position) {
			return 0;
		}
		//�ж��ٸ����ݾͻ��������������ٴ� 
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHolder holder = null;
			if(convertView == null){
				view = View.inflate(CallSafeActivity2.this, R.layout.item_callsafe_listblacknumber, null);
				holder = new ViewHolder();
				holder.tv_mode = (TextView) view.findViewById(R.id.tv_callsafe_mode);
				holder.tv_number = (TextView) view.findViewById(R.id.tv_callsafe_number);
				view.setTag(holder);
			}else{
				view = convertView;
				holder = (ViewHolder) view.getTag();
			}
			holder.tv_number.setText(infos.get(position).getNumber());
			String mode = infos.get(position).getMode();
			if("1".equals(mode)){
				holder.tv_mode.setText("ȫ������");
			}else if("2".equals(mode)){
				holder.tv_mode.setText("��������");
			}else if("3".equals(mode)){
				holder.tv_mode.setText("�绰����");
		}
			return view;
		}
		//findviewbyid ��ʵ�ǱȽ��˷���Դ�� û����һ����Ŀ����ͻ�ִ�����Σ��������һ�������ס�Ѿ��ҵ�
		//��View �Ͳ���Ҫ����ִ�������Ĵ�����  �����˵�ǱȽϽ�Լ��Դ��
		/**
		 * ��ͥ��  �൱��View���������
		 * @author Administrator
		 *
		 */
		private class ViewHolder{
			TextView tv_number;
			TextView tv_mode;
		}
	}
	
	
	
}








