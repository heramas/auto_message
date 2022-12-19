package com.auto.message;

import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

public class dnatest {

	public static void main(String[] args) {
		
		Random ran = new Random();
		
		String param_site = "AFREECATV";
		String video_id = "";
		
		Vector<Hashtable<String, String>> vecVideoId = new Vector<Hashtable<String, String>>();
		int a = 1;
		while(a<3) {
			Hashtable<String, String> ht = new Hashtable<String, String>();
			
			ht.put("VIDEO_ID", "1234"+ran.nextInt(9));
			ht.put("CNT", ""+ran.nextInt(4));
			vecVideoId.add(ht);
			a++;
		}
		System.out.println(vecVideoId.toString());
		
		boolean video_id_sch = true;
        if (vecVideoId.size() == 1) {
        	System.out.println("확인1");
            for (int i = 0; i < vecVideoId.size(); i++) {
                Hashtable<String, String> data = (Hashtable<String, String>) vecVideoId.get(i);
				
                //("AFREECATV".equals(param_site) && Integer.parseInt((String) data.get("CNT")) >= 3 )
                if (( !"AFREECATV".equals(param_site) && Integer.parseInt((String) data.get("CNT")) >= 2 ) ||
                	(  "AFREECATV".equals(param_site) && Integer.parseInt((String) data.get("CNT")) >= 3 ) ) {
                    video_id = (String) data.get("VIDEO_ID");
                    video_id_sch = false;
                }
            }
        }

        // AABXX -> A , AABCD -> A , AABCX -> A , AABBC -> X , AABBX -> X
        if (video_id_sch && vecVideoId.size() >= 2) {
        	System.out.println("확인2");
            // 2 이상인 값이 2개 이상이면 비교 하지 않는다. AAABB 같은 경우는 맨 아래에서 체크 되므로 여기선 패스
            int dubleCnt = 0;
            for (int i = 0; i < vecVideoId.size(); i++) {
                Hashtable<String, String> data = (Hashtable<String, String>) vecVideoId.get(i);

                if (Integer.parseInt((String) data.get("CNT")) >= 2) {
                    dubleCnt++;
                }
            }
            System.out.println("cnt : "+dubleCnt);
            // value 값 2이상이 1개일때만 실행
            if (dubleCnt < 2) {
            	System.out.println("확인3");
                for (int i = 0; i < vecVideoId.size(); i++) {
                    Hashtable<String, String> data = (Hashtable<String, String>) vecVideoId.get(i);

                    if (( !"AFREECATV".equals(param_site) && Integer.parseInt((String) data.get("CNT")) >= 2 ) ||
                        (  "AFREECATV".equals(param_site) && Integer.parseInt((String) data.get("CNT")) >= 3 ) ) {
                        video_id = (String) data.get("VIDEO_ID");
                        video_id_sch = false;
                    }
                }
            }
        }

        // ( AAAAA, AAAAX , AAAXX , AAABX , AAABB => A)
        if (video_id_sch) {
        	System.out.println("확인4");
            for (int i = 0; i < vecVideoId.size(); i++) {
                Hashtable<String, String> data = (Hashtable<String, String>) vecVideoId.get(i);

                if (Integer.parseInt((String) data.get("CNT")) >= 3) {
                    video_id = (String) data.get("VIDEO_ID");
                }
            }
        }
        
        System.out.println("video_id : " + video_id);

	}

}
