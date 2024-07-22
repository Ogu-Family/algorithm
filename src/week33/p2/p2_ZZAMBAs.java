package src.week33.p2;

/**
 * 문제 링크: https://www.acmicpc.net/problem/20366
 * 메모리: 370192 KB
 * 시간: 1916 ms
 * 시간 복잡도: O(N^2logN)
 * 공간 복잡도: O(N^2)
 */

/**
 * 정렬
 *
 * 1. 눈사람은 눈덩이 2개로 만들 수 있으므로, 만들 수 있는 눈사람 다 만들기
 * 2. 눈사람 정렬
 * 3. 연속된 두 눈사람을 선택하여, 사용한 눈덩이 4개가 중복되지 않는 경우 높이 차이를 구해 최소 값인 경우 업데이트.
 */

import java.util.*;
import java.util.stream.*;

public class p2_ZZAMBAs{
    static Scanner sc = new Scanner(System.in);
    static int N, res = Integer.MAX_VALUE;
    static List<Integer> chunks;
    static List<Triple<Snowman, Integer, Integer>> snowmanTriples = new ArrayList<>(); // { Snowman, 첫번째 덩어리 idx, 두번째 덩어리 idx }

    public static void main(String[] args) {
        N = sc.nextInt();
        chunks = IntStream.range(0, N).map(i -> sc.nextInt()).boxed().collect(Collectors.toList());

        for (int i = 0; i < N - 1; i++)
            for (int j = i + 1; j < N; j++)
                snowmanTriples.add(new Triple<>(new Snowman(chunks.get(i), chunks.get(j)), i, j));
        snowmanTriples.sort(Comparator.comparing(t -> t.t.sum()));

        for (int i = 0; i < snowmanTriples.size() - 1; i++) {
            Triple<Snowman, Integer, Integer> s1 = snowmanTriples.get(i);
            Triple<Snowman, Integer, Integer> s2 = snowmanTriples.get(i + 1);

            if (!hasSameChunk(s1, s2)) {
                res = Math.min(res, calGap(s1.t, s2.t));
            }
        }

        System.out.print(res);
    }

    static boolean hasSameChunk(Triple s1, Triple s2) {
        return s1.r.equals(s2.r) || s1.r.equals(s2.u) || s1.u.equals(s2.r) || s1.u.equals(s2.u);
    }

    static int calGap(Snowman s1, Snowman s2) {
        return Math.abs(s1.sum() - s2.sum());
    }

    static class Snowman {
        List<Integer> chunks = new ArrayList<>();

        Snowman(int ...chunks) {
            for (int chunk : chunks)
                this.chunks.add(chunk);
        }

        int sum() {
            return chunks.stream().mapToInt(c -> c).sum();
        }
    }

    static class Triple<T, R, U> {
        T t;
        R r;
        U u;

        Triple(T t, R r, U u) {
            this.t = t;
            this.r = r;
            this.u = u;
        }
    }
}
