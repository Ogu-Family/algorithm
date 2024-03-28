package src.week17.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/27980
 * 메모리: 117084 KB
 * 시간: 512 ms
 * 시간 복잡도: O(NM)
 * 공간 복잡도: O(NM)
 */

/**
 * 그래프 탐색으로 어떻게든 하려 했지만 도저히 할 수 없었다.
 * 알고리즘 분류를 보고 해결. LCS처럼 문자열 DP.
 */

import java.util.Scanner;

public class p1_ZZAMBAs {
    static int N, M, maxV;
    static String board, word;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); board = sc.next(); word = sc.next();
        dp = new int[M][N];

        for (int i = 0; i < N; i++) {
            if (board.charAt(i) == word.charAt(0))
                dp[0][i] = 1;
        }

        for (int i = 1; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int left = j - 1, right = j + 1;

                if (left >= 0)
                    dp[i][j] = Math.max(dp[i - 1][left], dp[i][j]);
                if (right < N)
                    dp[i][j] = Math.max(dp[i - 1][right], dp[i][j]);
                if (word.charAt(i) == board.charAt(j))
                    dp[i][j]++;
            }
        }

        for (int i = 0; i < N; i++)
            maxV = Math.max(maxV, dp[M - 1][i]);

        System.out.println(maxV);
    }
}
