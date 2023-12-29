package src.week04.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p3_GiHoo {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        solution();
    }

    private static void solution() {

        int answer = N;

        while (!isPalindrome(answer) || !isPrime(answer)) {
            answer++;
        }

        System.out.print(answer);
    }

    private static boolean isPalindrome(int target) {
        String str = String.valueOf(target);

        int lt = 0;
        int rt = str.length() - 1;

        while (lt < rt) {
            if (str.charAt(lt) != str.charAt(rt)) return false;
            lt++;
            rt--;
        }

        return true;
    }

    private static boolean isPrime(int target) {
        if (target == 1) return false;

        for (int i = 2; i <= Math.sqrt(target); i++) {
            if (target % i == 0) return false;
        }

        return true;
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1747
 * 메모리: 39424 KB
 * 시간: 252 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 문제의 조건 - N보다 크거나 같음, 소수, 팰린드롬
 * 심플하게 입력 N 부터 소수와 팰린드롬인지를 NlogN 안쪽으로 풀면 되는 문제
 *
 * 소수의 경우 N이 1일 때 자기 자신을 반환하는 부분만 고려하여 문제를 해결
 */
