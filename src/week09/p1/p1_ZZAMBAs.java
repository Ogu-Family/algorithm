package src.week09.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15486
 * 메모리: 323380 KB
 * 시간: 2484 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * DP
 * dp[i] = i일 상담 시작 전까지 최대한으로 받은 금액.
 * 이에 따라 N일 상담까지 전부 마친 최대 금액을 출력하려면 내 코드에서는 dp[N + 1]을 출력할 수밖에 없다.
 * 그러다보니 dp 초기화를 new int[N + 2]로 하였음.
 * 바텀 업 방식이 더 나아보여서 바텀 업 방식 채택.
 *
 * 문제에서 중요한 부분
 * -  N (1 ≤ N ≤ 1,500,000)
 * - 백준이가 얻을 수 있는 최대 수익을 구하여라.
 */

import java.util.*;

public class p1_ZZAMBAs{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), T, P;
        int[] dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);

            T = sc.nextInt();
            P = sc.nextInt();

            if (T + i > N + 1)
                continue;

            dp[T + i] = Math.max(dp[T + i], dp[i] + P);
        }
        dp[N + 1] = Math.max(dp[N + 1], dp[N]);

        System.out.println(dp[N + 1]);
    }
}
