package week08.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1937
 * 메모리: 60084 KB
 * 시간: 532 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N^2)
 */

/*
1. 대나무 숲 정보를 입력받아 dfs로 최대한 많은 대나무를 먹을 수 있게 하는 경로 탐색
2. 이때, 이전에 최대한 많은 대나무를 먹을 수 있게 하는 경로를 이미 구한 위치는 값을 저장해놓았다가 불러와서 사용
3. 최댓값 출력

메모리가 많이 드는 편인 것 같은데 다른 사람들과 어떤 부분이 달라서 그런건지 잘 모르겠습니다... 알려주실 분...~
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p3_letskuku {
    static int n;
    static int[][] forest, dp;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        forest = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }

        System.out.println(ans);
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                continue;
            }

            if (forest[x][y] < forest[nx][ny]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }
}
