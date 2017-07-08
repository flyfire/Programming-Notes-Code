package com.itheima.mobileguard.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class StreamUtils {
	/**
	 * 
	 * @param in ������
	 * @return �ַ��� ������������
	 * @throws IOException 
	 */
	public static String getString(InputStream in) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = 0;
		byte[] buf = new byte[1024];
		while((len = in.read(buf))!=-1){
			bos.write(buf,0,len);
		}
		in.close();
		buf = bos.toByteArray();
		bos.close();
		return new String(buf);
	}
}
