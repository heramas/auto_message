package com.auto.message.component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class SearchComponent {
	/*
	 *요청 변수
		  query 	| String 	| Y | - 					| 검색을 원하는 문자열로서 UTF-8로 인코딩한다.
		  display 	| integer 	| N | 10(기본값), 100(최대) 	| 검색 결과 출력 건수 지정
		  start		| integer	| N | 1(기본값), 1000(최대)	| 검색 시작 위치로 최대 1000까지 가능
		  sort		| String	| N | sim(기본값),date		| 정렬 옵션 : sim(유사도순), date(날짜순) 
		 
	 *에러 코드
		  400	SE01	Incorrect query request (잘못된 쿼리요청입니다.)			검색 API 요청에 오류가 있습니다. 요청 URL, 필수 요청 변수가 정확한지 확인 바랍니다.
		  400	SE02	Invalid display value 	(부적절한 display 값입니다.)		display 요청 변수값이 허용 범위(1~100)인지 확인해 보세요.
		  400	SE03	Invalid start value 	(부적절한 start 값입니다.)		start 요청 변수값이 허용 범위(1~1000)인지 확인해 보세요.
		  400	SE04	Invalid sort value 		(부적절한 sort 값입니다.)		sort 요청 변수 값에 오타가 없는지 확인해 보세요.
		  400	SE06	Malformed encoding 		(잘못된 형식의 인코딩입니다.)		검색어를 UTF-8로 인코딩하세요.
		  404	SE05	Invalid search api 		(존재하지 않는 검색 api 입니다.)	검색 API 대상에 오타가 없는지 확인해 보세요.
		  500	SE99	System Error			(시스템 에러)					서버 내부 에러가 발생하였습니다. 포럼에 올려주시면 신속히 조치하겠습니다.
	 */

	private static final String LOCAL_URL = "https://openapi.naver.com/v1/search/blog?query=";
	
	public String search(String cid, String cSecret, String getUrl) {
		
		HttpURLConnection 	conn 	= null;
		String 				result 	= "";
		
		
		try {
			
			URL url = new URL(LOCAL_URL + getUrl + "&display=20");
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id"		, cid);
			conn.setRequestProperty("X-Naver-Client-Secret"	, cSecret);
			
			int responseCode = conn.getResponseCode();
			
			if( responseCode == HttpURLConnection.HTTP_OK ) result = readBody(conn.getInputStream());
			else result = readBody(conn.getErrorStream());
			
		} catch (Exception e) {
			log.info("연결 오류 : [{}]",e.toString());
		} finally {
			conn.disconnect();
		}
		
		return result; 
	}
	
	public String readBody(InputStream body) {
		
		InputStreamReader streamReader = new InputStreamReader(body);
		
		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			
			StringBuilder responseBody = new StringBuilder();
			String line;
			
			while((line = lineReader.readLine()) != null ) {
				responseBody.append(line);
			}
			
			return responseBody.toString();
		
		} catch (Exception e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}
