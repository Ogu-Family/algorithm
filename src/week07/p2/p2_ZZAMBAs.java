package src.week07.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1931
 * 메모리: 184924 KB
 * 시간: 1420 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 그리디 알고리즘
 * 끝 시간이 가장 앞에 있는 것부터 선택해나가면 되는 문제.
 * 웰노운이라 그렇지 처음에는 감도 못잡았던 문제였음.
 *
 * 문제에서 중요한 부분
 * - 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수
 * - 회의의 시작시간과 끝나는 시간이 같을 수도 있다.
 * - 시작 시간과 끝나는 시간은 2^31-1보다 작거나 같은 자연수 또는 0
 */

import java.util.Arrays;
import java.util.Scanner;

public class p2_ZZAMBAs {
    static int bitSlide = 32;
    static long mask = 0b11111111111111111111111111111111L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), res = 0;
        Long[] arr = new Long[N];

        for(int i = 0; i < N; i++)
            arr[i] = sc.nextLong() + (sc.nextLong() << bitSlide); // arr[i] = {끝나는 시간, 시작 시간}
        Arrays.sort(arr);

        long lastEndTime = 0L;
        for(int i = 0; i < N; i++) {
            long startTime = arr[i] & mask;
            long endTime = (arr[i] >> bitSlide) & mask;
            if(startTime < lastEndTime)
                continue;

            res++;
            lastEndTime = endTime;
        }

        System.out.println(res);
    }
}
