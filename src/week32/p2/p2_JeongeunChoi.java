package algorithm.src.week32.p2;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15989
 * 메모리: 14948 KB
 * 시간: 156 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * dp
 * 풀이 참고한 문제
 * 합이 같고 순서만 다른 경우에 대해 중복 카운팅이 허용되지 않으므로, 더한 수들을 오름차순으로 정렬하여 1, 2, 3으로 끝나는 경우를 카운트한다.
 * dp[a][b]는 합이 a 이고 오름차순 정렬 시 b로 끝나는 경우의 수
 * dp[a][1] + dp[a][2] + dp[a][3] = 합이 n인 경우의 수가 된다.
 */

public class p2_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()), maxT = 0;
        int[] testCase = new int[T];
        for (int i = 0; i < T; i++) {
            testCase[i] = Integer.parseInt(br.readLine());
            maxT = Math.max(maxT, testCase[i]);
        }

        int[][] dp = new int[maxT + 1][4]; // dp[a][b] 합이 a 이고 오름차순 정렬 시 b로 끝나는 경우의 수
        dp[1][1] = 1;
        dp[2][1] = 1; dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;
        for (int i = 4; i <= maxT; i++) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }

        for (int i = 0; i < T; i++) {
            System.out.println(dp[testCase[i]][1] + dp[testCase[i]][2] + dp[testCase[i]][3]);
        }
    }

}
