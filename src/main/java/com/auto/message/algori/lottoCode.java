package com.auto.message.algori;

import java.util.Arrays;

public class lottoCode {

	static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int count = 0, zeroCount = 0;
        
        for (int i = 0; i < lottos.length; i++) {
        	for (int j = 0; j < win_nums.length; j++) {
				if(lottos[i] == win_nums[j]) {
					count++;
					zeroCount++;
//					System.out.println("lotto["+i+"] : "+ lottos[i] + ", win_num["+j+"] : " + win_nums[j] + ", count : " + count + ", zero : " + zeroCount);
				}
			}
        	if(lottos[i] == 0) {
        		count++;
        		System.out.println(count + ", " + zeroCount);
        	}
		}
        answer[0] = count;
        answer[1] = zeroCount;

        System.out.println(Arrays.toString(answer));
        
        for(int i = 0; i < answer.length; i++) {
        	switch (answer[i]) {
			case 6: answer[i] = 1; break;  
			case 5: answer[i] = 2; break;
			case 4: answer[i] = 3; break;
			case 3: answer[i] = 4; break;
			case 2: answer[i] = 5; break;
			default:
				answer[i] = 6; break;
			}
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		
		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		
		int[] result = solution(lottos, win_nums);
		
		System.out.println(Arrays.toString(result));
	}

}
