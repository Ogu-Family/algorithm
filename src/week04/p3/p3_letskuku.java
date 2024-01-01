package week04.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1747
 * 메모리: 19652 KB
 * 시간: 204 ms
 * 시간 복잡도: O(Nlog(logN))
 * 공간 복잡도: O(N)
 */

/*
1. 2000000까지의 수 중 소수인 것을 골라내고
2. n을 입력 받아 n 이상이고 소수이면서 팰린드롬인 가장 작은 수 계산
3. 결과 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p3_letskuku {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int ans = 0;
        boolean[] isPrime = new boolean[2000001];

        for (int i = 0; i < 2000001; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(2000000); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < 2000001; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = n; i < 2000001; i++) {
            if (isPrime[i] && checkPalindrome(Integer.toString(i))) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

    public static boolean checkPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i <= len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }

        return true;
    }
}
