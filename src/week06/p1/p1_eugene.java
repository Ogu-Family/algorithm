package week06.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_eugene {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][N];

        dp[0][0] = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int ans = dp[0][0];
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i+1; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    dp[i][j] += dp[i - 1][j];
                } else if (j == i) {
                    dp[i][j] += dp[i - 1][j - 1];
                } else {
                    dp[i][j] += Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }

                ans = Math.max(ans, dp[i][j]);
            }
        }

        System.out.println(ans);

    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1932
 * 메모리: 26152 KB
 * 시간: 292 ms
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(n^2)
 */

/**
 * DP
 * 윗줄의 오른쪽, 왼쪽 값 중 큰 값을 해당 줄에 더하는 방식
 * dp[0][0] 값을 저장하고, 그 다음 줄 부터 읽기, 윗줄에서 더 큰 값 더하기를 한번에 시행
 */
