package algorithm.src.week21.p2;

import java.io.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1644
 * 메모리: 45456 KB
 * 시간: 2276 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N)
 */

/**
 * 투포인터 미사용으로 시간초과 나서 풀이 참고
 * 누적합을 구한 후에 투포인터를 사용해서 경우의 수를 찾는 문제
 * 1. 소수 찾으면서 누적합 구하기
 * 2. 누적합에 대해 투포인터로 연속된 소수의 합으로 나타낼 수 있는 경우의 수 찾기
 */

public class p2_JeongeunChoi {

    private static int dpIdx = 0;
    private static long[] dp;

    private static void sumPrime(int N) {
        dp[dpIdx] = 0;
        dpIdx++;
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) {
                dp[dpIdx] += dp[dpIdx - 1] + i;
                dpIdx++;
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static int countPrimeSum(int N) {
        int l = 0, r = 0, cnt = 0;
        while (r < dpIdx) {
            long sum = dp[r] - dp[l];
            if (sum < N) {
                r++;
            } else if (sum > N) {
                l++;
            } else {
                cnt++;
                r++;
                l++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new long[N];
        sumPrime(N);

        System.out.println(countPrimeSum(N));
    }
}
