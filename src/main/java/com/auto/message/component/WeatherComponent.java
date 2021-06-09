package com.auto.message.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component
public class WeatherComponent {

	
	public String getWeather( ) throws IOException {
		
		String result = "";
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtNcst");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); 			/*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") 	+ "=" + URLEncoder.encode("1", "UTF-8")); 			/*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8")	+ "=" + URLEncoder.encode("10", "UTF-8")); 			/*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") 	+ "=" + URLEncoder.encode("XML", "UTF-8")); 		/*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") 	+ "=" + URLEncoder.encode("20151201", "UTF-8")); 	/*15년 12월 1일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") 	+ "=" + URLEncoder.encode("0600", "UTF-8")); 		/*06시 발표(정시단위)*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") 		+ "=" + URLEncoder.encode("18", "UTF-8")); 			/*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") 		+ "=" + URLEncoder.encode("1", "UTF-8")); 			/*예보지점 Y 좌표*/
		return result;
	}
	
	public String getWeekAir() throws IOException {
		
		String result = "";
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8")  + "=서비스키"); 												/*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8")  + "=" + URLEncoder.encode("인증키(URL Encode)", "UTF-8")); 	/*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8")  + "=" + URLEncoder.encode("xml", "UTF-8")); 				/*xml 또는 json*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") 	 + "=" + URLEncoder.encode("100", "UTF-8")); 				/*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") 	 + "=" + URLEncoder.encode("1", "UTF-8")); 					/*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("stationName","UTF-8") + "=" + URLEncoder.encode("종로구", "UTF-8")); 				/*측정소 이름*/
        urlBuilder.append("&" + URLEncoder.encode("dataTerm","UTF-8") 	 + "=" + URLEncoder.encode("DAILY", "UTF-8")); 				/*요청 데이터기간(1일: DAILY, 1개월: MONTH, 3개월: 3MONTH)*/
        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") 		 + "=" + URLEncoder.encode("1.0", "UTF-8")); 				/*버전별 상세 결과 참고*/
		
		URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
		
		return result;
	}
	
	public String getAir() throws UnsupportedEncodingException {
		String result = "";
		
		return result;
	}
	
}
