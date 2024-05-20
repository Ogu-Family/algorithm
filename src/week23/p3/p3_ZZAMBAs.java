package src.week23.p3;

/**
 * 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/1493
 * 메모리: 17796 KB
 * 시간: 220 ms
 * 시간 복잡도: O(logN)으로 추정
 * 공간 복잡도: O(1)
 */

/**
 * 그리디 알고리즘
 *
 * 1. 가로, 세로, 높이 중 가장 큰 값을 기준으로 최대로 채울 수 있는 큐브를 선택. 그 큐브 한 변 길이를 K라 하자.
 * 2. 가로 * 세로 * K의 전체 칸 수를 센 후, 큰 것 부터 채워 나감.
 * 3. 높이를 K 만큼 빼서 업데이트. K가 0이 될 때까지 반복.
 */

import java.util.*;

public class p3_ZZAMBAs{
    static int l, w, h, N, res;
    static int[] cube = new int[20], pow2 = new int[20];
    static boolean canMake = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        pow2[0] = 1;
        for (int i = 1; i < 20; i++)
            pow2[i] = pow2[i - 1] * 2;
        l = sc.nextInt(); w = sc.nextInt(); h = sc.nextInt(); N = sc.nextInt();
        for (int i = 0; i < N; i++)
            cube[sc.nextInt()] = sc.nextInt();

        while (h != 0 && canMake)
            greedy();

        System.out.println(canMake ? res : -1);
    }

    static void greedy() {
        int minV = Math.min(l, Math.min(w, h));
        int minPow2Idx = binarySearch(minV);

        fill(l, w, minPow2Idx);
    }

    static void fill(int l, int w, int hIdx) {
        int curH = pow2[hIdx];
        long allArea = (long)l * w * curH, curArea = 0L;
        int accCubeCnt = 0;
        for (int i = hIdx; i >= 0; i--) {
            int cubeLength = pow2[i];
            int cnt = Math.min((l / cubeLength) * (w / cubeLength) * (curH / cubeLength) - accCubeCnt,
                cube[i]);
            cube[i] -= cnt;
            accCubeCnt = accCubeCnt * 8 + cnt * 8;
            res += cnt;
            curArea += (long) cubeLength * cubeLength * cubeLength * cnt;
        }

        if (curArea < allArea)
            canMake = false;
        h -= curH;
    }

    static int binarySearch(int val) {
        int start = 0, end = 19;
        while (start <= end) {
            int mid = (start + end) / 2;
            int midVal = pow2[mid];

            if (midVal > val)
                end = mid - 1;
            else
                start = mid + 1;
        }

        return end;
    }
}

