package com.itheima.mobileguard.activities;

import com.itheima.mobileguard.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class LostSetup4Activity extends BaseLostSetupActivity {
	private CheckBox cb_setup4;
	private TextView tv_setup4;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_lostfindsetup4);
			
			cb_setup4 = (CheckBox) findViewById(R.id.cb_setup4);
			tv_setup4 = (TextView) findViewById(R.id.tv_setup4);
			
			boolean protecting = sp.getBoolean("protecting", false);
			if(protecting){//������
				tv_setup4.setText("�ֻ������Ѿ�����");
				cb_setup4.setChecked(true);
			}else{
				tv_setup4.setText("�ֻ�����û�п���");
				cb_setup4.setChecked(false);
			}
			
			//ע�Ḵѡ������¼�
			cb_setup4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							tv_setup4.setText("�ֻ������Ѿ�����");
						}else{
							tv_setup4.setText("�ֻ�����û�п���");
						}
						Editor editor = sp.edit();
						editor.putBoolean("protecting", isChecked);
						editor.commit();
				}
			});
		}

		/**
		 * ������ý��뵽����
		 */
		public void showNext() {
			//�����û��Ƿ������˿������� ����ϵͳдһ��������Ϣ �û���������� ���Խ����������
			Editor editor = sp.edit();
			editor.putBoolean("finishing", true);
			editor.commit();
			startActivityAndFinishSelf(LostFindActivity.class);
		}
		

		/**
		 * ������һ��Activity
		 */
		@Override
		public void showPrevious() {
				startActivityAndFinishSelf(LostSetup3Activity.class);
		}
}
