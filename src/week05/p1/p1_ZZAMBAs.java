package src.week05.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/1654
 * 메모리: 30424 KB
 * 시간: 432 ms
 * 시간 복잡도: O(KlogM) (M은 2^31 - 1)
 * 공간 복잡도: O(K)
 */

/**
 * 파라메트릭 서치. 브루트포스랑 가끔 헷갈려서 이 풀이를 놓치는 경우가 잦다.
 *
 * 문제에서 중요한 부분
 * - 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다.
 * - N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
 * - 최대 랜선의 길이를 구하는 ...
 * - 랜선의 길이는 2^31-1보다 작거나 같은 자연수이다. ( -> 이분 탐색 중간 값과 자른 랜선 개수가 int형 일시, 오버플로우가 일어날 수 있다.)
 */

import java.util.Scanner;

public class p1_ZZAMBAs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt(), N = sc.nextInt();
        int[] arr = new int[K];
        long start = 1, end = 1;

        for(int i = 0; i < K; i++) {
            arr[i] = sc.nextInt();
            end = Math.max(end, arr[i]);
        }

        while(start <= end) {
            long mid = (start + end) / 2;
            long cnt = 0;

            for(int val : arr)
                cnt += val / mid;

            if(cnt >= N)
                start = mid + 1;
            else
                end = mid - 1;
        }

        System.out.println(end);
    }
}
