/**
 * 문제 링크: https://www.acmicpc.net/problem/1644
 * 메모리: 27240 KB
 * 시간: 272 ms
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(N)
 */

/**
 * 투 포인터를 이용한 에라토스테네스의 체
 *
 * 1. 에라토스테네스의 체로 소수 판별 및 저장
 * 2. 소수들로 투 포인터 기법을 이용해 경우의 수를 찾음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SumOfConsecutivePrimeNumbers {

    private static final int MAX = 4000000;
    private static final boolean[] IS_PRIME_NUMBERS = new boolean[MAX + 1];
    private static final List<Integer> PRIME_NUMBERS = new ArrayList<>();

    // 최대값까지 소수 판별 및 저장
    static {
        for (int i = 2; i <= MAX; i++) {
            IS_PRIME_NUMBERS[i] = true;
        }

        for (int i = 2; i <= MAX; i++) {
            if (IS_PRIME_NUMBERS[i]) {
                for (int j = i * 2; j <= MAX; j += i) {
                    IS_PRIME_NUMBERS[j] = false;
                }
            }
        }

        for (int i = 2; i <= MAX; i++) {
            if (IS_PRIME_NUMBERS[i]) {
                PRIME_NUMBERS.add(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print(
                solution(
                        Integer.parseInt(
                                new BufferedReader(new InputStreamReader(System.in)).readLine()
                        )
                )
        );
    }

    private static int solution(int n) {
        int count = 0;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (true) {
            if (sum >= n) {
                sum -= PRIME_NUMBERS.get(left++);
            } else if (right == PRIME_NUMBERS.size()) {
                break;
            } else {
                sum += PRIME_NUMBERS.get(right++);
            }

            if (sum == n) {
                count++;
            }
        }

        return count;
    }
}
