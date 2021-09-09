package com.auto.message.algori;

import java.util.Arrays;

public class fiveCodeTest {

	static int solution(String word) {
		// 5*5*5*5*5 + 5*5*5*5 + 5*5*5 + 5*5 + 5 / 5 = 781
		// 3125 + 625 + 125 + 25 + 5 = 3905 / 5 = 781

		// AEIOU 길이가 5 인 경우
		// idx 0 에서 올 수 있는 경우의 수 = 5
		// idx 1 에서 올 수 있는 경우의 수 = 5 * 5
		// idx 2 에서 올 수 있는 경우의 수 = 5 * 5 * 5
		// idx 3 에서 올 수 있는 경우의 수 = 5 * 5 * 5 * 5
		// idx 4 에서 올 수 있는 경우의 수 = 5 * 5 * 5 * 5 * 5

		int answer = 0, per = 3905;
		String[] temp = word.split("");
		for (int i = 0; i < word.length(); i++) {
			answer += "AEIOU".indexOf(temp[i]) * (per /= 5) + 1;
			System.out.println(answer);
		}

		return answer;
	}

	static int solution1(String word) {
		int answer = 0, per = 3905;
		for (String s : word.split(""))
			answer += "AEIOU".indexOf(s) * (per /= 5) + 1;
		return answer;
	}

	static String solution3(String s) {
		String answer = "";
		String[] set = s.split(" ");
		
		for (int j = 0; j < set.length; j++ ) {
			String tempS = "";
			String[] temp = set[j].split("");
			for (int i = 0; i < temp.length; i++) {
				if (i % 2 == 0) {
					temp[i] = temp[i].toUpperCase();
				}
				tempS += temp[i];
			}
			if(j == set.length-1) answer += tempS;
			else answer += tempS + " ";
		}

		return answer;
	}

	/**
		A. 테스트 케이스
		
		공백 두 개 이상 오는 경우
		공백으로 끝나는 경우(띄어쓰기 두 개로 끝나기도 함)
		공백 두 개 이상으로 끝나는 경우
		소문자로 시작하는 경우 등 고려해 보세요.
		　
		B. 비트 연산자로 일반화했습니다. 다른 팁도 공부가 된다면 많이 말씀해 주세요!
		
		ㄱ. chArr[i] & 0xDF 하면 대문자가 됨.(chArr[i] & 0000 0000 1101 1111)
		　　대문자 'A'는 0000 0000 0100 0001
		　　소문자 'a'는 0000 0000 0110 0001
		ㄴ. (cnt&1)<<5 하면 cnt가 짝수일 때는 0, 홀수일 때는 32가 됨.
		ㄷ. 가령 'A' | 32 하면 소문자, 'A' | 0 하면 대문자 그대로.
		
		(이 코드를 참고하세요. 단, cnt를 1로 초기화했습니다.)
		
	 * @param s
	 * @return
	 */
	static String solution4(String s) {
		char[] chArr = s.toCharArray();
		
		System.out.println("char : "+Arrays.toString(chArr));
		
        for (int i=0, cnt=1; i<chArr.length; i++) {
            cnt += (((chArr[i] & 0x40) >>> 6)-1) * cnt;
            System.out.println("cnt : " + cnt );
            chArr[i] = (char) (chArr[i] & 0xDF | (~cnt++&1)<<5);
            System.out.println("result char : "+Arrays.toString(chArr));
        }
        return new String(chArr);
	}

	public static void main(String[] args) {

		String word = "UIA";
		int result = solution(word);
		System.out.println("결과 : " + result);

		String word2 = "try hello world ";
		String result2 = solution4(word2);

		System.out.println("결과2 : _" + result2 + "_");

	}

}
