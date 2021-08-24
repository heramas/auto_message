package com.auto.message.algori;

import java.util.HashSet;
import java.util.stream.Stream;

public class altest {

	public int[] solution(int n, String[] words) {
		int[] answer = { 0, 0 };
		char wordStart;
		char wordEnd = words[0].charAt(words[0].length() - 1);
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add(words[0]); // 첫단어를 해쉬셋에 저장
		for (int i = 1; i < words.length; i++) {
			hashSet.add(words[i]);
			wordStart = words[i].charAt(0);
			if (wordEnd != wordStart || hashSet.size() != i + 1) {
				answer[0] = (i % n) + 1;
				answer[1] = (i / n) + 1;
				break;
			}
			wordEnd = words[i].charAt(words[i].length() - 1);
		}
		return answer;
	}

	public String solution(String p) {
		return recursive(p);
	}

	public String recursive(String p) {
		if (p == "") { // 1
			return "";
		}
		int countCheck = 0;
		boolean isCorrect = true;
		String u = "";
		String v = "";
		for (int i = 0; i < p.length(); i++) { // 2번 문자열 분리
			if (p.charAt(i) == '(') {
				countCheck++;
			} else {
				countCheck--;
			}
			if (countCheck < 0) { // 올바르지 않음을 판단
				isCorrect = false;
			}
			if (countCheck == 0) {
				u = p.substring(0, i + 1);
				v = p.substring(i + 1); // 오류 안남
				break;
			}
		}

		if (isCorrect) {
			return u + recursive(v);
		} else {
			String temp = "("; // 4-1
			temp += recursive(v) + ")"; // 4-2 4-3
			u = u.substring(1, u.length() - 1); // 4-4
			char[] tempU = u.toCharArray();
			for (int i = 0; i < tempU.length; i++) {
				if (tempU[i] == '(') {
					tempU[i] = ')';
				} else {
					tempU[i] = '(';
				}
			}
			u = String.valueOf(tempU);
			return temp + u;
		}

	}

	// ME
	public String mSolution(String p) {
		String answer = "";
		
		return answer;
	}

	public static void main(String[] args) {
		String[] word = { "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank" };

		altest t = new altest();
		int[] a = t.solution(3, word);
		for (int i : a) {
			System.out.println(i);
		}

		String resultS = t.solution("()))((()");
		System.out.println(resultS);
		
		int num = 1234;
		int result = 0;
		int[] arrNum = Stream.of(String.valueOf(num).split("")).mapToInt(Integer::parseInt).toArray();
		for (int i : arrNum) {
			result += i;
		}
		
		int sum = 0;
		while(num > 0) {
			sum+=num%10;
			num/=10;
		}
		System.out.println(result);
		System.out.println(sum);
		


	}
}
