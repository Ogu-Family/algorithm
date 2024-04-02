package algorithm.src.week17.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/23815
 * 메모리: 50000 KB
 * 시간: 396 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 풀이 참고한 문제
 * dp와 그리디를 이용한 문제
 * 처음에는 dfs로 풀었으나 시간초과 발생하여 문제 유형 확인
 * dp[N][2] 크기로 만들어, 스킵하지 않은 경우와 스킵한 경우를 나누어 계산
 * 이전 값을 바탕으로 두 선택지를 연산하여 더 큰 값을 넣어준다.
 */


public class p2_JeongeunChoi {

    private static int calculateOperation(int opr1, int opr2, char opt) {
        int answer = 0;

        switch (opt) {
            case '+':
                answer = opr1 + opr2;
                break;
            case '-':
                answer = opr1 - opr2;
                break;
            case '*':
                answer = opr1 * opr2;
                break;
            case '/':
                answer = opr1 / opr2;
                break;
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()), maxPeople;
        String[] s1 = new String[N], s2 = new String[N];
        int[][] dp = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s1[i] = st.nextToken();
            s2[i] = st.nextToken();
        }

        dp[0][0] = 1;
        dp[0][1] = 1;

        for (int i = 1; i <= N; i++) {
            char s1Opt = s1[i - 1].charAt(0);
            int s1Opr = Integer.parseInt(s1[i - 1].substring(1));

            char s2Opt = s2[i - 1].charAt(0);
            int s2Opr = Integer.parseInt(s2[i - 1].substring(1));

            if (dp[i - 1][0] > 0) {
                dp[i][0] = Math.max(calculateOperation(dp[i - 1][0], s1Opr, s1Opt),
                        calculateOperation(dp[i - 1][0], s2Opr, s2Opt));
                dp[i][1] = dp[i - 1][0];
            }
            if (dp[i - 1][1] > 0) {
                dp[i][1] = Math.max(Math.max(calculateOperation(dp[i - 1][1], s1Opr, s1Opt),
                                calculateOperation(dp[i - 1][1], s2Opr, s2Opt)),
                        dp[i][1]);
            }
        }

        maxPeople = Math.max(dp[N][0], dp[N][1]);
        if (maxPeople <= 0) {
            System.out.println("ddong game");
        } else {
            System.out.println(maxPeople);
        }
    }
}