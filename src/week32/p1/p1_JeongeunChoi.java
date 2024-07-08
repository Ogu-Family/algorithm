package algorithm.src.week32.p1;

import java.io.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/27649
 * 메모리: 92100 KB
 * 시간: 644 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 구현
 * 구분자를 발견하기 전까지 토큰을 만들고, 구분자 발견 시 토큰을 추출한다.
 */

public class p1_JeongeunChoi {

    private static final StringBuilder afterTokenization = new StringBuilder();

    private static void appendToken(StringBuilder token) {
        if (token.length() > 0) {
            afterTokenization.append(token + " ");
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String origin = br.readLine();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < origin.length(); i++) {
            if (origin.charAt(i) == '<' || origin.charAt(i) == '>' || origin.charAt(i) == '(' || origin.charAt(i) == ')') {
                appendToken(token);
                token = new StringBuilder();
                afterTokenization.append(origin.charAt(i) + " ");
            } else if (origin.charAt(i) == '&' || origin.charAt(i) == '|') {
                appendToken(token);
                token = new StringBuilder();
                afterTokenization.append(origin.charAt(i) + "" + origin.charAt(i) + " ");
                i++;
            } else if (origin.charAt(i) == ' ') {
                appendToken(token);
                token = new StringBuilder();
            } else {
                token.append(origin.charAt(i));
            }
        }
        appendToken(token);

        System.out.println(afterTokenization);
    }

}
