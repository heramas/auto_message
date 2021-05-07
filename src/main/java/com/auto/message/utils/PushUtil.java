package com.auto.message.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.auto.message.interceptor.HeaderRequestInterceptor;

@Service
public class PushUtil {

	private static final Logger log = LoggerFactory.getLogger(PushUtil.class);

	private String FCM_SERVER_KEY;
	private static final String FCM_SERVER_URL = "https://fcm.googleapis.com/fcm/send";
	
	public void setKey(String pushKey) {
		this.FCM_SERVER_KEY = pushKey;
	}
	
	
	/**
	 * 안드로이드 전용? 모르겠음 
	 * 
	 * @param entity
	 * @return
	 */
	 @Async
	 public CompletableFuture<String> send(HttpEntity<String> entity) {
		
		 ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		 RestTemplate 							 restTemplate = new RestTemplate();

		 interceptors.add(new HeaderRequestInterceptor("Authorization",  "key=" + FCM_SERVER_KEY));
		 interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		 restTemplate.setInterceptors(interceptors);
        
		 String firebaseResponse = restTemplate.postForObject(FCM_SERVER_URL, entity, String.class);
        
		 return CompletableFuture.completedFuture(firebaseResponse);
	 }

	 
	 /**
	  * json 형태로 넘겨줘야하기에 만든 것 같다..
	  * 나중에는 토큰,제목, 내용을 따로 파라미터 넘겨주고 받는 걸로 만들어야함.
	  * 
	  * @return
	  * @throws JSONException
	  */
	 public String PeriodicNotificationJson(String toekn, String title, String content) throws JSONException {
        
		 List<String> 	tokenlist 		= new ArrayList<String>();
		 JSONObject 	body 			= new JSONObject();
		 JSONArray 		array 			= new JSONArray();
		 String 		sampleData[] 	= {toekn}; // 안드로이드 토큰 받아야해..
		
	     for(int i=0; i<sampleData.length; i++) { 
	    	 tokenlist.add(sampleData[i]); 
		 }
	     
	     for(int i=0; i<tokenlist.size(); i++) {
	    	 array.put(tokenlist.get(i));
	     }  
		  
	     body.put("registration_ids",array);
	     body.put("priority", "high");
	     
	     JSONObject 	notification 	= new JSONObject();
	     
	     notification.put("title", title);		// 제목
	     notification.put("body" , content);	// 내용
	     
	     body.put("notification", notification);
	     
	     System.out.println("## body : " +body.toString());
	     
	     return body.toString();
    }
	
	 public static void main(String[] args) {
		 
	}
}
