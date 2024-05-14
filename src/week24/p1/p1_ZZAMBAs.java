package src.week24.p1;

/**
 * 문제 링크: https://www.acmicpc.net/problem/28449
 * 메모리: 180272 KB
 * 시간: 1348 ms
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */

/**
 * 이분 탐색
 *
 * 1. 입력을 받을 때, bCnt[]에 동일한 b 값 개수를 저장한다. (b[1]은 b 원소 중 1 값의 개수)
 * 2. 수열 b(ARC 팀 원소)를 정렬
 * 3. 수열 a를 돌면서 b의 upper bound를 찾는다.
 * 4. bCnt를 참고하여 동일한 값은 draw에 추가하고 그것을 제외하여 a가 이긴 횟수, b가 이긴 횟수를 계산한다.
 */

import java.util.*;
import java.util.stream.*;

public class p1_ZZAMBAs{
    static int N, M;
    static long aWin, bWin, draw;
    static int[] a, b, bCnt = new int[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        a = new int[N];
        b = new int[M];
        IntStream.range(0, N).forEach(i -> a[i] = sc.nextInt());
        IntStream.range(0, M).forEach(i -> {
            b[i] = sc.nextInt();
            bCnt[b[i]]++;
        });
        Arrays.sort(b);
        Arrays.stream(a).forEach(v -> {
            int start = 0, end = M - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                int val = b[mid];

                if (val > v)
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            aWin += end + 1 - bCnt[v];
            bWin += M - end - 1;
            draw += bCnt[v];
        });

        System.out.print(aWin + " " + bWin + " " + draw);
    }
}

