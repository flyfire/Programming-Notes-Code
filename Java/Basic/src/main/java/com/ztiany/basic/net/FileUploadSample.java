package com.ztiany.basic.net;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟浏览器文件上传,   http://blog.csdn.net/lmj623565791/article/details/23781773
 *
 * @author Ztiany
 *         Date : 2016-12-03 22:11
 */

public class FileUploadSample {


    public static void main(String[] args) throws Exception {

        FileUploadEx fileUploadEx = new FileUploadEx();
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "123");

        Map<String, File> files = new HashMap<String, File>();

        files.put("haha1", new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
        files.put("haha2", new File("C:\\Users\\Administrator\\Desktop\\2.jpg"));
        files.put("haha3", new File("C:\\Users\\Administrator\\Desktop\\3.jpg"));
        files.put("haha4", new File("C:\\Users\\Administrator\\Desktop\\4.jpg"));


        try {
            fileUploadEx.uploadForm(params, files);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e);
        }

    }


    private static class FileUploadEx {

        static final String BOUNDARY = "----WebKitFormBoundarynVJqhWCwnVwIPu8C";


        void uploadForm(Map<String, String> params, Map<String, File> fileMap)
                throws Exception {


            URL url = new URL("http://192.168.1.117:8080/day21FileUpload/servlet/RegistServlet");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = urlConnection.getOutputStream();


            //普通表单
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("--" + BOUNDARY + "\r\n");
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\""
                        + "\r\n");
                sb.append("\r\n");//空行
                sb.append(entry.getValue() + "\r\n");
            }

            //写key 和 value
            out.write(sb.toString().getBytes("UTF-8"));

            //写文件
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {

                sb = new StringBuilder();
                sb.append("--" + BOUNDARY + "\r\n");
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey()
                        + "\"; filename=\"" + entry.getValue().getName() + "\"" + "\r\n");
                sb.append("Content-Type: image/jpeg" + "\r\n");// 如果服务器端有文件类型的校验，必须明确指定ContentType
                sb.append("\r\n");
                System.out.println(sb.toString());
                out.write(sb.toString().getBytes("UTF-8"));

                out.write(getBytes(entry.getValue()));//写文件
                out.write("\r\n".getBytes());//需要一个空行
            }


            //写结尾信息
            byte[] endInfo = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
            out.flush();

            out.write(endInfo);
            out.close();
            if (urlConnection.getResponseCode() == 200) {
                System.out.println("haha - success");
            }
        }
    }


    //把文件转换成字节数组
    private static byte[] getBytes(File f) throws Exception {
        FileInputStream in = new FileInputStream(f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
        in.close();
        return out.toByteArray();
    }


}

