package com.auto.message;

import java.util.Arrays;
import java.util.stream.Stream;

public class algorithmSimple {

	// int 값의 자리수들을 합치는
	static int jarisu(int n) {

		int answer = 0;

		// 1. stream 을 이용한 것
		int result = 0;
		int[] arrNum = Stream.of(String.valueOf(n).split("")).mapToInt(Integer::parseInt).toArray();
		for (int i : arrNum)
			result += i;

		// 2. 알고리즘 설명
		int sum = 0;
		while (n > 0) {
			sum += n % 10;
			n /= 10;
		}
		answer = result;
		answer = sum;

		return answer;
	}

	// 순열검사
	static boolean solution(int[] arr) {
		// sort 이용한 것
//		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
//		System.out.println(arr[arr.length -1] + ", " + arr.length);
//		return arr[arr.length - 1] == arr.length ? true : false;

		// import 없이 배열로
		int n = arr.length; // 길이 5 / 인덱스 0~4
		int[] chk = new int[n + 1]; // 인덱스 0~5 / 길이가 5라서
		for (int i = 0; i < n; ++i) {
			if (arr[i] < 1 || arr[i] > n) // 0 이거나 n(길이=제일큰수)보다 크면 false
				return false;
			chk[arr[i]]++; // 각각의 수를 카운팅하여 chk배열에 넣는다.
		}
		for (int i = 1; i <= n; ++i)
			if (chk[i] != 1) // chk배열에 1 아닌 숫자가 들어가면 false
				return false;
		return true;
	}

	// 직사각형 좌표 구하기
	static int[] solutionA(int[][] v) {
		int[] answer = new int[2];

		for (int i = 0; i < 3; i++) {
			answer[0] ^= v[i][0];
			answer[1] ^= v[i][1];
		}

		return answer;
	}

	// 가장 큰 정사각형 찾기
	static int solutionB(int[][] board) {
		int answer = 0;
		int height = board.length; // 세로길이
		int width = board[0].length; // 가로길이
		int[][] map = new int[height + 1][width + 1]; // 원본에 0 패딩할 배열

		// 배열 복사
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				map[i + 1][j + 1] = board[i][j]; // 왜 1,1 배열에서부터 시작을??
			}
		}

		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				if (map[i][j] != 0) { // 0이 아닌 경우에 한해서, 최소를 찾는다
					map[i][j] = Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]) + 1; // 이건 외워야한다..
					answer = Math.max(answer, map[i][j]); // 최대값 갱신
				}
			}
		}

		return answer * answer;
	}

	// 땅따먹기
	static int solutionC(int[][] land) {

		for (int i = 1; i < land.length; i++) {
			land[i][0] += Math.max(land[i - 1][1], Math.max(land[i - 1][2], land[i - 1][3]));
			land[i][1] += Math.max(land[i - 1][0], Math.max(land[i - 1][2], land[i - 1][3]));
			land[i][2] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][3]));
			land[i][3] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][2]));
		}

		return Math.max(land[land.length - 1][0],
				Math.max(land[land.length - 1][1], Math.max(land[land.length - 1][2], land[land.length - 1][3])));
	}

	// 스티커 모으기
	static int solutionD(int sticker[]) {
		int size = sticker.length;
		int[] dp1 = new int[size]; // 첫 번째 스티커
		int[] dp2 = new int[size]; // 두 번째 스티커

		if (size == 1)
			return sticker[0];

		// 첫 번째 스티커를 뜯는 경우
		dp1[0] = sticker[0];
		dp1[1] = dp1[0];
		for (int i = 2; i < size; i++) {
			dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
		}

		// dp1[n-2]
		// 첫 번째 스티커를 뜯지 않는 경우
		dp2[0] = 0;
		dp2[1] = sticker[1];
		for (int i = 2; i < size; i++) {
			dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
		}

		// dp2[n-1]
		return Math.max(dp1[size - 2], dp2[size - 1]);
	}

	// 단어 퍼즐
	static int solutionE(String[] strs, String t) {

		int answer = Integer.MAX_VALUE;
		int[] dp = new int[t.length()];

		for (int i = 1; i < t.length() + 1; i++) {
			for (int j = 0; j < strs.length; j++) {
				String puzzle = strs[j];
				if (i - puzzle.length() < 0)
					continue;
				if (puzzle.equals(t.substring(i - puzzle.length(), i))) {
					if (i - puzzle.length() == 0) {
						dp[i - 1] = 1;
						continue;
					}
					if (dp[i - puzzle.length() - 1] > 0) {
						dp[i - 1] = dp[i - 1] == 0 ? dp[i - puzzle.length() - 1] + 1
								: Math.min(dp[i - 1], dp[i - puzzle.length() - 1] + 1);
					}
				}
			}
		}
		answer = dp[dp.length - 1];
		if (answer == 0)
			return -1;
		return answer;
	}

	public static void main(String[] args) {
		int[] pro = { 1, 5, 3, 2, 4 };
		boolean a = solution(pro);
		System.out.println("# 순열 검사	: " + a);

		int[][] pro1 = { { 1, 4 }, { 3, 4 }, { 3, 10 } };
		int[] result = solutionA(pro1);
		System.out.println("# 직사각형 좌표	: " + Arrays.toString(result));

		int[][] pro2 = { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 1, 0 } };
		int result2 = solutionB(pro2);
		System.out.println("# 가장 큰 정사각형	: " + result2);

		int[][] pro3 = { { 1, 2, 3, 5 }, { 5, 6, 7, 8 }, { 4, 3, 2, 1 } };
		int result3 = solutionC(pro3);
		System.out.println("# 땅따먹기		: " + result3);

		int[] pro4 = { 14, 6, 5, 11, 3, 9, 2, 10 };
		int result4 = solutionD(pro4);
		System.out.println("# 스티커 모으기	: " + result4);

		String[] pro5 = { "app", "ap", "p", "l", "e", "ple", "pp" };
		String pro6 = "apple";

		int result5 = solutionE(pro5, pro6);
		System.out.println("# 단어 퍼즐 : " + result5);

	}

}
