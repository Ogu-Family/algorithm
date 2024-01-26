package src.week08.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1937
 * 메모리: 218236 KB
 * 시간: 1192 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/**
 * 그래프 DP
 * 각 칸에 대해 그래프 탐색을 해야하나 싶었지만 n^2 = 25000인지라 각 칸을 전부 탐색하면 시간초과 날 것 같았음
 * 판다가 결국 어떤 칸에 도달하면 최대로 갈 수 있는 칸 수는 결정이 되기에 다시 이것을 계산할 필요가 없을 것이라 생각 -> DP
 *
 * 문제에서 중요한 부분
 * - n(1 ≤ n ≤ 500)
 * - 대나무를 먹고 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다.
 * - 어떤 지점에 처음에 풀어 놓아야 하고, 어떤 곳으로 이동을 시켜야 판다가 최대한 많은 칸을 방문
 * - 판다가 이동할 수 있는 칸의 수의 최댓값을 출력
 */

import java.util.Scanner;

public class p3_ZZAMBAs{
    static int n, res;
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
    static int[][] bamboo, dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        res = 0;
        bamboo = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                bamboo[i][j] = sc.nextInt();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res = Math.max(res, fdp(i, j));

        System.out.println(res);
    }

    public static int fdp(int row, int col) {
        if (dp[row][col] > 0)
            return dp[row][col];

        dp[row][col] = 1;

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if (canGo(bamboo[row][col], nextRow, nextCol)) {
                dp[row][col] = Math.max(dp[row][col], fdp(nextRow, nextCol) + 1);
            }
        }

        return dp[row][col];
    }

    public static boolean canGo(int preNum, int destRow, int destCol) {
        return destRow >= 0 && destRow < n && destCol >= 0 && destCol < n
            && bamboo[destRow][destCol] > preNum;
    }
}
