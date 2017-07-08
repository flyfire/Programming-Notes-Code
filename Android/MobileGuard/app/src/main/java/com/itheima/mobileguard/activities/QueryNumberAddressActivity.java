package com.itheima.mobileguard.activities;


import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.db.dao.NumberAddressDao;

public class QueryNumberAddressActivity extends Activity {
		private EditText et_number;
		private TextView tv_result;
		//�𶯷���
		private Vibrator vibrator;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_querynumberaddress);
			
			et_number = (EditText) findViewById(R.id.et_querynumberaddress);
			tv_result = (TextView) findViewById(R.id.tv_querynumberaddress);
			//�õ�ϵͳ�𶯷���
			vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			//���ݱ仯�ļ����¼�
			et_number.addTextChangedListener(new TextWatcher() {
				
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					String number = s.toString();
					if(TextUtils.isEmpty(number)){
						tv_result.setText("");
						return ;
					}
					String address = NumberAddressDao.query(number, QueryNumberAddressActivity.this);
					tv_result.setText(address);
				}
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					
				}
				public void afterTextChanged(Editable s) {
					
				}
			});
		}
		
		
		public void query(View v){
			String number = et_number.getText().toString().trim();
			if(TextUtils.isEmpty(number)){
				Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
				
			/*	//�Զ��嶯��������
				shake.setInterpolator(new Interpolator() {
					public float getInterpolation(float input) {
						return 0;
					}
				});*/
				
				
		        et_number.startAnimation(shake);
				Toast.makeText(this, "���������", Toast.LENGTH_LONG).show();
				
				//ʵ���ֻ��� ��ҪȨ�� �𶯶�����  ͣ�¶�����  �ظ����ٴ�
			//	vibrator.vibrate(new long[]{200,300,100,400,440,420}, 2);
			
				/*<uses-permission android:name="android.permission.VIBRATE"/>
				 * ��Щ���̵��ֻ���������û��ʵ������API 
				 * ֻʵ���� 	vibrator.vibrate(milliseconds); �����Ҫʵ�ֶ����
				 * �������ö��߳������
				 */
				
				return ;
			}
			String address = NumberAddressDao.query(number, this);
			tv_result.setText(address);
		}
		
}
