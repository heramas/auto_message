package com.auto.message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.gson.Gson;

public class shinhantest {

	public static void main(String[] args) {
		
		Gson gson = null;
		BufferedReader in = null;
		BufferedReader ins = null;
		long start, end = 0;
		
		try {
			start = System.currentTimeMillis();
			URL url = new URL("http://121.254.128.235/?sfilter={\"path\":\"D:\\\\테스트_영상.mp4\"}");
			URL urls = new URL("https://121.254.128.235/?sfilter={\"path\":\"D:\\\\테스트_영상.mp4\"}");
			ignoreSsl();
//			URL url = new URL("https://172.16.70.20:8080/?sfilter={\"path\":\"C:\\\\샘플\\\\디지털성범죄물_테스트영상.mp4\"}");
//			URL url = new URL("http://172.16.70.20:8080/?sfilter={\"path\":\"C:\\\\샘플\\\\디지털성범죄물_테스트영상.mp4\"}");
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			HttpsURLConnection cons = (HttpsURLConnection) urls.openConnection();
			cons.setRequestMethod("GET");
			
			String line;
			String lines;
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			ins = new BufferedReader(new InputStreamReader(cons.getInputStream(), "UTF-8"));
			
			line = in.readLine();
			lines = ins.readLine();
			
			System.out.println("line : " + line);
			System.out.println("lines : " + lines);
//			
//			gson = new Gson();
//			Map<String,Object> map = gson.fromJson(line, Map.class);
//			
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//				System.out.println(entry.getKey() + "=" + entry.getValue());
//			}
			
			end = System.currentTimeMillis();
			System.out.println("시간경과 : ["+(end-start)/1000+"]초");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		

	}
	
	public static void ignoreSsl() throws Exception{
        HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
 
    static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
 
        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }
 
        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }
 
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			return;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			return;
		}

    }


}
