package algorithm.src.week04.p3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class p3_JeongeunChoi {

    static int N;

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindrome(String n) {
        int lp = 0, rp = n.length() - 1;
        while (lp <= rp) {
            if (n.charAt(lp) == n.charAt(rp)) {
                lp++;
                rp--;
            } else {
                return false;
            }
        }

        return true;
    }

    private static String intToString(int n) {
        String n_str = "";
        while (n != 0) {
            n_str += n % 10;
            n /= 10;
        }

        return n_str;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        while (!(isPrime(N) && isPalindrome(intToString(N)))) {
            N++;
        }

        System.out.println(N);
    }
}

/**
 * 문제 링크: https://www.acmicpc.net/problem/1747
 * 메모리: 21516 KB
 * 시간: 500 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(1)
 */

/**
 * 소수 판별은 2부터 루트N까지 나눠지는 수가 없는 경우 소수로 판별
 * 팰린드롭은 앞 뒤를 가리키는 포인터를 사용해서 같은지 비교하여 판별
 * 소수이면서 팰린드롭인 수가 발견되면 반복문 종료 후 정답 출력
 */