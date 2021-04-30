package com.auto.message.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.auto.message.interceptor.HeaderRequestInterceptor;
@Service
public class PushUtil {

	private static final String FCM_SERVER_KEY = "AAAAN2bBvdY:APA91bFWH5q__qOnYFhTEihz7NhnsUJINvMWjy36MlZB6sgaeuJReTvW2V3YDmh7t0fzQsWpTRr40K4u0bToVG3RgaeJ-bUc30LiDDcd2kWeK2nY0mD1gVBHJCifFboH_tITAkC7b1cD";
	private static final String FCM_SERVER_URL = "https://fcm.googleapis.com/fcm/send";
	
	
	/**
	 * 안드로이드 전용? 모르겠음 
	 * 
	 * @param entity
	 * @return
	 */
	 @Async
	    public CompletableFuture<String> send(HttpEntity<String> entity) {

	        RestTemplate restTemplate = new RestTemplate();

	        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

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
	 public String PeriodicNotificationJson() throws JSONException {
        
		 List<String> 	tokenlist 		= new ArrayList<String>();
		 JSONObject 	body 			= new JSONObject();
		 JSONArray 		array 			= new JSONArray();
		 String 		sampleData[] 	= {"token1"}; // 안드로이드 토큰 받아야해..
		
	     for(int i=0; i<sampleData.length; i++) { 
	    	 tokenlist.add(sampleData[i]); 
		 }
	     
	     for(int i=0; i<tokenlist.size(); i++) {
	    	 array.put(tokenlist.get(i));
	     }  
		  
	     body.put("registration_ids",array);
	     body.put("priority", "high");
	     
	     JSONObject 	notification 	= new JSONObject();
	     
	     notification.put("title","hello!");	// 제목
	     notification.put("body","hello");		// 내용
	     
	     body.put("notification", notification);
	     
	     System.out.println("## body : " +body.toString());
	     System.out.println(body.toString());
	     
	     return body.toString();
    }
	
	 public static void main(String[] args) {
		
		 PushUtil 			pushUtil 		= new PushUtil();
		 String 			notifications 	= pushUtil.PeriodicNotificationJson();
		 HttpEntity<String> req 			= new HttpEntity<String>(notifications);
		 
		 System.out.println("## req : " + req);
		 
		 CompletableFuture<String> pushThread = pushUtil.send(req);
		 CompletableFuture.allOf(pushThread).join();
		 
		 try {
			String rsp = pushThread.get();
			System.out.println("s_response : " + rsp + ",  https_status : "+HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("e_response : " + e + ",  https_status : " + HttpStatus.BAD_REQUEST);
		}
		 
	}
}
