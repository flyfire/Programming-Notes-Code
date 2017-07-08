package com.itheima.mobileguard.activities;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.itheima.mobileguard.R;

public class LostSetup2Activity extends BaseLostSetupActivity {
		private TelephonyManager tm;
		private ImageView iv_setup2;
		private String simNumber;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_lostfindsetup2);
			//��ȡͨ������
			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			simNumber = tm.getSimSerialNumber();
			iv_setup2 = (ImageView) findViewById(R.id.iv_setup2);
			//�ж��û�ʹ�����ð���SIM
			String sim = sp.getString("sim", "");
			//���½���
			if(TextUtils.isEmpty(sim)){//û�а�sim
				iv_setup2.setImageResource(R.drawable.unlock);
			}else{//�󶨹���
				iv_setup2.setImageResource(R.drawable.lock);
			}
		}
		
		public void bindSIMCrad(View v){
			String sim = sp.getString("sim", "");
			if(TextUtils.isEmpty(sim)){//û�а�sim
				Editor editor = sp.edit();
				if(simNumber == null){
					simNumber = "1KS932SL9DLJ099";
				}
				editor.putString("sim",simNumber);
				editor.commit();
				iv_setup2.setImageResource(R.drawable.lock);
				Toast.makeText(this, "�󶨳ɹ�", Toast.LENGTH_SHORT).show();
			}else{//�󶨹���
				Editor editor = sp.edit();
				editor.putString("sim","");
				editor.commit();
				iv_setup2.setImageResource(R.drawable.unlock);
				Toast.makeText(this, "���ɹ�", Toast.LENGTH_SHORT).show();
			}
		}
		
		
		@Override
		protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		}
		@Override
		protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		}
		
		/**
		 * ������һ������
		 */
		@Override
		public void showNext() {
//			�ж��û��Ƿ����sim�� û�в������ܵ���һ������
			String sim = sp.getString("sim", "");
			if(TextUtils.isEmpty(sim)){
				Toast.makeText(this, "���Ȱ�SIM��", Toast.LENGTH_LONG).show();
				return;
			}
			startActivityAndFinishSelf(LostSetup3Activity.class);
		}

		/**
		 * ������һ��Activity
		 */
		@Override
		public void showPrevious() {
				startActivityAndFinishSelf(LostSetup1Activity.class);
		}
}
