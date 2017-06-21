package com.gray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import junit.framework.TestCase;


public class ClientTest extends TestCase {

	//@Test
	public void testMain(){
		System.out.println(doPost("http://localhost:8090/test/dologin.do","username=admin&password=admin","UTF-8"));
	}
	public String doPost(String connectURL, String param, String charset) {
	    byte[] bytes = null;
	    ByteArrayOutputStream byteArrayOut = null;
	    URL url = null;
	    HttpURLConnection httpPost = null;
	    OutputStream out = null;
	    InputStream in = null;
	    try {
	        url = new URL(connectURL);
	        httpPost = (HttpURLConnection) url.openConnection();
//	        httpPost.setRequestMethod("GET");
//	        httpPost.setConnectTimeout(5*1000);
	        httpPost.setRequestMethod("POST");
	        httpPost.setDoInput(true);
	        httpPost.setDoOutput(true);
	        httpPost.setUseCaches(false);
	        httpPost.setConnectTimeout(1000);
	        httpPost.setReadTimeout(2000);
	        httpPost.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        httpPost.connect();
	        out = httpPost.getOutputStream();
	        out.write(param.getBytes(charset));
	        out.flush();
	        in = httpPost.getInputStream();
	        byteArrayOut = new ByteArrayOutputStream();
	        byte[] buf = new byte[512];
	        int l = 0;
	        while ((l = in.read(buf)) != -1) {
	            byteArrayOut.write(buf, 0, l);
	        }
	        bytes = byteArrayOut.toByteArray();
	        return new String(bytes, charset);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
				byteArrayOut.close();
				out.close();
				in.close();
				httpPost.disconnect();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    }
	    return null;
	}
	//@Test
	public void sendPost(){
		try {
			// ��Ҫ�����restful��ַ
			URL url = new URL("http://localhost:9090/profile/ws/emp");

			// ��restful����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// �ύģʽ
			conn.setRequestMethod("POST");// POST GET PUT DELETE

			// ���÷����ύģʽ�����ύ
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			conn.setConnectTimeout(10000);// ���ӳ�ʱ ��λ����
			conn.setReadTimeout(2000);// ��ȡ��ʱ ��λ����

			conn.setDoOutput(true);// �Ƿ��������

			StringBuffer params = new StringBuffer();
	        params.append("id").append("=").append("TY001");
			byte[] bypes = params.toString().getBytes();
			conn.getOutputStream().write(bypes);
			// ��ȡ���󷵻�ֵ
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
