package week09.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15486
 * 메모리: 315772 KB
 * 시간: 748 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/*
1. 상담을 완료하는데 걸리는 기간 T와 상담을 했을 때 받을 수 있는 금액 P를 입력 받아
2. n일에 얻을 수 있는 최대 이익 dp[n]을 계산
3. 최댓값 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1_letskuku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n + 1];
        int[] p = new int[n + 1];
        int[] dp = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }

            int next = i + t[i];
            if (next > n + 1) {
                continue;
            }

            dp[next] = Math.max(dp[next], max + p[i]);
        }

        if (max < dp[n + 1]) {
            max = dp[n + 1];
        }
        System.out.println(max);
    }
}
