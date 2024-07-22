package src.week32.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15989
 * 메모리: 18368 KB
 * 시간: 264 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * dp
 *
 * 1. 1-3까지 아래를 반복한다. 이 때의 값을 i라 하자.
 * 2. j가 i부터 10000까지 dp[j] += dp[j - i]를 통해 경우의 수를 증가시켜 나간다.
 * 3. dp[n]을 출력한다.
 */

import java.util.Scanner;
import java.util.stream.IntStream;

public class p2_ZZAMBAs {
    static Scanner sc = new Scanner(System.in);
    static int T;
    static int[] dp = new int[10001];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        T = sc.nextInt();
        dp[0] = 1;
        IntStream.rangeClosed(1, 3).forEach(i -> IntStream.range(i, 10001).forEach(j -> dp[j] += dp[j - i]));

        IntStream.range(0, T).forEach(i -> {
            int n = sc.nextInt();
            sb.append(dp[n]).append('\n');
        });

        System.out.print(sb);
    }
}
