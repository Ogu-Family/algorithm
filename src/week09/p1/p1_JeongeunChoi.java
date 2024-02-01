package algorithm.src.week09.p1;

import java.io.*;
import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15486
 * 메모리: 304480 KB
 * 시간: 712 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * DP
 * dp[n] n일 이전까지 최대 상담 금액
 * 입력받은 값에 t, p에 대해
 * 해당 시작 일자에 시작해서 끝나는 일자의 최대 상담 금액을 계산하여 dp에 넣는다.
 */

public class p1_JeongeunChoi {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            dp[i + 1] = Math.max(dp[i], dp[i + 1]); // i + 1일 이전까지 최대 상담 금액 갱신
            if (i + t <= N + 1) {
                dp[i + t] = Math.max(dp[i + t], dp[i] + p); // i + t일 이전까지 최대 상담 금액 갱신
            }
        }

        System.out.println(dp[N + 1]);
    }
}
