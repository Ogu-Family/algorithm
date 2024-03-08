/**
 * 문제 링크: https://www.acmicpc.net/problem/14620
 * 메모리: 21440 KB
 * 시간: 144 ms
 * 시간 복잡도: O(2^(N^2))
 * 공간 복잡도: O(N^2)
 */

/**
 * 백트래킹 문제
 *
 * 비용에 대한 costs + 꽃을 심을 수 있는지에 대한 isExist 배열을 사용하여 백트래킹 진행하여 풀이하였습니다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FlowerPaths {

    static final int[][] ZONES = {
            {0, 0},
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };
    static final int TARGET_COUNT = 3;
    static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.out.print(solution(parseCosts()));
    }

    private static int[][] parseCosts() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] garden = new int[n][n];

        for (int i = 0; i < n; i++) {
            garden[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return garden;
    }

    private static int solution(int[][] costs) {
        boolean[][] isExist = new boolean[costs.length][costs[0].length]; // 꽃이 심어져 있는지 여부
        bruteForce(costs, isExist, 0, 0);
        return minCost;
    }

    private static void bruteForce(int[][] costs, boolean[][] isExist, int count, int currentCost) {
        // 목표 개수만큼 꽃이 심어졌다면 최소 비용을 갱신하고 종료
        if (count == TARGET_COUNT) {
            minCost = Math.min(minCost, currentCost);
            return;
        }

        // 가장자리를 제외한 모든 좌표에 대해 꽃을 심음(가장 자리는 꽃을 심을 수 없음)
        for (int n = 1; n < costs.length - 1; n++) {
            for (int m = 1; m < costs[n].length - 1; m++) {
                Coordinate coordinate = new Coordinate(n, m);
                // 꽃이 심어지는 범위에 대한 비용 계산
                int cost = calculateCost(costs, coordinate);
                // 현재 비용이 최소 비용보다 크다면 더 이상 진행하지 않음
                if (currentCost + cost >= minCost) {
                    continue;
                }
                // 꽃을 심을 수 있다면 심고,
                if (seedFlower(isExist, coordinate)) {
                    // 다음 단계로 진행
                    bruteForce(costs, isExist, count + 1, currentCost + cost);
                    // 꽃을 심은 것을 되돌림
                    rollbackFlower(isExist, coordinate);
                }
            }
        }
    }

    private static int calculateCost(int[][] garden, Coordinate coordinate) {
        int cost = 0;

        for (int[] zone : ZONES) {
            int n = coordinate.n + zone[0];
            int m = coordinate.m + zone[1];
            cost += garden[n][m];
        }

        return cost;
    }

    private static boolean seedFlower(boolean[][] isExist, Coordinate coordinate) {
        for (int[] zone : ZONES) {
            int n = coordinate.n + zone[0];
            int m = coordinate.m + zone[1];
            if (isExist[n][m]) {
                return false;
            }
        }

        for (int[] zone : ZONES) {
            int n = coordinate.n + zone[0];
            int m = coordinate.m + zone[1];
            isExist[n][m] = true;
        }

        return true;
    }

    private static void rollbackFlower(boolean[][] isExist, Coordinate coordinate) {
        for (int[] zone : ZONES) {
            int n = coordinate.n + zone[0];
            int m = coordinate.m + zone[1];
            isExist[n][m] = false;
        }
    }

    static class Coordinate {

        int n;
        int m;

        public Coordinate(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
