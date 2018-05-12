package com.itheima.mobileguard.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.itheima.mobileguard.R;

public abstract class BaseLostSetupActivity extends Activity {
	/**
	 * ����̽���� 
	 */
	private GestureDetector gd;
	protected SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				//ˮƽ������һ��ʱ���ڱ仯�� ������ȷ������
				//e1  ������ָ��һ�δ�����Ļ���¼�
				//e2 ������ָ�뿪��Ļһ˲����¼�
				//velocityX ˮƽ������ٶ� ��λ  pix/s
				//velocityY ��ֱ������ٶ�
				if(Math.abs(velocityX)<200){//����С������
					Toast.makeText(BaseLostSetupActivity.this, "��Ч������,̫����", Toast.LENGTH_LONG).show();
					return true;
				}else if((e2.getRawX()-e1.getRawX())>200){//�������� ��ĻҪ����
					showPrevious();
					overridePendingTransition(R.anim.previous_in_translate, R.anim.previous_out_translate);
					return true;
				}else if((e1.getRawX()-e2.getRawX())>200){//�������� ��Ļ����
					showNext();
					overridePendingTransition(R.anim.next_in_translate, R.anim.next_out_translate);
					return true;//����true ��ʾ�¼��������
				}
				return super.onFling(e1, e2, velocityX, velocityY);
			}
		});
	}
	/**
	 * 3.������ʶ����ȥʶ���¼�
	 */
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			gd.onTouchEvent(event);//����ʶ���� �����¼� 
			return super.onTouchEvent(event);
		}
	
	/**
	 * ������һ�����ý���
	 * @param v
	 */
	public void previous(View v){
		showPrevious();
		overridePendingTransition(R.anim.previous_in_translate, R.anim.previous_out_translate);
	}
	public abstract void showPrevious();
	/**
	 * ����¼� ������һ��ҳ��
	 * @param view
	 */
	public void next(View view){
		showNext();
		overridePendingTransition(R.anim.next_in_translate, R.anim.next_out_translate);
	}
	/**
	 * ������ʵ�־��嵽�ĸ�Activity
	 */
	public abstract void showNext();
	/**
	 * �����µ�Activity �������Լ�
	 * @param clazz
	 */
	public void startActivityAndFinishSelf(Class<?> clazz){
		Intent intent = new Intent(this,clazz);
		startActivity(intent);
		finish();
	}
}
