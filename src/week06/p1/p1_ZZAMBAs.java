package src.week06.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1932
 * 메모리: 133216 KB
 * 시간: 936 ms
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(n^2)
 */

/**
 * DP
 * x칸까지 최대 값 = max(x 왼쪽 대각 위까지 최댓값, x 오른쪽 대각 위까지 최댓값) + x 칸 값
 *
 * 문제에서 중요한 부분
 * - 이제까지 선택된 수의 합이 최대
 * - 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 */

import java.util.Scanner;

public class p1_ZZAMBAs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), res = 0;
        int[][] pyramid = new int[n + 2][n + 2], dp = new int[n + 2][n + 2];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                pyramid[i][j] = sc.nextInt();
                dp[i][j] = -1;
            }
        }
        dp[1][1] = pyramid[1][1];

        for (int i = 1; i <= n; i++) {
            res = Math.max(res, fdp(n, i, pyramid, dp));
        }

        System.out.println(res);
    }

    static int fdp(int row, int col, int[][] pyramid, int[][] dp) {
        if (dp[row][col] >= 0) {
            return dp[row][col];
        }

        dp[row][col] = Math.max(fdp(row - 1, col, pyramid, dp), fdp(row - 1, col - 1, pyramid, dp))
            + pyramid[row][col];
        return dp[row][col];
    }
}
