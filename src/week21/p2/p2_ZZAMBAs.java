package src.week21.p2;

import java.util.*;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1644
 * 메모리: 30652 KB
 * 시간: 424 ms
 * 시간 복잡도: O(N * C) - 1억보다는 작음 (C = \sum\limits_{k=1}^N{\frac{N}{k}} )
 * 공간 복잡도: O(N)
 */

/**
 * 에라토스테네스의 체 + 투 포인터
 *
 * 1. 에라토스테네스의 체로 소수 판별 및 저장
 * 2. 소수들로 투 포인터 기법을 이용해 가짓 수를 찾음.
 */


public class p2_ZZAMBAs{
    static int res, N;
    static List<Integer> primes = new ArrayList<>();
    static boolean[] isNotPrime = new boolean[4000001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 2; i <= 4000000; i++)
            for (int j = i + i; j <= 4000000; j += i)
                isNotPrime[j] = true;
        for (int i = 2; i <= 4000000; i++)
            if (!isNotPrime[i])
                primes.add(i);

        N = sc.nextInt();

        int start = 0, end = 0, sum = 0;
        while (end <= primes.size() && start <= end && start < primes.size()) {
            if (sum >= N)
                sum -= primes.get(start++);
            else{
                if (end == primes.size())
                    break;
                sum += primes.get(end++);
            }

            if (sum == N)
                res++;
        }

        System.out.println(res);
    }
}

