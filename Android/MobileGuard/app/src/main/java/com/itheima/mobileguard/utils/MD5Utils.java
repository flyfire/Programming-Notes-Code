package com.itheima.mobileguard.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * md5����
	 * @param str
	 * @return
	 */
	public static String MD5Encode(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[]	 buf = md.digest(str.getBytes());
			//���� ���ܵ����� ���м���
			StringBuilder sb = new StringBuilder();
			for(byte b:buf){
				int temp =b & 0xFF;
				String hex = Integer.toHexString(temp);
				if(hex.length()==1){
					hex = 0+hex;
				}
				sb.append(hex);
			}
			System.out.println(sb.toString());
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			//���ᷢ�͵��쳣
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ȡ�ļ���md5ֵ
	 * @param path
	 * @return
	 */
	public static String getFileMd5Code(String path){
		try {
			MessageDigest digest =  MessageDigest.getInstance("MD5");
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while((len = fis.read(buf)) != -1){
				digest.update(buf, 0, len);//����
			}
			byte[] result = digest.digest();//����
			StringBuilder sb = new StringBuilder();
			for(byte b : result){
				int i = b & 0xff;
				String hex = Integer.toHexString(i);
				if(hex.length() == 1){
					hex = "0"+hex;
				}
				sb.append(hex);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
