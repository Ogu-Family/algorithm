package src.week14.p3;

/**
 * 문제 링크: https://www.acmicpc.net/problem/14620
 * 메모리: 93856 KB
 * 시간: 808 ms
 * 시간 복잡도: O((N^2)^3) = O(N^6)
 * 공간 복잡도: O(N^2)
 */

/**
 * 백트래킹 문제인데 좀 많이 귀찮게 하는.
 *
 * 맨해튼 거리 3 이상이어야 하는 조건 + 꽃이 화단 범위를 안 벗어나도록 하는 조건을 이용해 풀이.
 */

import java.util.*;

public class p3_ZZAMBAs {
    static int SEED_NUM = 3, DISTANCE_LIMIT = 2;
    static ArrayList<Pair> seeds = new ArrayList<>();
    static int N, res = Integer.MAX_VALUE;
    static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
    static int[][] garden;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        garden = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                garden[i][j] = sc.nextInt();
            }
        }

        backtracking(0);

        System.out.print(res);
    }

    static void backtracking(int depth) {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                seeds.add(new Pair(i, j));

                if (depth != SEED_NUM - 1) {
                    backtracking(depth + 1);
                } else {
                    calculate();
                }

                seeds.remove(depth);
            }
        }

    }

    static void calculate() { // 최소 비용 계산
        if (isOk()) {
            res = Math.min(res, seeds.stream().mapToInt((pair) -> {
                int charge = garden[pair.r][pair.c];
                for(int i = 0; i < 4; i++)
                    charge += garden[pair.r + dr[i]][pair.c + dc[i]];

                return charge;
            }).sum());
        }
    }

    static boolean isOk() {
        for(int i = 0; i < SEED_NUM; i++) { // 꽃이 화단을 넘는지 체크
            for(int j = 0; j < 4; j++) {
                if (seeds.get(i).r + dr[j] < 0 || seeds.get(i).r + dr[j] >= N || seeds.get(i).c + dc[j] < 0 || seeds.get(i).c + dc[j] >= N)
                    return false;
            }
        }

        for(int i = 0; i < SEED_NUM - 1; i++) { // 맨해튼 거리 2 이하인 것이 존재하는지 체크
            for(int j = i + 1; j < SEED_NUM; j++) {
                if(manhattanDistance(seeds.get(i), seeds.get(j)) <= DISTANCE_LIMIT)
                    return false;
            }
        }

        return true;
    }

    static int manhattanDistance(Pair p1, Pair p2) { // 맨해튼 거리 계산
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }

    static class Pair { // Coordinate
        int r, c;

        Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
