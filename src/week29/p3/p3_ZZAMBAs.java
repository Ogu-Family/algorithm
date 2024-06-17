package src.week29.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/15922
 * 메모리: 207644 KB
 * 시간: 1600 ms
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

/**
 * 정렬
 *
 * 1. 선분의 시작점과 끝점을 따로 배열에 저장한다.
 * 2. 시작점이면 cnt 변수에 +1, 끝 점이면 -1을 더한다.
 * 3. 끝 점에서 cnt가 0이 되었다면 현재 끝점과 최초의 시작점 사이 길이를 결과에 더한다.
 * 4. 2, 3을 끝까지 반복한다.
 * 5. 결과를 출력한다.
 */

import java.util.*;
import java.util.stream.*;

public class p3_ZZAMBAs {
    static Scanner sc = new Scanner(System.in);
    static int N, cnt, start = Integer.MAX_VALUE, res;
    static Pair[] coor;

    public static void main(String[] args) {
        N = sc.nextInt();
        coor = new Pair[N * 2];
        IntStream.range(0, N).forEach(i -> {
            coor[i * 2] = new Pair(sc.nextInt(), true);
            coor[i * 2 + 1] = new Pair(sc.nextInt(), false);
        });
        Arrays.sort(coor, Comparator.comparing(Pair::getX));

        for (Pair p : coor) {
            cnt += (p.isStart) ? 1 : -1;
            start = Math.min(p.getX(), start);

            if (cnt == 0) {
                res += p.x - start;
                start = Integer.MAX_VALUE;
            }
        }

        System.out.println(res);
    }

    static class Pair {
        int x;
        boolean isStart;

        Pair (int x, boolean isStart) {
            this.x = x;
            this.isStart = isStart;
        }

        public int getX() {
            return x;
        }
    }
}

