package src.week04.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1747
 * 메모리: 21268 KB
 * 시간: 528 ms
 * 시간 복잡도: O(NlogN) (N은 최대로 나올 수 있는 답. 이 문제의 최대 답은 1003001)
 * 공간 복잡도: O(1)
 */

/**
 * 팰린드롬과 소수 판별. 1이 소수 아니라는 것을 잊고 있었어서 이거 찾느라 한세월 걸렸다.
 * 소수 판별 => i = 2부터 sqrt(N) (= logN) 까지 돌면서 판별. (https://doodle-ns.tistory.com/32)
 * 팰린드롬 => (길이 / 2)번 앞 뒤 비교해보면 됨.
 *
 * 문제에서 중요한 부분
 * - 1 ≤ N ≤ 1,000,000
 * - N보다 크거나 같고, 소수이면서 팰린드롬인 수 중에서, 가장 작은 수
 */

import java.util.Scanner;

public class p3_ZZAMBAs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        while(true) {
            if(isPrime(N) && isPalindrome(N))
                break;
            N++;
        }

        System.out.println(N);
    }

    public static boolean isPrime(int num) {
        if (num == 1) // 이거 때매 시간 잡아먹힘
            return false;

        for(int i = 2; num >= i * i; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }

    public static boolean isPalindrome(int num) {
        String s = String.valueOf(num);
        int size = s.length();

        for(int i = 0; i < size / 2; i++) {
            if(s.charAt(i) != s.charAt(size - 1 - i))
                return false;
        }

        return true;
    }
}
