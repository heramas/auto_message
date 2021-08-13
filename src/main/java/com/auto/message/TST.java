package com.auto.message;

public class TST {

	public String rfn(String num) {
		System.out.println("===================================================");
		double str = System.currentTimeMillis();
		
		String temp = num.replaceAll(" ", "");
		temp 		= temp.replaceAll("-", "");
		
		int 		index 	= 0;
		String 		set 	= "";
		String 		temp2 	= null;
		String[] 	setStr2 = null;
		
		StringBuilder strBuild = new StringBuilder();
		
		if(temp.length() % 3 ==1 ) {
			//두가지로 자른다. 먼저 맨뒤에서 앞4자리를 잘라서 보관하고, 처음부터 맨뒤에서 4번째까지 잘라서 저장한다.
			temp2 = temp.substring(temp.length()-4, temp.length());
			temp = temp.substring(0, temp.length()-4);
			
			//배열로 만들어서 한글자씩 읽는다.
			setStr2 = temp2.split("");
			for (String string : setStr2) {
				set += string;
				index += 1;
				if(index % 2 == 0 && index != setStr2.length) set += "-"; // 나머지 4자리에서 인덱스2 뒤로 (-) 넣는다.
			}
		}
		
		String[] setStr = temp.split("");
		
		index = 0; //초기화
		
		//앞자리 수를 처리하는 과정...
		for (String string : setStr) {
			strBuild.append(string);
			index += 1;
			if(index % 3 == 0 ) strBuild.append("-");
		}
		//앞자리와 뒷자리를 합친다.
		strBuild.append(set);
		
		String result = strBuild.toString();
		
		double end = System.currentTimeMillis();
		System.out.println("시간	: "+(end-str)/1000);
		System.out.println("===================================================");
		return result;
	}
	
	
	//이게 빠르고 더 간결..?
	public String reformatNumber(String number) {
		System.out.println("===================================================");
		double str = System.currentTimeMillis();
		
		StringBuilder sb 	= new StringBuilder();
		StringBuilder ans 	= new StringBuilder();

		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch == ' ' || ch == '-')
				continue;
			sb.append(ch);

		}
		int length 		= sb.length();
		int blockCnt 	= length / 3; // of size 3
		int index;

		if (length % 3 == 1)
			blockCnt -= 1; // 마지막에 4자리가 남았음을 의미합니다.

		System.out.println("총 길이	: " + length);
		System.out.println("블락 길이	: " + blockCnt);

		// 블락길이 만큼 돌아서 처리
		for (index = 1; index <= length && blockCnt > 0; index += 1) {
			ans.append(sb.charAt(index - 1)); // 0 인덱스부터 한글자씩 붙이기

			// 변수 index 가 3이고 총 길이와 같지 않으면 블락길이 빼기
			if (index % 3 == 0 && index != length) {
				ans.append('-');
				blockCnt -= 1;
			}
		}

		System.out.println("인덱스	: " + index);
		System.out.println("중간결과	: " + ans);

		for (int cnt = 1; index <= length; index += 1, cnt += 1) {
			ans.append(sb.charAt(index - 1));
			if (cnt % 2 == 0 && index != length) {
				ans.append('-');
			}
		}
		
		double end = System.currentTimeMillis();
		System.out.println("시간	: "+(end-str)/1000);
		System.out.println("===================================================");
		return ans.toString();
	}
	
	//int[] b = { 7, 4, -2, 4, -2, -9 };
	public int solution(int[] A) {
		if (A.length == 1)
			return 1;
		int even = A[0], odd = A[1];
		int start = 0, max_len = 0;
		for (int i = 2; i < A.length; ++i) {
			System.out.println("even : "+even+", A[i] : " + A[i] + ", odd : " + odd + ", max_len : " + max_len + ", start : " + start);
			if (i % 2 == 0 && A[i] != even || i % 2 == 1 && A[i] != odd) { 
				max_len = Math.max(max_len, i - start);	// max_len 에서  i-start 인덱스 중에서 큰 값 리턴
				start = i - 1;
				if (i % 2 == 0) { // 2,4,6,8 > 0 이니까 even = A[i] 대입, odd = A[i-1] 대입
					even = A[i];
					odd = A[i - 1];
				} else {
					even = A[i - 1];
					odd = A[i];
				}
			}
		}
		return Math.max(max_len, A.length - start);
	}

	// 스킬트리
	public int solution(String skill, String[] skill_trees) {
		int answer = 0;

		for (String skillTree : skill_trees) {
			String tempSkill = skillTree;

			for (int i = 0; i < skillTree.length(); i++) {
				String s = skillTree.substring(i, i + 1);
				if (!skill.contains(s)) {
					tempSkill = tempSkill.replace(s, ""); // 필수 스킬이 아닐 경우 공백으로 치환
				}
			}

			if (skill.indexOf(tempSkill) == 0)
				answer++;
		}

		return answer;
	}
	
	//실패율 1번째
	public int[] failSolution(int N, int[] stages) {
        int[] answer = new int[N];
        double[] tempArr = new double[N];
        int arrLength = stages.length;
        int idx = arrLength;
        double tempD = 0;
        int tempI = 0;
        for (int i = 0; i < arrLength; i++) {
            int stage = stages[i];
            if (stage != N + 1)
                answer[stage - 1] += 1;
        }
        for (int i = 0; i < N; i++) {
            int personNum = answer[i];
            tempArr[i] = (double) personNum / idx;
            idx -= personNum;
            answer[i] = i + 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (tempArr[j - 1] < tempArr[j]) {	// 0.125 < 0.428~
                    tempD = tempArr[j - 1]; 		// tempD = 0.125
                    tempArr[j - 1] = tempArr[j]; 	// 0.125 -> 0.428
                    tempArr[j] = tempD;				// tempArr[1] = 0.125

                    tempI = answer[j - 1];			// tempI = 1
                    answer[j - 1] = answer[j];		// 1 -> 2 (answer[0] = 2 )
                    answer[j] = tempI;				// answer[1] = 1
                }
            }
        }
        return answer;
    }
	
	
	// 실패율 ME
	public int[] fail(int N, int[] stage) {
		
		int[] answer = new int[N];
		double[] per = new double[N];
		int idx = stage.length;
		double tempD = 0;
        int tempI = 0;
        
		for(int i = 0; i < idx; i++) {
			int temp = stage[i];
			if(temp != N + 1) answer[temp-1] += 1;
		}
		for(int i = 0; i < N; i++) {
			int perNum = answer[i];
			per[i] = (double) perNum/idx;
			idx -= perNum;
			answer[i] = i + 1;
		}
		// 퍼센트 제일 큰 값을 정렬시키는 것..
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < N; j++) {
				if(per[j-1] < per[j]) {
					tempD = per[j-1];
					per[j-1] = per[j];
					per[j] = tempD;
					
					tempI = answer[j-1];
					answer[j-1] = answer[j];
					answer[j] = tempI;
				}
			}
		}
		return answer;
	}
	
	//py 개수에 따른 true, false
	boolean solution(String s) {
        boolean answer = true;
        int p = 0;
        int y = 0;
        
        s = s.toLowerCase();
        String[] temp = s.split("");
        for (String getChar : temp) {
			if("p".equals(getChar)) p+=1;
			if("y".equals(getChar)) y+=1;
		}
        if(p==y) answer = true;
        else answer = false;

        return answer;
    }
	public static void main(String[] args) {
		String a = "0 - 22 1985--324";
		int[] b = { 7, 4, -2, 4, -2, -9 };
//		int[] b = { 4, 4, 4, 4, 4, 4 };

		TST tst = new TST();
		String aa = tst.reformatNumber(a);
		int bb = tst.solution(b);
		
		String rfn = tst.rfn(a);
		System.out.println("최종결과	: "+aa);
		System.out.println("슬라이스	: "+bb);
		System.out.println("me	: "+rfn);
		
		boolean aaaaa = tst.solution("Pyy");
		System.out.println("true?false : "+aaaaa);
		
		int[] send = {2, 1, 2, 6, 2, 4, 3, 3};
//		int[] isFail = tst.failSolution(4, send);
		int[] isFail = tst.fail(5, send);
		for (int i : isFail) {
			System.out.println("? : "+i);
		}
	}

}
