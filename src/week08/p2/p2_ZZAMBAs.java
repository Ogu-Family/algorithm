package src.week08.p2;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20300
 * 메모리: 32208 KB
 * 시간: 484 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 그리디 알고리즘, 정렬
 * 맨 앞과 맨 뒤를 짝지어서 가장 큰 수가 답. 홀수는 맨 뒤를 따로 뺌.
 * 그리디는 어려운 것도 어렵고 쉬운 것도 어렵다.
 *
 * 문제에서 중요한 부분
 * - 0 <= t_i <= 10^18 (오버플로우 주의)
 */

public class p2_ZZAMBAs{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long res = 0;
        long[] arr = new long[N];

        for(int i = 0; i < N; i++)
            arr[i] = sc.nextLong();
        Arrays.sort(arr);

        if (N % 2 == 1)
            res = arr[N-- - 1];

        for (int i = 0; i < N / 2; i++)
            res = Math.max(res, arr[i] + arr[N - i - 1]);

        System.out.println(res);
    }
}
