package algorithm.src.week19.p2;

import java.io.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/3613
 * 메모리: 16284 KB
 * 시간: 156 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 구현
 * 대문자와 _를 기준으로 자바와 c++을 구분
 * 1. 변수명의 첫 문자가 소문자이고, 마지막 문자가 _이 아닌지 확인
 * 2. 변수명 인덱스 값 반복문 돌기
 * 2-1. 대문자가 나온 경우, Java로 판단하고 "_소문자"로 변경, 이 때 이전에 C++로 판단되어 있는 경우 Error! 출력
 * 2-2. _이 나온 경우, C++로 판단하고 다음 차례에 문자를 "대문자"로 변경, 이 때 이전에 Java로 판단되어 있는 경우 Error! 출력
 * 2-3. _가 연속 2회 이상 나오는 경우 Error! 출력
 */

public class p2_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = br.readLine();
        String answer = "";
        boolean isJava = false, isCpp = false;
        char firstChar = name.charAt(0), lastChar = name.charAt(name.length() - 1);

        if (!(firstChar >= 'a' && firstChar <= 'z') || lastChar == '_') {
            answer = "Error!";
        } else {
            answer += firstChar;
            for (int i = 1; i < name.length(); i++) {
                char ch = name.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    if (isCpp) {
                        answer = "Error!";
                        break;
                    } else {
                        isJava = true;
                        answer += "_" + (char) (ch + 32);
                    }
                } else if (ch == '_') {
                    if (isJava || name.charAt(i - 1) == '_') {
                        answer = "Error!";
                        break;
                    } else {
                        isCpp = true;
                    }
                } else if (name.charAt(i - 1) == '_') {
                    answer += (char) (ch - 32);
                } else {
                    answer += ch;
                }
            }
        }

        System.out.println(answer);
    }

}