package com.auto.message.algori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class fourCodeTest {

	/**
	 * 1. languages와 preference는 idx가 동일하여 map에 담았다.
	 * 2. table 배열의 길이만큼 for문 돌아서 split을 이용해 자르기
	 * 3. 자른 get 배열 길이만큼 다시 for문 돌아서 temp map에 담는다.
	 * 4. 담고 나면 reSet map에 담은 내용을 temp map 내용과 비교를 하여 true인 경우에 
	 * value 값을 곱하여 tempI 변수에 담는 과정을 진행한다.
	 * 5. tempSet에 map 담은 것 중 value가 6인 것을 찾아 tempI(곱한 것을 업데이트한다)
	 * 6. list 선언하여 정렬해서 idx 0을 뽑는다.
	 * 7. tempSet에 업데이트한 것 중 list idx 0 인 최고값을 넣어서 찾아 리턴한다.
	 * 
	 * @param table
	 * @param languages
	 * @param preference
	 * @return
	 */
	static String solution(String[] table, String[] languages, int[] preference) {
        
		String 				 answer 	= "";
        List<Integer> 		 listTemp 	= new ArrayList<>();
        Map<String, Integer> reSet 		= new HashMap<>();
        Map<String, Integer> tempSet 	= new TreeMap<>();
        
        for (int i = 0; i < languages.length; i++) {
			reSet.put(languages[i], preference[i]);
		}
        
        for (int i = 0; i < table.length; i++) {
        	int tempI = 0;
        	Map<String, Integer> temp = new HashMap<>();
        	String[] get = table[i].split(" ");
        	
        	for (int j = 0, m = 6; j < get.length; ++j,m--) {
				temp.put(get[j], m);
			}
        	
        	for (Entry<String, Integer> string : reSet.entrySet()) {
				if(temp.containsKey(string.getKey())) {
					
					int getI = temp.get(string.getKey()).intValue() * string.getValue();
					tempI += getI;
				}
			}
        	tempSet.put(getKey(temp,6), tempI);
        	
        	temp.put(getKey(temp,6), tempI);
        	listTemp.add(tempI);
        	Collections.sort(listTemp, Collections.reverseOrder());
        }
        
        
        answer = getKey(tempSet,listTemp.get(0));

		return answer;
    }
	public static <K, V> K getKey(Map<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }
	
	 static String solution1(String[] table, String[] languages, int[] preference) {
	        String answer = "";
	        int score=-1;
	        for(String str : table){
	            String[] t = str.split(" ");
	            String tname = t[0];
	            int tscore = 0;
	            for(int i=0;i<languages.length;i++){
	                int idx = Arrays.asList(t).indexOf(languages[i]);
	                if(idx>-1) tscore+=preference[i]*(6-idx);
	            }
	            System.out.println("비교 : " + answer.compareTo(tname));
	            // 점수가 같고 문자열을 비교하여 0보다 크면 바꿔주고...참
	            if(score ==tscore && answer.compareTo(tname)>0) answer=tname;
	            if(score<tscore){
	                score =tscore;
	                answer= tname;
	            }
	            System.out.println("score : " + score);
	            System.out.println("tscore : " + tscore);
	            System.out.println("tname : " + tname);
	            System.out.println("answer : " + answer);
	            System.out.println("=============================");
	        }
	        return answer;
	    }
	
	public static void main(String[] args) {
	
		String[] pro1 = {"SI JAVA JAVASCRIPT SQL PYTHON C#", 
						 "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", 
						 "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", 
						 "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", 
						 "GAME C++ C# JAVASCRIPT C JAVA"};
		String[] pro2 = {"JAVA", "JAVASCRIPT"};
		int[]	 pro3 = {2, 2};
		
		//결과 HARDWARE 가 나와야함
		String result = solution1(pro1, pro2, pro3);
		System.out.println(result);
	}
}
