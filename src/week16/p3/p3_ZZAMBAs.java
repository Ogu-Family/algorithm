package src.week16.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/2004
 * 메모리: 17772 KB
 * 시간: 212 ms
 * 시간 복잡도: O(logn)으로 추정
 * 공간 복잡도: O(1)
 */

/**
 * n!의 5의 소인수 개수와 2의 소인수 개수를 구한다. 각각 a, b라 한다.
 * m!과 (n-m)!도 똑같이 5의 소인수 개수와 2의 소인수 개수를 구한 뒤 각각을 더하고 a와 b에서 뺀다.
 * 남은 a와 b 중 더 작은 값이 0의 개수다.
 */

import java.util.Scanner;

public class p3_ZZAMBAs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        System.out.println(cal(n, m));
    }

    static long cal(int num, int divideNum) {
        long rest = num - divideNum;
        long fiveNum, twoNum;
        fiveNum = getNum(5, num) - getNum(5, divideNum) - getNum(5, rest);
        twoNum = getNum(2, num) - getNum(2, divideNum) - getNum(2, rest);

        return Math.min(fiveNum, twoNum);
    }

    private static long getNum(long mulNum, long num) {
        long ret = 0;
        for (long i = mulNum; i <= num; i *= mulNum) {
            ret += num / i;
        }
        return ret;
    }
}
