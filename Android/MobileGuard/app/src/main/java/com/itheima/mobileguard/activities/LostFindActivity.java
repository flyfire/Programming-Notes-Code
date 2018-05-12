package com.itheima.mobileguard.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.utils.MD5Utils;
import com.itheima.mobileguard.utils.UiUtils;

public class LostFindActivity extends Activity {
		private SharedPreferences sp ;
		private TextView tv_lostfind_safecontact;
		private ImageView iv_lostfind_lock;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_lostfind);
			//�ҵ����
			tv_lostfind_safecontact = (TextView) findViewById(R.id.tv_lostfind_safecontact);
			iv_lostfind_lock = (ImageView) findViewById(R.id.iv_lostfind_lock);
			//��ȡSharedPreference ���ڴ洢�û�������Ϣ��
			sp = getSharedPreferences("config", MODE_PRIVATE);
			//���û��Ƿ����ù�������Ϣ
			boolean isSetup = isFinishSetup();
			//���û�оͽ��������򵼽���
			if(isSetup){
				//ֱ����ʾlostfind���� ��ȡ�û����õ�����
				String safeContact = sp.getString("safeContact", "");
				boolean isOpen = sp.getBoolean("protecting", false);
				tv_lostfind_safecontact.setText(safeContact);
				if(isOpen){
					iv_lostfind_lock.setImageResource(R.drawable.lock);
				}else{
					iv_lostfind_lock.setImageResource(R.drawable.unlock);
				}
			}else{
				//���������򵼽���
				Intent intent = new Intent(this,LostSetup1Activity.class);
				startActivity(intent);
				finish();
			}
		}
		
		/**
		 * ��ť��� ���½���������
		 * @param v
		 */
		public void reEntrySetup(View v){
			Intent  intent = new Intent(this,LostSetup1Activity.class);
			startActivity(intent);
			finish();
		}
		/**
		 * �ж��û��Ƿ��������ֻ�����
		 * @return
		 */
		private boolean isFinishSetup(){
			boolean b = sp.getBoolean("finishing", false);
			return b;
		}
		/**
		 * ��������
		 */
		public void  open(View v){
			boolean isOpen = sp.getBoolean("protecting", false);
			if(isOpen){
				Editor editor = sp.edit();
				editor.putBoolean("protecting", false);
				editor.commit();
				iv_lostfind_lock.setImageResource(R.drawable.unlock);
			}else{
				Editor editor = sp.edit();
				editor.putBoolean("protecting", true);
				editor.commit();
				iv_lostfind_lock.setImageResource(R.drawable.lock);
			}
		}
		
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.menu_lostfind, menu);
				return true;
		}
		
		private AlertDialog dialog;
		@Override
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
			if(R.id.item_change_name == item.getItemId()){
//				�����Ի���
				AlertDialog.Builder builder = new Builder(this);
				builder.setTitle("�޸ķ�������");
				final EditText et = new EditText(this);
				builder.setView(et);
				builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String newName = et.getText().toString();
						Editor  editor = sp.edit();
						editor.putString("newName",newName);
						if(!TextUtils.isEmpty(newName) && newName.length()>5){
							UiUtils.showToast(LostFindActivity.this, "���Ȳ��ܳ���5��");
							return;
						}
						editor.commit();
					}
				});
				dialog = builder.show();
					
			}else if(R.id.item_change_password == item.getItemId()){
			
				AlertDialog.Builder builder = new Builder(this);
				View view = View.inflate(this, R.layout.dialog_lostfind_changepassword,null);
				builder.setView(view);
				dialog = builder.show();
				final EditText	et_lostfind_newpassword = (EditText) view.findViewById(R.id.et_lostfind_newpassword);
				final EditText   et_lostfind_oldpassword = (EditText) view.findViewById(R.id.et_lostfind_oldpassword);
				Button	bt_lostfind_ok = (Button) view.findViewById(R.id.bt_lostfind_ok);
				Button   bt_lostfind_cancel = (Button) view.findViewById(R.id.bt_lostfind_cancel);
				
				bt_lostfind_ok.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						//�ֻ�ȡ�û�������
						String setOldPassword = sp.getString("password", "");//���õ�����
						String oldPassword = et_lostfind_oldpassword.getText().toString();//�����old����
						String newPassword = et_lostfind_newpassword.getText().toString();//������
						if(MD5Utils.MD5Encode(oldPassword).equals(setOldPassword)){ 
							Editor editor = sp.edit();
							editor.putString("password", MD5Utils.MD5Encode(newPassword));
							editor.commit();
							dialog.dismiss();
							UiUtils.showToast(LostFindActivity.this, "�������óɹ�");
						}else{
							UiUtils.showToast(LostFindActivity.this, "���벻ƥ��");
						}
					}
				});
				bt_lostfind_cancel.setOnClickListener(new OnClickListener() {//ȡ��������
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			}else if(R.id.item_howtouse == item.getItemId()){
				AlertDialog.Builder builder = new Builder(this);
				builder.setTitle("���ʹ��").setMessage("���ֹ�����Ҫ������-->��ȫ-->�豸����-->�����ֻ�С��ʿ���豸����Ա���ܣ������޷�ʹ�ã������Ҫж�ش�Ӧ�ã���Ҫ��ȡ���ֻ�С��ʿ���豸����Ա����")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				dialog = builder.show();
			}
			return super.onMenuItemSelected(featureId, item);
		}
		
		
}

